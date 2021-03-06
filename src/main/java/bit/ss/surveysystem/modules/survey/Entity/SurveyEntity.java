package bit.ss.surveysystem.modules.survey.Entity;

import bit.ss.surveysystem.common.utils.IdGen;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.FieldResult;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data 
@Document(collection = "suit")
public class SurveyEntity {

    @Field("id")
    private String id;

    @Field("owner_id")
    private String ownerId;//创建人ID

    private String title;//问卷标题

    private String description;//问卷描述

    private Boolean enable;//问卷是否启用

    private List<QuestionEntity> questions;//问题列表



    @Field("start_time")
    private Date startTime;//问卷开始填写时间
    @Field("end_time")
    private Date endTime;//问卷结束填写时间

    public  void preInsert(){
        this.id = IdGen.uuid();
    }

    public  int getQuestionIndexById(String questionId,Integer extraNum){
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(questionId)){
                return i+extraNum;
            }
        }
        return -1;
    }
}
