package cn.odboy.ops.cmdb.service.dto;

import cn.odboy.base.BasePeachDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmdbBizLineDto extends BasePeachDto {
    private String bizName;
    private String bizInfo;
    private Integer bizLeaderId;
    private Integer bizAlertGroupId;
}
