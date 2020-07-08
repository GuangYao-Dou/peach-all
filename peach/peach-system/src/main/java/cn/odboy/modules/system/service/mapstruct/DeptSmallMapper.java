package cn.odboy.modules.system.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.modules.system.domain.Dept;
import cn.odboy.modules.system.service.dto.DeptSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @date 2019-03-25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptSmallMapper extends BaseMapper<DeptSmallDto, Dept> {

}