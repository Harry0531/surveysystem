package bit.ss.surveysystem.modules.sys.Controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Entity.RoleEntity;
import bit.ss.surveysystem.modules.sys.Service.ConfigService;
import bit.ss.surveysystem.modules.sys.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
      * @Description 角色管理API
      * @author wh
      * @date 2019/8/12 13:08
      */
@RequestMapping("api/sys/role")
@Controller
public class RoleController extends BaseApi {

    @Autowired
    RoleService roleService;



    @RequestMapping(value ="selectRoleListByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object selectRoleListByPage(@RequestBody RoleEntity roleEntity)throws Exception {
            try{
                Page<RoleEntity> page = new Page<>();
                page.setResultList(roleService.selectRoleListByPage(roleEntity));
                page.setTotal(roleService.selectSearchCount(roleEntity));
                return retMsg.Set(MsgType.SUCCESS,page);
            }catch (Exception e){
                e.printStackTrace();
                return  retMsg.Set(MsgType.ERROR);
            }
    }

    @RequestMapping(value = "insertOrUpdateRole",method = RequestMethod.POST)
    @ResponseBody
    public Object insertOrUpdateRole(@RequestBody RoleEntity roleEntity)throws Exception {
        if("".equals(roleEntity.getId())||roleEntity.getId()==null){
            if(roleService.insertRole(roleEntity))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }else{
            if(roleService.updateRole(roleEntity))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }

    }

    @RequestMapping(value="deleteRoleByIds",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteRoleByIds(@RequestBody List<RoleEntity> roleEntities)throws Exception {
        try{
            if(roleService.deleteRoleByIds(roleEntities)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="deleteRoleById",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteRoleById(@RequestBody RoleEntity role)throws Exception {
        try{
            if(roleService.deleteRoleById(role)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

}
