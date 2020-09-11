package bit.ss.surveysystem.modules.sys.Entity;

import bit.ss.surveysystem.common.persistence.DataEntity;
import bit.ss.surveysystem.common.utils.IdGen;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserEntity extends DataEntity<UserEntity> {
    private String  id;
    private String userName;
    private String password;
    private String email;
    private Date registerTime;
    private Date lastLoginTime;
    private String role;

    public void preInsert() {
        this.id = IdGen.uuid();
        this.registerTime = new Date();
        this.lastLoginTime = this.registerTime;
    }
}
