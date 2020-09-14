package bit.ss.surveysystem.modules.sys.Entity;

import bit.ss.surveysystem.common.persistence.DataEntity;
import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;

@Data
public class ConfigEntity extends DataEntity<ConfigEntity> {


    public String id;
    //配置键
    public String configKey;
    //配置值
    public String configValue;

    //备注
    public String remark;

    //排序等级
    public Integer sort;

    public Integer delFlag;

    public void preInsert(){
        this.id = IdGen.uuid();
        this.delFlag = 0;
    }

}
