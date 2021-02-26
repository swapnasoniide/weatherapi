package weather.restful.api.dao;

import org.springframework.stereotype.Repository;
import weather.restful.api.vo.WeatherRequestVO;

@Repository
public class WeatherDAOImpl implements IWeatherDAO {
    @Override
    public String getWeatherDesc(WeatherRequestVO requestVO) {
        return null;
    }

    @Override
    public String insertWeatherDesc(WeatherRequestVO requestVO, String desc) {
        return null;
    }
}
