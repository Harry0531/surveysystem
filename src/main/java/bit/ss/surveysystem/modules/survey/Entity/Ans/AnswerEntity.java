package bit.ss.surveysystem.modules.survey.Entity.Ans;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class AnswerEntity {

    @Field("question_id")
    private String questionId;

    private String answer;

}
