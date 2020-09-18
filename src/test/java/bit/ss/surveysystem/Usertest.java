package bit.ss.surveysystem;

import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import bit.ss.surveysystem.modules.sys.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Usertest {

    @Autowired
    UserService userService;

    @Test
    void testRegister(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("1120171192");
        userEntity.setEmail("997009553@qq.com");
        userEntity.setPassword("password");
        userEntity.setRole("student");
        userEntity.preInsert();
        userService.register(userEntity);
    }


    @Test
    void testGetUserInfo(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId("2bcdd55957804be38f99e770fbfd8a20");
        System.out.println(userService.getUserInfoByConditions(userInfoEntity));
    }

    @Test
    void testUpdatetUserInfo(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName("日天");
        userInfoEntity.setContact("13681536017");
        userInfoEntity.setType(2);
        userInfoEntity.setId("2bcdd55957804be38f99e770fbfd8a20");
        System.out.println(userService.updateUserInfo(userInfoEntity));
    }
}
