package cn.odboy.modules.system.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.modules.system.domain.Role;
import cn.odboy.modules.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
