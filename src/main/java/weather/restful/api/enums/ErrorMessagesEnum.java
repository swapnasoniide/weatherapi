package weather.restful.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessagesEnum {
    TOEKN_MISSING("token.error.missing"),
    INVALID_TOKEN("token.error.notfound"),
    TOKEN_USAGE_LIMIT_EXCEED("token.error.usageLimitExceed"),
    TOKEN_TIME_LIMIT_EXCEED("token.error.timeLimitExceed"),
    WEATHER_NORESPONSE("weather.error.noresponse"),
    WEATHER_MISSINGPARAM("weather.error.missingparam");

    private String code;
}
