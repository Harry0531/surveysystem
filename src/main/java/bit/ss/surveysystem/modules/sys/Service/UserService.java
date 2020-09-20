package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.UserDAO;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean isNameUsed(String userName) {
        UserEntity userEntity = userDAO.getUserByName(userName);
        return  userEntity != null;
    }

    public UserEntity login(UserEntity user) {
        UserEntity userId = userDAO.getUserByName(user.getUsername());

        if ( userId == null) {
            return null;
        }
        if (userDAO.getPasswordByName(user.getUsername()).equals(user.getPassword())) {
            user.setLastLoginTime(new Date());
            userDAO.updateLoginTime(user);
            return userId;
        } else{
            return null;
        }

    }


    public boolean register(UserEntity user) {
        if (isNameUsed(user.getUsername()))
            return false;
        user.preInsert();
        user.setRole("student");
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(user.getId());
        userInfoEntity.setAdmissionNumber(user.getUsername());
        try{
            userDAO.insertUserInfoEntry(userInfoEntity);
            userDAO.insertUserEntry(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<UserInfoEntity> getUserInfoByConditions(UserInfoEntity userInfoEntity){
        return  userDAO.getUserInfoByEntity(userInfoEntity);
    }

    public boolean updateUserInfo(UserInfoEntity userInfoEntity){
        return userDAO.updateUserInfo(userInfoEntity) == 1;
    }
    
    public List<UserEntity> getUserByConditions(UserEntity userEntity){
        return userDAO.getUserByConditions(userEntity);
    }

    public int deleteUserByIds(List<UserEntity> userEntities){
        userDAO.deleteUserInfoByIds(userEntities);
        return userDAO.deleteUserByIds(userEntities);
    }

}
