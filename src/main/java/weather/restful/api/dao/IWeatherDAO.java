package weather.restful.api.dao;

import weather.restful.api.vo.WeatherRequestVO;

public interface IWeatherDAO {

    String getWeatherDesc(WeatherRequestVO requestVO);

    String insertWeatherDesc(WeatherRequestVO requestVO, String desc);
}
