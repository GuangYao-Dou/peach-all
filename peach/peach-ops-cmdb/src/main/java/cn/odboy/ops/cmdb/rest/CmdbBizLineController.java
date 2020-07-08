package cn.odboy.ops.cmdb.rest;

import cn.odboy.annotation.Log;
import cn.odboy.base.BasePeachController;
import cn.odboy.common.PeachPage;
import cn.odboy.constant.PeachConstant;
import cn.odboy.exception.BadRequestException;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.ICmdbBizLineService;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineQueryParameter;
import cn.odboy.ops.cmdb.service.mapstruct.ICmdbBizLineMapper;
import cn.odboy.utils.SecurityUtils;
import cn.odboy.utils.ValidationUtil;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "CMDB：业务线管理")
@RestController
@RequestMapping("/api/cmdb/bizLines")
public class CmdbBizLineController implements BasePeachController<CmdbBizLineQueryParameter, CmdbBizLineDto, CmdbBizLine> {
    @Autowired
    private ICmdbBizLineService bizLineService;
    @Resource
    private ICmdbBizLineMapper bizLineMapper;

    @Log("查询业务线")
    @ApiOperation(value = "查询业务线")
    @GetMapping
    @PreAuthorize("@el.check('bizLine:list')")
    @Cached(name = "CmdbBizLineController.getList", expire = 3600)
    @Override
    public ResponseEntity<Object> getList(PeachPage<CmdbBizLineQueryParameter, CmdbBizLineDto> queryBody) {
        IPage<CmdbBizLine> iPage = null;
        if (queryBody != null && queryBody.getParams() != null) {
            QueryWrapper<CmdbBizLine> wrapper = new QueryWrapper<>();
            if (queryBody.getParams().getId() != null) {
                wrapper.eq(PeachConstant.SQL_COLUMN_ID, queryBody.getParams().getId());
            }
            iPage = bizLineService.page(new Page<>(queryBody.getPage(), queryBody.getSize()), wrapper);
            queryBody.setData(bizLineMapper.toDto(iPage.getRecords()));
        }
        return new ResponseEntity<>(iPage, HttpStatus.OK);
    }

    @Log("新增业务线")
    @ApiOperation(value = "新增业务线")
    @PostMapping
    @PreAuthorize("@el.check('bizLine:create')")
    @Override
    public ResponseEntity<Object> create(CmdbBizLine resources) {
        ValidationUtil.validateEntity(resources);
        resources.setCreateUser(SecurityUtils.getCurrentUsername());
        resources.setUpdateUser(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(bizLineService.save(resources), HttpStatus.OK);
    }

    @Log("修改业务线")
    @ApiOperation(value = "修改业务线")
    @PutMapping
    @PreAuthorize("@el.check('bizLine:update')")
    @Override
    public ResponseEntity<Object> update(CmdbBizLine resources) {
        ValidationUtil.validateEntity(resources);
        if (resources.getId() == null) {
            throw new BadRequestException(PeachConstant.MSG_ID_NULL);
        }
        resources.setUpdateUser(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(bizLineService.updateById(resources), HttpStatus.OK);
    }

    @Log("删除业务线")
    @ApiOperation(value = "删除业务线")
    @DeleteMapping
    @PreAuthorize("@el.check('bizLine:delete')")
    @Override
    public ResponseEntity<Object> delete(Integer id) {
        if (id == null) {
            throw new BadRequestException(PeachConstant.MSG_ID_NULL);
        }
        return new ResponseEntity<>(bizLineService.removeById(id), HttpStatus.OK);
    }
}

