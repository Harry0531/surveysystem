package bit.ss.surveysystem.modules.survey.Service;

import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<SurveyEntity> selectSurveyByOwnerId(String ownerId){
        Query query = Query.query(Criteria.where("owner_id").is(ownerId));
        List<SurveyEntity> surveyEntities = mongoTemplate.find(query,SurveyEntity.class,"suit");
        return surveyEntities;
    }

    public List<SurveyEntity> selectSurveyByRespondentId(String id){
        //查询id
        Query query = Query.query(Criteria.where("respondent_id").is(id));
        List<AnsSurveyEntity> ansSurveyEntities = mongoTemplate.find(query,AnsSurveyEntity.class,"ans");
//        for(AnsSurveyEntity ansSurveyEntity:ansSurveyEntities){
//
//        }
        return null;
    }


    public int insertorUpdateSurvey(SurveyEntity surveyEntity){
        try {
            if(surveyEntity.getId()==null){
                surveyEntity.preInsert();
            }
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(surveyEntity.getId()));

            Update update =new Update();
            update.set("title",surveyEntity.getTitle());
            update.set("owner_id",surveyEntity.getOwnerId());
            update.set("description",surveyEntity.getDescription());
            update.set("enable",surveyEntity.getEnable());
            update.set("questions",surveyEntity.getQuestions());
            update.set("start_time",surveyEntity.getStartTime());
            update.set("end_time",surveyEntity.getEndTime());

            mongoTemplate.upsert(query,update,SurveyEntity.class,"suit");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public int deleteSurveyById(SurveyEntity surveyEntity){
        Query query = Query.query(Criteria.where("id").is(surveyEntity.getId()));
        mongoTemplate.remove(query,SurveyEntity.class);
        return 1;
    }



    public int insertorUpdateAnswer(AnsSurveyEntity ansSurveyEntity){
        try {
            if(ansSurveyEntity.getId()==null){
                ansSurveyEntity.preInsert();
            }
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ansSurveyEntity.getId()));

            Update update =new Update();
            update.set("respondent_id", ansSurveyEntity.getRespondentId());
            update.set("survey_id", ansSurveyEntity.getSurveyId());
            update.set("ans_list", ansSurveyEntity.getAnsList());

            mongoTemplate.upsert(query,update,SurveyEntity.class,"ans");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<AnsSurveyEntity> selectAnswerBySurveyIdAndpersonId(AnsSurveyEntity ansSurveyEntity){
            Query query = new Query();
            if(ansSurveyEntity.getId()!=null){
                query.addCriteria(Criteria.where("survey_id").is(ansSurveyEntity.getId()));
            }
           if(ansSurveyEntity.getRespondentId()!=null){
               query.addCriteria(Criteria.where("respondent_id").is(ansSurveyEntity.getRespondentId()));
           }

            return mongoTemplate.find(query,AnsSurveyEntity.class,"ans");
    }


}
