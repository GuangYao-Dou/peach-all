package cn.odboy.modules.system.rest;

import cn.odboy.annotation.Log;
import cn.odboy.exception.BadRequestException;
import cn.odboy.modules.system.domain.Job;
import cn.odboy.modules.system.service.JobService;
import cn.odboy.modules.system.service.dto.JobQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @date 2019-03-29
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：岗位管理")
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;
    private static final String ENTITY_NAME = "job";

    @Log("导出岗位数据")
    @ApiOperation("导出岗位数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('job:list')")
    public void download(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        jobService.download(jobService.queryAll(criteria), response);
    }

    @Log("查询岗位")
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("@el.check('job:list','user:list')")
    public ResponseEntity<Object> query(JobQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(jobService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增岗位")
    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("@el.check('job:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Job resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        jobService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改岗位")
    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("@el.check('job:edit')")
    public ResponseEntity<Object> update(@Validated(Job.Update.class) @RequestBody Job resources) {
        jobService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除岗位")
    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("@el.check('job:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        // 验证是否被用户关联
        jobService.verification(ids);
        jobService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}