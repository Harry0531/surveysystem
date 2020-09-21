package bit.ss.surveysystem.modules.survey.Entity;

import bit.ss.surveysystem.common.utils.IdGen;
import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import lombok.Data;
import java.util.List;

@Data
public class SearchEntity {

    private String id;

    private UserInfoEntity userConditions;

    private SurveyEntity surveyEntity;

    private AnsSurveyEntity ansEntity;

    private List<Integer> sortConditions;

    public  void preInsert(){
        this.id = IdGen.uuid();
    }

}
