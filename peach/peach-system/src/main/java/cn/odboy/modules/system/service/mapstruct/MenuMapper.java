package cn.odboy.modules.system.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.modules.system.domain.Menu;
import cn.odboy.modules.system.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {
}
