package cn.odboy.ops.cmdb.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICmdbBizLineMapper extends BaseMapper<CmdbBizLineDto, CmdbBizLine> {
}
