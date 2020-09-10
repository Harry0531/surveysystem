package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.UserDAO;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean isNameUnused(String userName) {
        return userDAO.getUserNumberByName(userName) == 0;
    }

    public String login(UserEntity user) {
        if (userDAO.getUserNumberByName(user.getUserName()) == 0)
            return "User not exist";
        if (userDAO.getPasswordByName(user.getUserName()).equals(user.getPassword())) {
            user.setLastLoginTime(new Date());
            userDAO.updateLoginTime(user);
            return "Success";
        } else
            return "Wrong password";
    }

    public boolean register(UserEntity user) {
        if (!isNameUnused(user.getUserName()))
            return false;
        int availableId = userDAO.getAvailableId() - 1;
        user.preInsert(availableId);
        return userDAO.insertUserEntry(user) == 1;
    }
}
