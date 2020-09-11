package bit.ss.surveysystem.modules.sys.Service;

import bit.ss.surveysystem.modules.sys.Dao.DictDao;
import bit.ss.surveysystem.modules.sys.Entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictService {

    @Autowired
    DictDao dictDao;
    //关于字典类型

    public List<String> selectDictTypeList(){
        return dictDao.selectDictTypeList();
    }

    //关于字典操作
    public List<Dict> selectDictListByPage(Dict dict){
        return dictDao.selectDictListByPage(dict);
    }
    public int selectSearchCount(Dict dict) {
        return dictDao.selectSearchCount(dict);
    }

    public  boolean insertDict(Dict dict){
        dict.preInsert();
        return dictDao.insertDict(dict) == 1;
    }
    public boolean deleteDictByIds(List<Dict> dicts){
        return dicts.size()==0 || dictDao.deleteDictByIds(dicts)==dicts.size();
    }
    public boolean deleteDictById(Dict dict){
        return dictDao.deleteDictById(dict)==1;
    }

    public  boolean updateDict(Dict dict){
        return dictDao.updateDict(dict) == 1;
    }


}
