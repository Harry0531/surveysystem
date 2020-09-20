package bit.ss.surveysystem.modules.sys.Dao;



import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Entity.RoleEntity;

import javax.management.relation.Role;
import java.util.List;


public interface RoleDao {



    //关于字典操作
    List<RoleEntity> selectRoleListByPage(RoleEntity roleEntity);
    int selectSearchCount(RoleEntity roleEntity);

    int insertRole(RoleEntity roleEntity);

    int deleteRoleByIds(List<RoleEntity> roleEntity);
    int deleteRoleById(RoleEntity roleEntity);

    int updateRole(RoleEntity roleEntity);

}
