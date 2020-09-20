package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.ConfigDao;
import bit.ss.surveysystem.modules.sys.Dao.RoleDao;
import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public List<RoleEntity> selectRoleListByPage(RoleEntity roleEntity){
        return roleDao.selectRoleListByPage(roleEntity);
    }
    public int selectSearchCount(RoleEntity roleEntity) {
        return roleDao.selectSearchCount(roleEntity);
    }

    public  boolean insertRole(RoleEntity roleEntity){
        roleEntity.preInsert();
        return roleDao.insertRole(roleEntity) == 1;
    }
    public boolean deleteRoleByIds(List<RoleEntity> roleEntity){
        return roleEntity.size()==0 || roleDao.deleteRoleByIds(roleEntity)==roleEntity.size();
    }
    public boolean deleteRoleById(RoleEntity roleEntity){
        return roleDao.deleteRoleById(roleEntity)==1;
    }

    public  boolean updateRole(RoleEntity roleEntity){
        return roleDao.updateRole(roleEntity) == 1;
    }


}
