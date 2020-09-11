package bit.ss.surveysystem.modules.sys.Controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
      * @Description 字典管理api
      * @author wh
      * @date 2019/8/12 13:08
      */
@RequestMapping("api/sys/dict")
@Controller
public class DictController extends BaseApi {

    @Autowired
    DictService dictService;

    //后端插入字典类型用，不做前端实现
//    @RequestMapping(value = "insertDictType",method = RequestMethod.POST)
//    @ResponseBody
//    public Object insertDictType(@RequestBody DictType dictType)throws Exception {
//        if(dictService.insertDictType(dictType)){
//            return retMsg.Set(MsgType.SUCCESS);
//        }else
//            return retMsg.Set(MsgType.ERROR);
//    }
    //获取字典类型序列
    @RequestMapping(value= "getDictTypeList",method = RequestMethod.GET)
    @ResponseBody
    public Object getDictTypeList()throws Exception {
        try {
            List<String> data = dictService.selectDictTypeList();
            return retMsg.Set(MsgType.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }


    @RequestMapping(value ="selectDictListByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object selectDictListByPage(@RequestBody Dict dict)throws Exception {
            try{
                Page<Dict> page = new Page<>();
                page.setResultList(dictService.selectDictListByPage(dict));
                page.setTotal(dictService.selectSearchCount(dict));
                return retMsg.Set(MsgType.SUCCESS,page);
            }catch (Exception e){
                e.printStackTrace();
                return  retMsg.Set(MsgType.ERROR);
            }
    }

    @RequestMapping(value = "insertOrUpdateDict",method = RequestMethod.POST)
    @ResponseBody
    public Object insertOrUpdateDict(@RequestBody Dict dict)throws Exception {
        if(dict.getId().equals("")||dict.getId()==null){
            if(dictService.insertDict(dict))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }else{
            if(dictService.updateDict(dict))
                return retMsg.Set(MsgType.SUCCESS);
            else return retMsg.Set(MsgType.ERROR);
        }

    }

    @RequestMapping(value="deleteDictByIds",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteDictByIds(@RequestBody List<Dict> dicts)throws Exception {
        try{
            if(dictService.deleteDictByIds(dicts)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="deleteDictById",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteDictById(@RequestParam String dicts)throws Exception {
        Dict dict  = new Dict();
        dict.setId(dicts);
        try{
            if(dictService.deleteDictById(dict)){
                return retMsg.Set(MsgType.SUCCESS);
            }else
                return retMsg.Set(MsgType.ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

}
