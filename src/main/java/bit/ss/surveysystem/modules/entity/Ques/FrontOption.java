package bit.ss.surveysystem.modules.entity.Ques;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

//问题的前置选项
@Data
public class FrontOption {
    @Field("question_id")
    private String questionId;

    @Field("question_answer")
    private String questionAnswer;


}
