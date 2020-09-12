package bit.ss.surveysystem.modules.sys.Dao;

import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import org.apache.catalina.User;

public interface UserDAO {
    UserEntity getUserByName(String userName);
    String getPasswordByName(String userName);

    int insertUserEntry(UserEntity user);
    int insertUserInfoEntry(UserInfoEntity user);

    int updateUserInfo(UserInfoEntity userInfoEntity);
    int updateLoginTime(UserEntity user);
    UserInfoEntity getUserInfoByEntity(UserInfoEntity user);


}
