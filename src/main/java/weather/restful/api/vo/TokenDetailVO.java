package weather.restful.api.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
public class TokenDetailVO {
    private String keyValue;
    private Date startTime;
    private int usageLimit;

    public int incrementUsageLimit() {
        usageLimit++;
        return usageLimit;
    }

    public long usageTime() {
        return TimeUnit.HOURS.convert(Math.abs(new Date().getTime()-startTime.getTime()), TimeUnit.MILLISECONDS);
    }
}
