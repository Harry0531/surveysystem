package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.ConfigDao;
import bit.ss.surveysystem.modules.sys.Dao.DictDao;
import bit.ss.surveysystem.modules.sys.Entity.ConfigEntity;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

    @Autowired
    ConfigDao configDao;
    //关于字典类型

    public List<String> selectConfigTypeList(){
        return configDao.selectConfigTypeList();
    }

    //关于字典操作
    public List<Dict> selectDictListByPage(ConfigEntity configEntity){
        return configDao.selectConfigListByPage(configEntity);
    }
    public int selectSearchCount(ConfigEntity configEntity) {
        return configDao.selectSearchCount(configEntity);
    }

    public  boolean insertDict(ConfigEntity configEntity){
        configEntity.preInsert();
        return configDao.insertConfig(configEntity) == 1;
    }
    public boolean deleteDictByIds(List<ConfigEntity> configEntities){
        return configEntities.size()==0 || configDao.deleteConfigByIds(configEntities)==configEntities.size();
    }
    public boolean deleteDictById(ConfigEntity configEntity){
        return configDao.deleteConfigById(configEntity)==1;
    }

    public  boolean updateDict(ConfigEntity configEntity){
        return configDao.updateConfig(configEntity) == 1;
    }


}
