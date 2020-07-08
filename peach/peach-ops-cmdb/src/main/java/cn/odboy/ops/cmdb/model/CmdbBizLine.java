package cn.odboy.ops.cmdb.model;

import cn.odboy.base.BasePeachModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 业务线
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName(value = "tb_cmdb_biz_line")
public class CmdbBizLine extends BasePeachModel {
    @NotEmpty(message = "业务线名称不能为空")
    private String bizName;
    @NotEmpty(message = "业务线信息不能为空")
    private String bizInfo;
    private Integer bizLeaderId;
    private Integer bizAlertGroupId;
}
