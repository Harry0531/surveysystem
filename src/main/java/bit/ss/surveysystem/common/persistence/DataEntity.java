package bit.ss.surveysystem.common.persistence;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 所有表格的对应实体类的基类
 * 提供了基础的通用属性
 */
@MappedSuperclass
public class DataEntity<T> {
    @Transient
    private Page<T> page; // 分页对象

    @Transient
    private String orderBy;//排序依据

    @Transient
    private String sortBy;//排序方式

    public DataEntity() {

    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
