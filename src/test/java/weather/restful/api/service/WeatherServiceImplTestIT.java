package weather.restful.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.nashorn.internal.AssertsEnabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import weather.restful.api.vo.WeatherRequestVO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherServiceImplTestIT {

    @Autowired
    IWeatherService weatherService;

    @Test
    void getWeatherDesc() {
        WeatherRequestVO requestVO = new WeatherRequestVO();
        requestVO.setCountryName("UK");
        requestVO.setCityName("London");
        requestVO.setApiKey("cdd75f9136de7e5ca88e345caf824e3a");

        String desc = weatherService.getWeatherDesc(requestVO);
        assertNotNull(desc);
    }
}