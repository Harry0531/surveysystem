package bit.ss.surveysystem.modules.sys.Dao;



import bit.ss.surveysystem.modules.sys.Entity.Dict;

import java.util.List;


public interface DictDao {


    List<String> selectDictTypeList();

    //关于字典操作
    List<Dict> selectDictListByPage(Dict dict);
    int selectSearchCount(Dict dict);

    int insertDict(Dict dict);

    int deleteDictByIds(List<Dict> dicts);
    int deleteDictById(Dict dict);

    int updateDict(Dict dict);

}
