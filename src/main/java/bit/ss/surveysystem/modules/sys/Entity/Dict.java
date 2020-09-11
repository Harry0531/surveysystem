package bit.ss.surveysystem.modules.sys.Entity;

import bit.ss.surveysystem.common.persistence.DataEntity;

import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;

@Data
public class Dict extends DataEntity<Dict> {


    public String id;
    //字典中文属性
    public String dicProperty;
    //字典值
    public String dicValue;

    //备注
    public String remark;

    //父字典名字
    public String father;
    //排序等级
    public Integer sort;

    public Integer delFlag;

    public void preInsert(){
        this.id = IdGen.uuid();
        this.delFlag = 0;
    }

}
