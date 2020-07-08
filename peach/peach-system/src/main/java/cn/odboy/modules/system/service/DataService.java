package cn.odboy.modules.system.service;

import cn.odboy.modules.system.service.dto.UserDto;

import java.util.List;

/**
 * 数据权限服务类
 *
 * @date 2020-05-07
 */
public interface DataService {

    /**
     * 获取数据权限
     *
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
