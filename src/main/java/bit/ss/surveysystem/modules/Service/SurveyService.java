package bit.ss.surveysystem.modules.Service;

import bit.ss.surveysystem.modules.entity.SurveyEntity;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<SurveyEntity> selectSurveyByOwnerId(String ownerId){
        //Query query = Query.query(Criteria.where(""))
        List<SurveyEntity> surveyEntities = mongoTemplate.findAll(SurveyEntity.class);
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
