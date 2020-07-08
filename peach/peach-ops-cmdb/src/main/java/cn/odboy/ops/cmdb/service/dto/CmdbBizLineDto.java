package cn.odboy.ops.cmdb.service.dto;

import cn.odboy.base.BasePeachDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务线 DTO
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 2:12
 */
@Getter
@Setter
public class CmdbBizLineDto extends BasePeachDto {
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 业务信息
     */
    private String bizInfo;
    /**
     * 负责人ID
     */
    private Integer bizLeaderId;
    /**
     * 告警组ID
     */
    private Integer bizAlertGroupId;
}
