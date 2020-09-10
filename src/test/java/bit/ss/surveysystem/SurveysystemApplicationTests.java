package bit.ss.surveysystem;

import bit.ss.surveysystem.modules.survey.Service.SurveyService;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SurveysystemApplicationTests {

    @Autowired
    SurveyService surveyService;
    @Test
    void contextLoads() {
    }

    @Test
    void testSurveyFind(){
        List<SurveyEntity>surveyEntities = surveyService.selectSurveyByOwnerId("123");
        System.out.println(surveyEntities);
    }

    @Test
    void testSurveyInsert(){
        List<QuestionEntity> questionEntities = new ArrayList<>();
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTitle("第一题");
        questionEntity.setId(new ObjectId().toString());

        questionEntity.setIndex(2);
        questionEntities.add(questionEntity);


        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setEnable(1);
        surveyEntity.setQuestions(questionEntities);
        surveyEntity.setOwnerId("!23213");
        surveyService.insertSurvey(surveyEntity);
    }
}
