package bit.ss.surveysystem;

import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionType;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.survey.Service.SurveyService;
import org.assertj.core.util.Lists;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SurveyTest {

    @Autowired
    SurveyService surveyService;

    @Test
    void testInsertOrUpdateSurvey(){
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setOwnerId("2bcdd55957804be38f99e770fbfd8a20");
        surveyEntity.setTitle("测试问卷");
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

        surveyService.insertorUpdateSurvey(surveyEntity);
    }
}
