package bit.ss.surveysystem;

import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import bit.ss.surveysystem.modules.sys.Service.DictService;
import bit.ss.surveysystem.modules.sys.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Dicttest {

    @Autowired
    DictService dictService;

    @Test
    void testSelectDictType() {
        System.out.println(dictService.selectDictTypeList());
    }
}
