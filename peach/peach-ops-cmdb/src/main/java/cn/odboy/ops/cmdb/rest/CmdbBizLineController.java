package cn.odboy.ops.cmdb.rest;

import cn.odboy.annotation.Log;
import cn.odboy.common.PeachPage;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.ICmdbBizLineService;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineDto;
import cn.odboy.ops.cmdb.service.dto.CmdbBizLineQueryCriteria;
import com.alicp.jetcache.anno.Cached;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "CMDB：业务线管理")
@RestController
@RequestMapping("/api/cmdb/bizLines")
public class CmdbBizLineController {
    @Autowired
    private ICmdbBizLineService bizLineService;

    @Log("查询业务线")
    @ApiOperation(value = "查询业务线")
    @GetMapping
    @PreAuthorize("@el.check('bizLine:list')")
    @Cached(name = "CmdbBizLineController.getList", expire = 3600)
    public ResponseEntity<Object> list(CmdbBizLineQueryCriteria criteria, PeachPage<CmdbBizLineDto> page) {
        return bizLineService.listByPage(criteria, page);
    }

    @Log("新增业务线")
    @ApiOperation(value = "新增业务线")
    @PostMapping
    @PreAuthorize("@el.check('bizLine:create')")
    public ResponseEntity<Object> create(CmdbBizLine resources) {
        return bizLineService.create(resources);
    }

    @Log("修改业务线")
    @ApiOperation(value = "修改业务线")
    @PutMapping
    @PreAuthorize("@el.check('bizLine:update')")
    public ResponseEntity<Object> update(CmdbBizLine resources) {
        return bizLineService.update(resources);
    }

    @Log("删除业务线")
    @ApiOperation(value = "删除业务线")
    @DeleteMapping
    @PreAuthorize("@el.check('bizLine:delete')")
    public ResponseEntity<Object> delete(Integer id) {
        return bizLineService.delete(id);
    }
}

