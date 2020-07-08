package cn.odboy.modules.system.service.mapstruct;

import cn.odboy.base.BaseMapper;
import cn.odboy.modules.system.domain.DictDetail;
import cn.odboy.modules.system.service.dto.DictDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @date 2019-04-10
 */
@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}