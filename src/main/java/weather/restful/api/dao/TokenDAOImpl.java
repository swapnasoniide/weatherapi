package weather.restful.api.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import weather.restful.api.vo.TokenDetailVO;

import java.util.Date;

@Repository
@Slf4j
public class TokenDAOImpl implements ITokenDAO {

    @Autowired
    private JdbcTemplate jtm;


    @Override
    public void updateTokenTime(TokenDetailVO tokenDetailVO) {
        String SQL = "update TOKEN_DETAIL set start_time = ? where key_value = ?";
        int recordsUpdated = jtm.update(SQL, tokenDetailVO.getStartTime(), tokenDetailVO.getKeyValue());
        log.debug("Start Time updated for rows {}",recordsUpdated);
    }

    @Override
    public void updateTokenLimit(TokenDetailVO tokenDetailVO) {
        String SQL = "update TOKEN_DETAIL set usage_limit = ? where key_value = ?";
            int recordsUpdated = jtm.update(SQL, tokenDetailVO.getUsageLimit(), tokenDetailVO.getKeyValue());
        log.debug("Usage limit updated for rows {}",recordsUpdated);
    }

    @Override
    public TokenDetailVO fetchTokenDetail(String key) {

        String sql = "SELECT key_value as keyValue, start_time as startTime, usage_limit as usageLimit FROM  WHERE key_value = ?";
        return jtm.queryForObject(sql, new Object[]{key},
                new BeanPropertyRowMapper<>(TokenDetailVO.class));
    }
}
