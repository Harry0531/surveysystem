package bit.ss.surveysystem.modules.survey.Entity.Ques;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

//问题的前置选项2
@Data
public class FrontOption {
    @Field("question_id")
    private String questionId;

    @Field("question_answer")
    private String questionAnswer;


}
