package cn.odboy.ops.cmdb.service.dto;

import cn.odboy.base.BasePeachQueryCriteria;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务线 查询参数类
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 2:15
 */
@Getter
@Setter
public class CmdbBizLineQueryCriteria extends BasePeachQueryCriteria {
    /**
     * 业务线ID
     */
    private Integer id;
}
