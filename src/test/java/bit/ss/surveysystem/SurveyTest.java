package bit.ss.surveysystem;

import bit.ss.surveysystem.modules.survey.Controller.SurveyController;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionType;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.survey.Service.SurveyService;
import bit.ss.surveysystem.modules.sys.Controller.UserController;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.assertj.core.util.Lists;
import org.bson.types.ObjectId;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SurveyTest {


    @Autowired
    SurveyService surveyService;

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    private MockMvc mockMvc;




    @Test
    void testInsertOrUpdateSurvey(){
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setId("05b65a4ee1644f32b2bbfab0f3861620");
        surveyEntity.setOwnerId("2bcdd55957804be38f99e770fbfd8a20");
        surveyEntity.setTitle("修改是否成功");
        surveyEntity.setDescription("这是一个测试问卷");
        surveyEntity.setEnable(0);

        List<QuestionEntity> questionEntities = new ArrayList<>();

        QuestionEntity q1 = new QuestionEntity();
        q1.setId(new ObjectId().toString());
        q1.setTitle("你是谁");
        q1.setIndex(1);
        q1.setType(QuestionType.FillBlank);
        q1.setIsRequired(1);
        q1.setDefaultAns("王日天");
        questionEntities.add(q1);


        QuestionEntity q2 = new QuestionEntity();
        q2.setId(new ObjectId().toString());
        q2.setTitle("你来自哪个年级");
        q2.setIndex(2);
        q2.setType(QuestionType.MultipleChoice);
        q2.setIsRequired(1);
        q2.setAnswerList(Lists.newArrayList("大一", "大二", "大三","大四"));
        q2.setDefaultAns("大一");
        questionEntities.add(q2);

        surveyEntity.setQuestions(questionEntities);


        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/survey/insertOrUpdateSurvey")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(surveyEntity).getBytes()))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindSurvey(){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("aeacba5d546d415ca546ad318be52cb9"));
        SurveyEntity surveyEntity = mongoTemplate.findOne(query,SurveyEntity.class);
        System.out.println(surveyEntity);
    }


    @Test
    void testGetSurveyByConditions(){
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setTitle("测试问卷");
        try {
            mockMvc.perform(post("/api/survey/getSurveyByConditions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(surveyEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPostSurvey(){
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId("05b65a4ee1644f32b2bbfab0f3861620");
        try {
            mockMvc.perform(post("/api/survey/postSurvey")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(surveyEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteSurvey(){
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId("05b65a4ee1644f32b2bbfab0f3861620");
        try {
            mockMvc.perform(post("/api/survey/deleteSurvey")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(surveyEntity)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
