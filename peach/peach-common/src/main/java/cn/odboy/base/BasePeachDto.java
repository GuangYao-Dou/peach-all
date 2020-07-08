package cn.odboy.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BasePeachDto implements Serializable {
    protected Integer id;
    protected Date createTime;
    protected Date updateTime;
    protected String createUser;
    protected String updateUser;
}
