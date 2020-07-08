package cn.odboy.ops.cmdb.service;

import cn.odboy.common.PeachPage;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineQueryCriteria;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * 业务线 服务接口类
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 1:59
 */
public interface ICmdbBizLineService extends IService<CmdbBizLine> {
    /**
     * 条件分页查询
     *
     * @param criteria 查询条件
     * @param page     分页对象
     * @return ResponseEntity<Object>
     */
    ResponseEntity<Object> listByPage(CmdbBizLineQueryCriteria criteria, PeachPage<CmdbBizLineDto> page);

    /**
     * 新增业务线
     *
     * @param resources CmdbBizLine
     * @return ResponseEntity<Object>
     */
    ResponseEntity<Object> create(CmdbBizLine resources);

    /**
     * 更新业务线
     *
     * @param resources CmdbBizLine
     * @return ResponseEntity<Object>
     */
    ResponseEntity<Object> update(CmdbBizLine resources);

    /**
     * 删除业务线
     *
     * @param id Integer
     * @return ResponseEntity<Object>
     */
    ResponseEntity<Object> delete(Integer id);
}
