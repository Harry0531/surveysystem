package bit.ss.surveysystem.modules.sys.Controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
      * @Description 角色管理API
      * @author wh
      * @date 2019/8/12 13:08
      */
@RequestMapping("api/sys/config")
@Controller
public class RoleController extends BaseApi {

    @Autowired
    ConfigService configService;

    //后端插入字典类型用，不做前端实现
//    @RequestMapping(value = "insertDictType",method = RequestMethod.POST)
//    @ResponseBody
//    public Object insertDictType(@RequestBody DictType dictType)throws Exception {
//        if(dictService.insertDictType(dictType)){
//            return retMsg.Set(MsgType.SUCCESS);
//        }else
//            return retMsg.Set(MsgType.ERROR);
//    }

    @RequestMapping(value= "getConfigTypeList",method = RequestMethod.GET)
    @ResponseBody
    public Object getConfigTypeList()throws Exception {
        try {
            List<String> data = configService.selectConfigTypeList();
            return retMsg.Set(MsgType.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }


    @RequestMapping(value ="selectConfigListByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object selectConfigListByPage(@RequestBody ConfigEntity configEntity)throws Exception {
            try{
                Page<Dict> page = new Page<>();
                page.setResultList(configService.selectDictListByPage(configEntity));
                page.setTotal(configService.selectSearchCount(configEntity));
                return retMsg.Set(MsgType.SUCCESS,page);
            }catch (Exception e){
                e.printStackTrace();
                return  retMsg.Set(MsgType.ERROR);
            }
    }

    @RequestMapping(value = "insertOrUpdateConfig",method = RequestMethod.POST)
    @ResponseBody
    public Object insertOrUpdateConfig(@RequestBody ConfigEntity configEntity)throws Exception {
        if("".equals(configEntity.getId())||configEntity.getId()==null){
            if(configService.insertDict(configEntity))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }else{
            if(configService.updateDict(configEntity))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }

    }

    @RequestMapping(value="deleteConfigByIds",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteConfigByIds(@RequestBody List<ConfigEntity> configEntities)throws Exception {
        try{
            if(configService.deleteDictByIds(configEntities)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="deleteConfigById",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteConfigById(@RequestParam String configs)throws Exception {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setId(configs);
        try{
            if(configService.deleteDictById(configEntity)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

}
