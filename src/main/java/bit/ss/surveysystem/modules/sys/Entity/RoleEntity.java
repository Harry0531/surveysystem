package bit.ss.surveysystem.modules.sys.Entity;

import lombok.Data;

@Data
public class RoleEntity {

    private String id;
    private String code;
    private String name;

    private Integer delFlag;
}
