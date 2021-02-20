package weather.restful.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WeatherController {

    @GetMapping(value="/weather/v1/api/getWeatherInfo")
    @ApiOperation(value = "Get weather info ", response = ResponseEntity.class, tags = "getWeatherInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!") })
    public ResponseEntity getWeatherInfo(@RequestParam("city") String city,
                                         @RequestParam("state") String state,
                                         @RequestParam("apiKey") String apiKey){
        log.debug("entered service");
        return ResponseEntity.ok().body("hello world");
    }
}
