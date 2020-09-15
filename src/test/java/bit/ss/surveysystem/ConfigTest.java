package bit.ss.surveysystem;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Service.DictService;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConfigTest {

    @Autowired
     MockMvc  mockMvc;

    @Test
    void testGetConfigList(){
        ConfigEntity configEntity = new ConfigEntity();
        try {
            mockMvc.perform(get("/api/sys/config/getConfigTypeList")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(configEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsertConfig(){
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setId("c12a15ea6803474c819b3b9ef5b0474c");
        configEntity.setConfigKey("年龄要大于");
        configEntity.setConfigValue("15");
        //configEntity.preInsert();
        try {
            mockMvc.perform(post("/api/sys/config/insertOrUpdateConfig")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(configEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindConfig(){
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setConfigKey("年龄要大于");
        try {
            mockMvc.perform(post("/api/sys/config/selectConfigListByPage")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(configEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
