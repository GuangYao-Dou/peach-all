package cn.odboy.ops.cmdb.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 业务线 领域模型转换接口
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 2:16
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICmdbBizLineMapper extends BaseMapper<CmdbBizLineDto, CmdbBizLine> {
}
