package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.UserDAO;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean isNameUnused(String userName) {
        return userDAO.getUserNumberByName(userName) == 0;
    }

    public String login(UserEntity user) {
        if (userDAO.getUserNumberByName(user.getUsername()) == 0)
            return "User not exist";
        if (userDAO.getPasswordByName(user.getUsername()).equals(user.getPassword())) {
            user.setLastLoginTime(new Date());
            userDAO.updateLoginTime(user);
            return "Success";
        } else
            return "Wrong password";
    }


    public boolean register(UserEntity user) {
        if (!isNameUnused(user.getUsername()))
            return false;
        user.preInsert();
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

    public UserInfoEntity getUserInfoById(UserInfoEntity userInfoEntity){
        return  userDAO.getUserInfoByEntity(userInfoEntity);
    }

    public boolean updateUserInfo(UserInfoEntity userInfoEntity){
        return userDAO.updateUserInfo(userInfoEntity) == 1;
    }
}
