package bit.ss.surveysystem.modules.survey.Controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
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

}
