package bit.ss.surveysystem.modules.survey.Entity.Ques;

import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Data
@Document
public class QuestionEntity {

    @Id
    @Field("id")
    private String id;//问题ID

    private String title;//题目

    private Integer index;//题目序号

    private QuestionType type;//题目类型

    @Field("required")
    private boolean required;//是否为必选题

    @Field("default_ans")
    private String defaultAns;//默认答案

    @Field("answer_list")
    private List<String>answerList;//答案列表

    @Field("front_option")
    private List<FrontOption>frontOptions;//前置选项列表

    @Field("skip_logic")
    private List<SkipLogic>skipLogices;//跳题逻辑

    private Boolean isPrivate;//是否为隐私项
    private String validation;//是否需要验证

    private SearchConditionType searchCondition;//大于、小于；等于、不等于、包含、不包含
    private String searchKey;//具体值


    public  void preInsert(){
        this.id = IdGen.uuid();
    }
}