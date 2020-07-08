package cn.odboy.modules.system.service.dto;

import cn.odboy.annotation.Query;
import lombok.Data;

/**
 * @date 2019-04-10
 */
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name", joinName = "dict")
    private String dictName;
}