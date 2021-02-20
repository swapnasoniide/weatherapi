package weather.restful.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {
    protected MockMvc mvc;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(weatherController)
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        String uri = "/weather/v1/api/getWeatherInfo";
        Map queryParams = new HashMap<String, String>();
        queryParams.put("city", "melbourne");
        queryParams.put("state", "vic");
        queryParams.put("apiKey","key1");
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("city", "melbourne")
                        .queryParam("state", "vic")
                        .queryParam("apiKey","key1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("hello world");
    }
}
