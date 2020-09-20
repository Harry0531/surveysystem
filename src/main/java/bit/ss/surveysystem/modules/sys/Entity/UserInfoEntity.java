package bit.ss.surveysystem.modules.sys.Entity;

import bit.ss.surveysystem.common.persistence.DataEntity;

import lombok.Data;

@Data
public class UserInfoEntity extends DataEntity<UserInfoEntity> {

    private String id;
    private String admissionNumber;
    private String name;
    private String contact;//联系方式
    private String alternateContact;//备用联系方式
    private Integer type;//身份
    private String studentName;

    private String province;
    private String city;
    private String address;
    private String highSchool;
    private String major;//专业
    private Integer year;//年份

    private String fractionalSegment;//分数段
    private String rankingSection;//名次段
    private Integer qiangjiPlan;//强计计划
    private Integer tiqianpi;//提前批
    private Integer signed;//已签约

    private Integer model;//高考模式
    private String subject;//高考科目


}
