package cn.odboy.modules.mnt.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.modules.mnt.domain.Database;
import cn.odboy.modules.mnt.service.dto.DatabaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @date 2019-08-24
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseMapper extends BaseMapper<DatabaseDto, Database> {

}
