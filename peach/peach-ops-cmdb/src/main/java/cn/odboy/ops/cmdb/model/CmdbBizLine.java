package cn.odboy.ops.cmdb.model;

import cn.odboy.base.BasePeachModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 业务线 Model
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 1:52
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName(value = "tb_cmdb_biz_line")
public class CmdbBizLine extends BasePeachModel {
    /**
     * 业务线名称
     */
    @NotEmpty(message = "业务线名称不能为空")
    private String bizName;
    /**
     * 业务线信息
     */
    @NotEmpty(message = "业务线信息不能为空")
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
