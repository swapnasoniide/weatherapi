package weather.restful.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import weather.restful.api.dao.IWeatherDAO;
import weather.restful.api.enums.ErrorMessagesEnum;
import weather.restful.api.vo.RestException;
import weather.restful.api.vo.TokenDetailVO;
import weather.restful.api.vo.WeatherRequestVO;

import java.net.URI;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class WeatherServiceImpl implements IWeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ITokenService tokenService;

    @Autowired
    IWeatherDAO weatherDAO;

    @Value("${weather.opensource.uri}")
    private String weatherApiUri;

    @Value("${token.usage.limit}")
    private Integer tokenUsageLimit;

    @Value("${token.time.limit}")
    private Integer tokenTimeLimit;

    @Autowired
    MessageSource messageSource;

    public String callWeatherApi(WeatherRequestVO requestVO) throws RestException {
        String desc="";
        try {
            URI url = new UriTemplate(weatherApiUri).expand(requestVO.getCityName(), requestVO.getCountryName(), requestVO.getApiKey());
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            desc = root.path("weather").get(0).path("main").asText();
        }catch(Exception e) {
            throw new RestException(messageSource.getMessage(ErrorMessagesEnum.WEATHER_NORESPONSE.getCode(), new Object[]{}, Locale.getDefault()));
        }
        log.debug("Weather Condition : {}", desc);
        return desc;
    }


    //todo - better way of doing it
    public void valiateApiKey(String apiKey) throws RestException {

        if(Objects.isNull(apiKey)){
            throw new RestException(messageSource.getMessage(ErrorMessagesEnum.TOEKN_MISSING.getCode(), new Object[]{}, Locale.getDefault()));
        }

        TokenDetailVO tokenDetailVO = tokenService.getTokenDetailVO(apiKey);
        if(Objects.isNull(tokenDetailVO)) {
            throw new RestException(messageSource.getMessage(ErrorMessagesEnum.INVALID_TOKEN.getCode(), new Object[]{apiKey}, Locale.getDefault()));
        }

        tokenService.updateTokenTime(tokenDetailVO);
        if(tokenDetailVO.incrementUsageLimit() > tokenUsageLimit) {
            if(tokenDetailVO.usageTime() > tokenTimeLimit) {
                throw new RestException(messageSource.getMessage(ErrorMessagesEnum.TOKEN_TIME_LIMIT_EXCEED.getCode(), new Object[]{apiKey,tokenUsageLimit}, Locale.getDefault()));
            }
            throw new RestException(messageSource.getMessage(ErrorMessagesEnum.TOKEN_USAGE_LIMIT_EXCEED.getCode(), new Object[]{apiKey,tokenUsageLimit}, Locale.getDefault()));
        } else {
            tokenService.incrementTokenLimit(tokenDetailVO);
        }
    }

    public void validateRequestParam(WeatherRequestVO requestVO) throws RestException {
        if(Stream.of(requestVO.getCityName(), requestVO.getCountryName()).allMatch(Objects::isNull)) {
            throw new RestException(messageSource.getMessage(ErrorMessagesEnum.WEATHER_MISSINGPARAM.getCode(), new Object[]{}, Locale.getDefault()));
        }
    }

    @Override
    public String getWeatherDesc(WeatherRequestVO requestVO) throws RestException {
        valiateApiKey(requestVO.getApiKey());
        validateRequestParam(requestVO);

        String desc = getWeatherDescFromDB(requestVO);
        if(Objects.isNull(desc)) {
            desc = callWeatherApi(requestVO);
            insertWeatherDescToDB(requestVO, desc);
        }
        return desc;
    }

    public String getWeatherDescFromDB(WeatherRequestVO requestVO) {
        return weatherDAO.getWeatherDesc(requestVO);
    }

    public String insertWeatherDescToDB(WeatherRequestVO requestVO, String desc) {
        return weatherDAO.insertWeatherDesc(requestVO, desc);
    }

}
