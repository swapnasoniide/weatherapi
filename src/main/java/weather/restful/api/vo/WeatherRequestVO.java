package weather.restful.api.vo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequestVO {

    private String countryName;
    private String cityName;
    private String apiKey;

}
