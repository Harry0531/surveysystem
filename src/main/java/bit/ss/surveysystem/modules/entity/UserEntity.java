package bit.ss.surveysystem.modules.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private int id;
    private String userName;
    private String password;
    private Date registerTime;
    private Date lastLoginTime;

    public void preInsert(int id) {
        this.id = id;
        this.registerTime = new Date();
        this.lastLoginTime = this.registerTime;
    }
}
