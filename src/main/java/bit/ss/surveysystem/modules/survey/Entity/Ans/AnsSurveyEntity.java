package bit.ss.surveysystem.modules.survey.Entity.Ans;


import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class AnsSurveyEntity {

    @Field("id")
    private  String id;

    @Field("respondent_id")
    private String respondentId;//答题人Id
    @Field("survey_id")
    private String surveyId;//问卷Id


    @Field("ans_list")
    private List<AnswerEntity> ansList;

    public Boolean star;
    public void preInsert(){
        this.id = IdGen.uuid();
    }
}
