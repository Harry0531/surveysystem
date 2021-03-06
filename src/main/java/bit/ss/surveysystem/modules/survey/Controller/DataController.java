package bit.ss.surveysystem.modules.survey.Controller;

import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.SearchEntity;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.survey.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("api/survey/data")
@Controller
public class DataController extends BaseApi {

    @Autowired
    DataService dataService;

    @RequestMapping(value = "getSurveyStatistics", method = RequestMethod.POST)
    @ResponseBody
    public Object getSurveyStatistics(@RequestBody SearchEntity searchEntity) throws Exception {
        try {
            Object result = dataService.getSurveyStatistics(searchEntity);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value = "getAnsListByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Object getSurveyTableAns(@RequestBody SearchEntity searchEntity) throws Exception {
        try {
            Object result = dataService.getAnsListByConditions(searchEntity);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value = "getAnsListByPerson", method = RequestMethod.POST)
    @ResponseBody
    public Object getSurveyTableAns(@RequestBody AnsSurveyEntity userId) throws Exception {
        try {
            Object result = dataService.getAnsListByPerson(userId);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

    @RequestMapping(value = "getExportId",method = RequestMethod.POST)
    @ResponseBody
    public Object exportSurvey(
            @RequestBody SearchEntity searchEntity
    ) throws Exception {
        try {
            Object result = dataService.getExportId(searchEntity);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }


    @RequestMapping(value = "exportSurvey")
    @ResponseBody
    public Object exportSurvey(
            @RequestParam String ExcelId,
            HttpServletResponse response
    ) throws Exception {
        try {
            Object result = dataService.exportSurvey(ExcelId,response);
            return retMsg.Set(MsgType.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR);
        }
    }

}
