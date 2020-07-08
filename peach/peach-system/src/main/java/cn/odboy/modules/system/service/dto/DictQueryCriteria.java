package cn.odboy.modules.system.service.dto;

import cn.odboy.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 */
@Data
public class DictQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;
}
