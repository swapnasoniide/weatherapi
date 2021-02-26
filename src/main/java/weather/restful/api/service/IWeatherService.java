package weather.restful.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import weather.restful.api.vo.RestException;
import weather.restful.api.vo.WeatherRequestVO;

public interface IWeatherService {
    String getWeatherDesc(WeatherRequestVO requestVO) throws RestException;

}
