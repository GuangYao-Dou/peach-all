package cn.odboy.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 自定义Model基类
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 2:19
 */
@Getter
@Setter
public class BasePeachModel implements Serializable {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    //需要指定，否则无法新增后拿到回调的id，以及进行删除等操作
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    @ApiModelProperty(value = "主键", hidden = true)
    protected Integer id;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    protected Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间", hidden = true)
    protected Date updateTime;
    @ApiModelProperty(value = "创建者，默认admin", hidden = true)
    protected String createUser;
    @ApiModelProperty(value = "更新者，默认admin", hidden = true)
    protected String updateUser;
}
