package weather.restful.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import weather.restful.api.dao.ITokenDAO;
import weather.restful.api.vo.TokenDetailVO;

import java.util.Date;

public interface ITokenService {


    void updateTokenTime(TokenDetailVO tokenDetailVO);

    void incrementTokenLimit(TokenDetailVO tokenDetailVO);

    TokenDetailVO getTokenDetailVO(String key);
}
