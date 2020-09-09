package bit.ss.surveysystem.modules.entity;

import bit.ss.surveysystem.modules.entity.Ques.QuestionEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "suit")
public class SurveyEntity {

    @Id
    @Field("id")
    private String id;

    @Field("owner_id")
    private String ownerId;

    private Integer enable;

    private List<QuestionEntity> questions;
}
