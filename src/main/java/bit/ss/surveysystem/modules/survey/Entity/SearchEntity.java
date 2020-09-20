package bit.ss.surveysystem.modules.survey.Entity;

import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import lombok.Data;
import java.util.List;

@Data
public class SearchEntity {

    private UserInfoEntity userConditions;

    private SurveyEntity surveyEntity;

}
