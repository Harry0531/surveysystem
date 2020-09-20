package bit.ss.surveysystem.modules.sys.Dao;



import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;

import java.util.List;


public interface RoleDao {


    List<String> selectConfigTypeList();

    //关于字典操作
    List<Dict> selectConfigListByPage(ConfigEntity configEntity);
    int selectSearchCount(ConfigEntity configEntity);

    int insertConfig(ConfigEntity configEntity);

    int deleteConfigByIds(List<ConfigEntity> configEntity);
    int deleteConfigById(ConfigEntity configEntity);

    int updateConfig(ConfigEntity configEntity);

}