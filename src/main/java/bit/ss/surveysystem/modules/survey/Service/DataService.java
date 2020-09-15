package bit.ss.surveysystem.modules.survey.Service;

import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ans.AnswerEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class DataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    SurveyService surveyService;

    public Object getSurveyStatistics(SurveyEntity surveyEntity){
        //获取问卷信息
        surveyEntity = surveyService.selectSurveyByConditions(surveyEntity).get(0);
        //获取所有该问卷的答卷
        AnsSurveyEntity ansSurveyEntity = new AnsSurveyEntity();
        ansSurveyEntity.setSurveyId(surveyEntity.getId());
        List<AnsSurveyEntity> ansSurveyEntityList = surveyService.selectAnswerByConditions(ansSurveyEntity);
        //遍历每个题目
        HashMap<Integer,Object> result = new HashMap<>();
        for(QuestionEntity ques:surveyEntity.getQuestions()){
            HashMap<String,Object> singleResult = new HashMap<>();
            //汇总所有答案
            HashMap<String,Object> summaryAns = new HashMap<>();
            for(AnsSurveyEntity ansList:ansSurveyEntityList){
                //anslist 每个人的答案列表
                for(AnswerEntity ans:ansList.getAnsList()){
                    //ans 每个人的每道题答案
                    if(ans.getQuestionId().equals(ques.getId())){
                        summaryAns.put(ansList.getRespondentId(),ans.getAnswer());
                    }
                }
            }
            singleResult.put("summary",summaryAns);
            //答案统计
            HashMap<String,Object> statisticsAns = new HashMap<>();
            switch (ques.getType()){
                case FillBlank:
                case Sort:
                     break;
                 case MultipleChoice:
                 case SingleChoice:
                     int totalCnt = ansSurveyEntityList.size();
                     List<Integer> ansCnt = new ArrayList<>();
                     for(int i =0;i<ques.getAnswerList().size();i++) ansCnt.add(0);
                     for(AnsSurveyEntity ansList:ansSurveyEntityList){
                         //anslist 每个人的答案列表
                         for(AnswerEntity ans:ansList.getAnsList()){
                             //ans 每个人的每道题答案
                             if(ans.getQuestionId().equals(ques.getId())){
                                 int index = ques.getAnswerList().indexOf(ans.getAnswer());
                                 ansCnt.set(index,ansCnt.get(index)+1);
                             }
                         }
                     }
                     for(int i=0;i<ques.getAnswerList().size();i++){
                         statisticsAns.put(ques.getAnswerList().get(i),ansCnt.get(i));
                     }
                      break;
               default:
                    break;
           }
            singleResult.put("statistics",statisticsAns);
            result.put(ques.getIndex(),singleResult);
        }


        return result;
    }



}
