package weather.restful.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weather.restful.api.service.IWeatherService;
import weather.restful.api.vo.RestException;
import weather.restful.api.vo.WeatherRequestVO;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    IWeatherService weatherService;

    @GetMapping(value = "/weather/v1/api/getWeatherInfo")
    @ApiOperation(value = "Get weather info ", response = ResponseEntity.class, tags = "getWeatherInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!")})
    public ResponseEntity getWeatherInfo(@RequestParam("city") String city,
                                         @RequestParam("country") String country,
                                         @RequestParam("apiKey") String apiKey) {
        try {
            log.debug("entered service");
            return ResponseEntity.ok().body(weatherService.getWeatherDesc(new WeatherRequestVO(city, country, apiKey)));
        } catch (RestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
