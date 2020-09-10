package bit.ss.surveysystem.modules.survey.Entity.Ques;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class SkipLogic {

    @Field("skip_option")
    private String skipOption;

    @Field("skip_to")
    private String skipTo;
}
