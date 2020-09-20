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

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class SurveyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<SurveyEntity> selectSurveyByConditions(SurveyEntity surveyEntity){
        Query query = new Query();
        if(surveyEntity.getId()!=null&&!"".equals(surveyEntity.getId())){
            query.addCriteria(Criteria.where("id").is(surveyEntity.getId()));
        }
        if(surveyEntity.getOwnerId()!=null&&!"".equals(surveyEntity.getOwnerId())){
            query.addCriteria(Criteria.where("owner_id").is(surveyEntity.getOwnerId()));
        }
        if(surveyEntity.getTitle()!=null&&!"".equals(surveyEntity.getTitle())){
            query.addCriteria(Criteria.where("title").regex(Pattern.compile("^.*"+surveyEntity.getTitle()+".*$")));
        }
        if(surveyEntity.getDescription()!=null&&!"".equals(surveyEntity.getDescription())){
            query.addCriteria(Criteria.where("description").regex(Pattern.compile("^.*"+surveyEntity.getTitle()+".*$")));
        }

        return mongoTemplate.find(query,SurveyEntity.class,"suit");
    }



    public int postSurvey(SurveyEntity surveyEntity){
        Query query = Query.query(Criteria.where("id").is(surveyEntity.getId()));
        Update update =new Update();
        update.set("enable",surveyEntity.getEnable());
        if(surveyEntity.getEnable()){
            update.set("start_time",new Date());
        }else{
            update.set("end_time",new Date());
        }
        mongoTemplate.updateMulti(query,update,"suit");
        return 1;
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

    public int copySurvey(SurveyEntity surveyEntity){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(surveyEntity.getId()));
        SurveyEntity surveyEntity_old = mongoTemplate.findOne(query,SurveyEntity.class,"suit");
        if(surveyEntity_old!=null){
            surveyEntity_old.preInsert();
            mongoTemplate.save(surveyEntity_old);
            return 1;
        }
        return -1;
    }

    public int deleteSurveyById(SurveyEntity surveyEntity){
        Query query = Query.query(Criteria.where("id").is(surveyEntity.getId()));
        mongoTemplate.remove(query,SurveyEntity.class,"suit");
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

    public List<AnsSurveyEntity> selectAnswerByConditions(AnsSurveyEntity ansSurveyEntity){
            Query query = new Query();

            if(ansSurveyEntity.getId()!=null &&!"".equals(ansSurveyEntity.getId())){
                query.addCriteria(Criteria.where("id").is(ansSurveyEntity.getId()));
            }
           if(ansSurveyEntity.getRespondentId()!=null&&!"".equals(ansSurveyEntity.getRespondentId())){
               query.addCriteria(Criteria.where("respondent_id").is(ansSurveyEntity.getRespondentId()));
           }
            if(ansSurveyEntity.getSurveyId()!=null&&!"".equals(ansSurveyEntity.getSurveyId())){
                query.addCriteria(Criteria.where("survey_id").is(ansSurveyEntity.getSurveyId()));
            }
            return mongoTemplate.find(query,AnsSurveyEntity.class,"ans");
    }

    public int deleteAnswer(AnsSurveyEntity ansSurveyEntity){
        Query query = Query.query(Criteria.where("id").is(ansSurveyEntity.getId()));
        mongoTemplate.remove(query,AnsSurveyEntity.class,"ans");
        return 1;
    }

}
