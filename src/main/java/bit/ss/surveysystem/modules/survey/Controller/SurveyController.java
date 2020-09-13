package bit.ss.surveysystem.modules.survey.Controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.survey.Service.SurveyService;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import bit.ss.surveysystem.modules.sys.Service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/survey/")
@Controller
public class SurveyController extends BaseApi {

    @Autowired
    SurveyService surveyService;

    @RequestMapping(value="postSurvey",method = RequestMethod.POST)
    @ResponseBody
    public Object postSurvey(@RequestBody SurveyEntity surveyEntity)throws Exception {
        try{
            surveyService.postSurvey(surveyEntity);
            return retMsg.Set(MsgType.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }


    @RequestMapping(value="insertOrUpdateSurvey",method = RequestMethod.POST)
    @ResponseBody
    public Object createSurvey(@RequestBody SurveyEntity surveyEntity)throws Exception {
        try{
            surveyService.insertorUpdateSurvey(surveyEntity);
            return retMsg.Set(MsgType.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="getSurveyByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Object getSurveyById(@RequestBody SurveyEntity surveyEntity)throws Exception {
        try{
            List<SurveyEntity> surveyEntities = surveyService.selectSurveyByCondition(surveyEntity);
            return retMsg.Set(MsgType.SUCCESS,surveyEntities);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="copySurvey",method = RequestMethod.POST)
    @ResponseBody
    public Object copySurvey(@RequestBody SurveyEntity surveyEntity)throws Exception {
        try{
            if (surveyService.copySurvey(surveyEntity)==1){
                return retMsg.Set(MsgType.SUCCESS);
            }else{
                return retMsg.Set(MsgType.WARNING,"未更新任何数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="deleteSurvey",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSurvey(@RequestBody SurveyEntity surveyEntity)throws Exception {
        try{
            if (surveyService.deleteSurveyById(surveyEntity)==1){
                return retMsg.Set(MsgType.SUCCESS);
            }else{
                return retMsg.Set(MsgType.WARNING,"未更新任何数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value="insertOrUpdateAnswer",method = RequestMethod.POST)
    @ResponseBody
    public Object insertOrUpdateAnswer(@RequestBody AnsSurveyEntity ansSurveyEntity)throws Exception {
        try{

            return retMsg.Set(MsgType.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }



}
