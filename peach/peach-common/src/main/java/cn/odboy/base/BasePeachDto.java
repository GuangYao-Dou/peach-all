package cn.odboy.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 自定义DTO基类
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 2:14
 */
@Getter
@Setter
public class BasePeachDto implements Serializable {
    /**
     * 自增主键
     */
    protected Integer id;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 更新时间
     */
    protected Date updateTime;
    /**
     * 创建人
     */
    protected String createUser;
    /**
     * 更新人
     */
    protected String updateUser;
}
