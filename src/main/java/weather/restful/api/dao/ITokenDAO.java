package weather.restful.api.dao;

import weather.restful.api.vo.TokenDetailVO;

public interface ITokenDAO {
    void updateTokenTime(TokenDetailVO tokenDetailVO);

    void updateTokenLimit(TokenDetailVO tokenDetailVO);

    TokenDetailVO fetchTokenDetail(String key);
}
