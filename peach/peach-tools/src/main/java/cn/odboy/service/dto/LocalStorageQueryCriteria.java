package cn.odboy.service.dto;

import cn.odboy.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @date 2019-09-05
 */
@Data
public class LocalStorageQueryCriteria {

    @Query(blurry = "name,suffix,type,createBy,size")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}