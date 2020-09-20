package bit.ss.surveysystem.modules.sys.Dao;

import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import java.util.List;
import org.apache.catalina.User;

public interface UserDAO {
    UserEntity getUserByName(String userName);
    String getPasswordByName(String userName);

    int insertUserEntry(UserEntity user);
    int insertUserInfoEntry(UserInfoEntity user);

    int updateUserInfo(UserInfoEntity userInfoEntity);
    int updateUser(UserEntity userInfoEntity);
    int updateLoginTime(UserEntity user);
    List<UserInfoEntity> getUserInfoByEntity(UserInfoEntity user);
    List<UserEntity> getUserByConditions(UserEntity userEntity);

    int deleteUserByIds(List<UserEntity> userEntities);
    int deleteUserInfoByIds(List<UserEntity> userEntities);
}
