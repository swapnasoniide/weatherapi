package weather.restful.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weather.restful.api.dao.ITokenDAO;
import weather.restful.api.vo.TokenDetailVO;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
@Transactional
public class TokenServiceImpl implements ITokenService {

    @Value("${token.usage.limit}")
    private Integer tokenUsageLimit;

    @Autowired
    ITokenDAO tokenDOA;

    @Override
    public void updateTokenTime(TokenDetailVO tokenDetailVO) {
        // check if key is used first time then update its start time
        if (tokenDetailVO.getUsageLimit() == 1 && Objects.isNull(tokenDetailVO.getStartTime())) {
            tokenDetailVO.setStartTime(new Date());
            tokenDOA.updateTokenTime(tokenDetailVO);
        }
    }

    @Override
    public void incrementTokenLimit(TokenDetailVO tokenDetailVO) {
        tokenDOA.updateTokenLimit(tokenDetailVO);
    }


    @Override
    public TokenDetailVO getTokenDetailVO(String key) {
        TokenDetailVO tokenDetailVO = tokenDOA.fetchTokenDetail(key);
        log.debug("Fetched token details : {}", tokenDetailVO);
        return tokenDetailVO;
    }
}
