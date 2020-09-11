package bit.ss.surveysystem;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import bit.ss.surveysystem.modules.sys.Service.DictService;
import bit.ss.surveysystem.modules.sys.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Dicttest {

    @Autowired
    DictService dictService;

    @Test
    void testSelectDictType() {
        List<String> s = dictService.selectDictTypeList();
        System.out.println(s);
    }

    @Test
    void testSelectDict(){
        Dict dict = new Dict();
        Page<Dict> page = new Page<>();
        page.setPageSize(10);
        page.setPageIndex(2);
        page.setPageStart(100);
        dict.setPage(page);
        List<Dict> dicts = dictService.selectDictListByPage(dict);
        System.out.println(dicts);
    }
}
