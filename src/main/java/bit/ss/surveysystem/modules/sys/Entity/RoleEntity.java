package bit.ss.surveysystem.modules.sys.Entity;

import bit.ss.surveysystem.common.persistence.DataEntity;
import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;

@Data
public class RoleEntity extends DataEntity<RoleEntity> {

    private String id;
    private String code;
    private String name;

    private Integer delFlag;

    public void preInsert(){
        this.id = IdGen.uuid();
        this.delFlag = 0;
    }
}
