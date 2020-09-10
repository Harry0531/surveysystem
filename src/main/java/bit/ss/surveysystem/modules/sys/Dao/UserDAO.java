package bit.ss.surveysystem.modules.sys.Dao;

import bit.ss.surveysystem.modules.sys.Entity.UserEntity;

public interface UserDAO {
    int getUserNumberByName(String userName);
    String getPasswordByName(String userName);
    int getAvailableId();
    int insertUserEntry(UserEntity user);
    int updateLoginTime(UserEntity user);
    UserEntity getUserInfoByUsername(String userName);
}
