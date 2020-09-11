package bit.ss.surveysystem.modules.survey.Service;

import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<SurveyEntity> selectSurveyByOwnerId(String ownerId){
        //Query query = Query.query(Criteria.where(""))
        List<SurveyEntity> surveyEntities = mongoTemplate.findAll(SurveyEntity.class);
        Criteria opration = Criteria.where("dasdsa");
        opration.and("dsa");
        return surveyEntities;
    }

    public int insertSurvey(SurveyEntity surveyEntity){
        try {
            mongoTemplate.save(surveyEntity);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


    }
}
