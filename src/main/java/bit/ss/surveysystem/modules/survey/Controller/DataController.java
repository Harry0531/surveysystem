package bit.ss.surveysystem.modules.survey.Controller;

import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.survey.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequestMapping("api/survey/data")
@Controller
public class DataController extends BaseApi {

    @Autowired
    DataService dataService;

    @RequestMapping(value = "getSurveyStatistics", method = RequestMethod.POST)
    @ResponseBody
    public Object getSurveyStatistics(@RequestBody SurveyEntity surveyEntity) throws Exception {
        try {
            Object result = dataService.getSurveyStatistics(surveyEntity);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }
}
