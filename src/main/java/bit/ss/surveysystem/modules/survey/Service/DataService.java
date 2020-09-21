package bit.ss.surveysystem.modules.survey.Service;

import bit.ss.surveysystem.modules.survey.Entity.Ans.AnsSurveyEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ans.AnswerEntity;
import bit.ss.surveysystem.modules.survey.Entity.Ques.QuestionEntity;
import bit.ss.surveysystem.modules.survey.Entity.SearchEntity;
import bit.ss.surveysystem.modules.survey.Entity.SurveyEntity;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import bit.ss.surveysystem.modules.sys.Service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Service
public class DataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    SurveyService surveyService;

    @Autowired
    UserService userService;

    public List<SearchEntity> getAnsListByConditions(SearchEntity searchEntity){
        List<SurveyEntity> surveyEntities=surveyService.selectSurveyByConditions(searchEntity.getSurveyEntity());
        if(surveyEntities.size()==0){
            return null;
        }
        SurveyEntity surveyEntity =surveyEntities.get(0);
        //获取所有该问卷的答卷
        AnsSurveyEntity ansSurveyEntity = new AnsSurveyEntity();
        ansSurveyEntity.setSurveyId(surveyEntity.getId());
        List<AnsSurveyEntity> oldAnsList = surveyService.selectAnswerByConditions(ansSurveyEntity);
        List<AnsSurveyEntity> ansSurveyEntityList = new ArrayList<>();
        //对答卷进行筛选

        //用户筛选
        List<UserInfoEntity> userList = userService.getUserInfoByConditions(searchEntity.getUserConditions());
        List<String> userIdList;
        userIdList = new ArrayList<>();
        for(UserInfoEntity userInfoEntity:userList){
            userIdList.add(userInfoEntity.getAdmissionNumber());
        }
        for(AnsSurveyEntity ans:oldAnsList){
            if(userIdList.contains(ans.getRespondentId())){
                ansSurveyEntityList.add(ans);
            }
        }
        //题目条件筛选
        if(searchEntity.getSurveyEntity().getQuestions()!=null){
            for(QuestionEntity ques:searchEntity.getSurveyEntity().getQuestions()){
                oldAnsList.clear();
                oldAnsList.addAll(ansSurveyEntityList);
                ansSurveyEntityList.clear();
                switch (ques.getSearchCondition()){
                    case LESS:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(Integer.parseInt(ans.getAnswer())<Integer.parseInt(ques.getSearchKey())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case GREATER:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(Integer.parseInt(ans.getAnswer())>Integer.parseInt(ques.getSearchKey())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case EQUAL:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(ques.getSearchKey().equals(ans.getAnswer())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case NOT_EQUAL:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(!ques.getSearchKey().equals(ans.getAnswer())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case CONTAIN:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(ans.getAnswer().contains(ques.getSearchKey())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case NOT_CONTAIN:
                        for(AnsSurveyEntity ansSurvey:oldAnsList){
                            //每份答卷
                            for (AnswerEntity ans:ansSurvey.getAnsList()){
                                if(ans.getQuestionId().equals(ques.getId())){
                                    if(!ans.getAnswer().contains(ques.getSearchKey())){
                                        ansSurveyEntityList.add(ansSurvey);
                                    }else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        List<SearchEntity> result = new ArrayList<>();
        SearchEntity tmpSearch ;
        for(AnsSurveyEntity ansSurveyEntity1:ansSurveyEntityList){
            tmpSearch = new SearchEntity();
            tmpSearch.setAnsEntity(ansSurveyEntity1);

            for (UserInfoEntity user:userList){
                if(user.getAdmissionNumber().equals(ansSurveyEntity1.getRespondentId())){
                    tmpSearch.setUserConditions(user);
                    break;
                }
            }
            result.add(tmpSearch);
        }

        //todo 排序
        if(searchEntity.getSortConditions()!=null){
            for(Integer sort:searchEntity.getSortConditions()){
                //正序
                int index = Math.abs(sort)-1;
                String type = surveyEntity.getQuestions().get(index).getValidation();
                if("phone".equals(type)||"integer".equals(type)||"grade".equals(type)||"rank".equals(type)){
                    result.sort(new Comparator<SearchEntity>() {
                        @Override
                        public int compare(SearchEntity o1, SearchEntity o2) {
                            String a1="",a2="";
                            String quesId = surveyEntity.getQuestions().get(index).getId();
                            for(AnswerEntity answerEntity:o1.getAnsEntity().getAnsList()){
                                if(quesId.equals(answerEntity.getQuestionId())){
                                    a1 = answerEntity.getAnswer();
                                    break;
                                }
                            }
                            for(AnswerEntity answerEntity:o2.getAnsEntity().getAnsList()){
                                if(quesId.equals(answerEntity.getQuestionId())){
                                    a2 = answerEntity.getAnswer();
                                    break;
                                }
                            }
                            return sort*(Integer.parseInt(a1)-Integer.parseInt(a2));
                        }
                    });
                }
                else{
                    result.sort(new Comparator<SearchEntity>() {
                        @Override
                        public int compare(SearchEntity o1, SearchEntity o2) {
                            String a1="",a2="";
                            String quesId = surveyEntity.getQuestions().get(index).getId();
                            for(AnswerEntity answerEntity:o1.getAnsEntity().getAnsList()){
                                if(quesId.equals(answerEntity.getQuestionId())){
                                    a1 = answerEntity.getAnswer();
                                    break;
                                }
                            }
                            for(AnswerEntity answerEntity:o2.getAnsEntity().getAnsList()){
                                if(quesId.equals(answerEntity.getQuestionId())){
                                    a2 = answerEntity.getAnswer();
                                    break;
                                }
                            }
                            return sort*(a1.compareTo(a2));
                        }
                    });
                }
            }
        }


        return result;
    }

    public Object getSurveyStatistics(SearchEntity searchEntity){
        List<SurveyEntity> surveyEntities=surveyService.selectSurveyByConditions(searchEntity.getSurveyEntity());
        if(surveyEntities.size()==0){
            return null;
        }
        SurveyEntity surveyEntity =surveyEntities.get(0);
        List<SearchEntity> searchEntities = getAnsListByConditions(searchEntity);
        List<AnsSurveyEntity> ansSurveyEntityList = new ArrayList<>();
        for (SearchEntity searchEntity1:searchEntities){
            ansSurveyEntityList.add(searchEntity1.getAnsEntity());
        }
        //遍历每个题目进行统计
        HashMap<Integer,Object> result = new HashMap<>();
        for(QuestionEntity ques:surveyEntity.getQuestions()){
            HashMap<String,Object> singleResult = new HashMap<>();
            //汇总所有答案
            HashMap<String,Object> summaryAns = new HashMap<>();
            for(AnsSurveyEntity ansList:ansSurveyEntityList){
                //anslist 每个人的答案列表
                for(AnswerEntity ans:ansList.getAnsList()){
                    //ans 每个人的每道题答案
                    if(ans.getQuestionId().equals(ques.getId())){
                        summaryAns.put(ansList.getRespondentId(),ans.getAnswer());
                    }
                }
            }
            singleResult.put("summary",summaryAns);
            //答案统计
            HashMap<String,Object> statisticsAns = new HashMap<>();
            switch (ques.getType()){
                case FILL_BLANK:
                case ORDER:
                     break;
                case MULTIPLE:
                case SINGLE:
                     int totalCnt = ansSurveyEntityList.size();
                     List<Integer> ansCnt = new ArrayList<>();
                     for(int i =0;i<ques.getAnswerList().size();i++) ansCnt.add(0);
                     for(AnsSurveyEntity ansList:ansSurveyEntityList){
                         //anslist 每个人的答案列表
                         for(AnswerEntity ans:ansList.getAnsList()){
                             //ans 每个人的每道题答案
                             if(ans.getQuestionId().equals(ques.getId())){
                                 int index = ques.getAnswerList().indexOf(ans.getAnswer());
                                 ansCnt.set(index,ansCnt.get(index)+1);
                             }
                         }
                     }
                     for(int i=0;i<ques.getAnswerList().size();i++){
                         statisticsAns.put(ques.getAnswerList().get(i),ansCnt.get(i));
                     }
                      break;
               default:
                    break;
           }
            singleResult.put("statistics",statisticsAns);
            singleResult.put("question",ques);
            result.put(ques.getIndex(),singleResult);
        }


        return result;
    }

    public String getExportId(SearchEntity searchEntity){
        searchEntity.preInsert();
        mongoTemplate.save(searchEntity,"excel");
        return searchEntity.getId();
    }

    public Object exportSurvey(String ExcelId, HttpServletResponse response){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(ExcelId));
        SearchEntity searchEntity = mongoTemplate.findOne(query,SearchEntity.class,"excel");
        SurveyEntity surveyEntity = surveyService.selectSurveyByConditions(searchEntity.getSurveyEntity()).get(0);
        List<SearchEntity> searchEntities = getAnsListByConditions(searchEntity);
        List<AnsSurveyEntity> ansSurveyEntityList = new ArrayList<>();
        for (SearchEntity searchEntity1:searchEntities){
            ansSurveyEntityList.add(searchEntity1.getAnsEntity());
        }

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(surveyEntity.getTitle());
        XSSFCellStyle style = wb.createCellStyle();

        // 设置居中样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置合计样式
        XSSFCellStyle styleLeft = wb.createCellStyle();
        Font font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
        font.setFontHeightInPoints((short) 12); //设置字体大小
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        XSSFCellStyle styleRight = wb.createCellStyle();
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        int colIndex = 0,rowIndex=1;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);



        UserInfoEntity userInfoEntity=new UserInfoEntity();
        //设置表头
        List<String> extraTitle = new ArrayList<String>(Arrays.asList("序号","姓名","高中"));
        List<String> title = new ArrayList<>(extraTitle);
        List<Integer> excelHeaderWidth = new ArrayList<Integer>(Arrays.asList(50,50,50));
        for(QuestionEntity ques:surveyEntity.getQuestions()){
            title.add(ques.getTitle());
            excelHeaderWidth.add(ques.getTitle().length()*10+20);
        }

        for (int i = 0; i < excelHeaderWidth.size(); i++) {
            sheet.setColumnWidth(i,50*excelHeaderWidth.get(i));
        }
        //设置表头
        Row dataRow=sheet.createRow(0);
        Cell dataCell;
        for(String tit:title){
            Cell cell2 = dataRow.createCell(colIndex);
            cell2.setCellValue(tit);
            cell2.setCellStyle(dataStyle);
            colIndex++;
        }

        for(int i = 0;i<ansSurveyEntityList.size();i++){
            dataRow = sheet.createRow(rowIndex+i);
            colIndex = 0;
            userInfoEntity = new UserInfoEntity();
            userInfoEntity.setAdmissionNumber(ansSurveyEntityList.get(i).getRespondentId());
            userInfoEntity = userService.getUserInfoByConditions(userInfoEntity).get(0);
            //前几列输入用户信息
            for(int j =0;j<extraTitle.size();j++){
                dataCell = dataRow.createCell(colIndex+j);
                switch (j){
                    case 0:
                        dataCell.setCellValue(i+1);
                        break;
                    case 1:
                        dataCell.setCellValue(userInfoEntity.getName());
                        break;
                    case 2:
                        dataCell.setCellValue(userInfoEntity.getHighSchool());
                        break;
                    default:
                        break;
                }
                dataCell.setCellStyle(dataStyle);
            }
            //后几列是问卷答案
            for(AnswerEntity ans:ansSurveyEntityList.get(i).getAnsList()){
                dataCell = dataRow.createCell(surveyEntity.getQuestionIndexById(ans.getQuestionId(),extraTitle.size()));
                dataCell.setCellValue(ans.getAnswer());
                dataCell.setCellStyle(dataStyle);
            }

        }


        response.setContentType("application/vnd.ms-excel");
        //注意此处文件名称如果想使用中文的话，要转码new String( "中文".getBytes( "gb2312" ), "ISO8859-1" )
        try {
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String((surveyEntity.getTitle()+"问卷统计结果").getBytes("gb2312"), "ISO8859-1")
                            + ".xlsx");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
