package cn.odboy.ops.cmdb.service.impl;

import cn.odboy.common.PeachPage;
import cn.odboy.constant.PeachConstant;
import cn.odboy.exception.BadRequestException;
import cn.odboy.ops.cmdb.dao.ICmdbBizLineDao;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.ICmdbBizLineService;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineQueryCriteria;
import cn.odboy.ops.cmdb.service.mapstruct.ICmdbBizLineMapper;
import cn.odboy.utils.SecurityUtils;
import cn.odboy.utils.ValidationUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 业务线 服务实现类
 * 单一查询可以不需要只读事务，多重查询一定要只读事务
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/9 1:51
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class CmdbBizLineServiceImpl extends ServiceImpl<ICmdbBizLineDao, CmdbBizLine> implements ICmdbBizLineService {
    /**
     * 自动注入：业务线 领域模型转换接口
     */
    @Resource
    private ICmdbBizLineMapper bizLineMapper;


    /**
     * 条件分页查询
     *
     * @param criteria 查询条件
     * @param page     分页对象
     * @return ResponseEntity<Object>
     */
    @Override
    public ResponseEntity<Object> listByPage(CmdbBizLineQueryCriteria criteria, PeachPage<CmdbBizLineDto> page) {
        IPage<CmdbBizLine> iPage = null;
        if (criteria != null && criteria.getId() != null) {
            QueryWrapper<CmdbBizLine> bizLineQueryWrapper = new QueryWrapper<>();
            bizLineQueryWrapper.eq(PeachConstant.SQL_COLUMN_ID, criteria.getId());
            iPage = this.baseMapper.selectPage(new Page<>(page.getPage(), page.getSize()), bizLineQueryWrapper);
            page.setData(bizLineMapper.toDto(iPage.getRecords()));
        }
        return new ResponseEntity<>(iPage, HttpStatus.OK);
    }

    /**
     * 新增业务线
     *
     * @param resources CmdbBizLine
     * @return ResponseEntity<Object>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> create(CmdbBizLine resources) {
        ValidationUtil.validateEntity(resources);
        resources.setCreateUser(SecurityUtils.getCurrentUsername());
        resources.setUpdateUser(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(this.baseMapper.insert(resources), HttpStatus.OK);
    }

    /**
     * 更新业务线
     *
     * @param resources CmdbBizLine
     * @return ResponseEntity<Object>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> update(CmdbBizLine resources) {
        ValidationUtil.validateEntity(resources);
        if (resources.getId() == null) {
            throw new BadRequestException(PeachConstant.MSG_ID_NULL);
        }
        resources.setUpdateUser(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(this.baseMapper.updateById(resources), HttpStatus.OK);
    }

    /**
     * 删除业务线
     *
     * @param id Integer
     * @return ResponseEntity<Object>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> delete(Integer id) {
        if (id == null) {
            throw new BadRequestException(PeachConstant.MSG_ID_NULL);
        }
        return new ResponseEntity<>(this.baseMapper.deleteById(id), HttpStatus.OK);
    }
}
