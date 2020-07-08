package cn.odboy.modules.mnt.rest;

import cn.odboy.annotation.Log;
import cn.odboy.exception.BadRequestException;
import cn.odboy.modules.mnt.domain.Database;
import cn.odboy.modules.mnt.service.DatabaseService;
import cn.odboy.modules.mnt.service.dto.DatabaseDto;
import cn.odboy.modules.mnt.service.dto.DatabaseQueryCriteria;
import cn.odboy.modules.mnt.util.SqlUtils;
import cn.odboy.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @date 2019-08-24
 */
@Api(tags = "运维：数据库管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/database")
public class DatabaseController {

    private final String fileSavePath = FileUtil.getTmpDirPath() + "/";
    private final DatabaseService databaseService;

    @Log("导出数据库数据")
    @ApiOperation("导出数据库数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('database:list')")
    public void download(HttpServletResponse response, DatabaseQueryCriteria criteria) throws IOException {
        databaseService.download(databaseService.queryAll(criteria), response);
    }

    @Log("查询数据库")
    @ApiOperation(value = "查询数据库")
    @GetMapping
    @PreAuthorize("@el.check('database:list')")
    public ResponseEntity<Object> query(DatabaseQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(databaseService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增数据库")
    @ApiOperation(value = "新增数据库")
    @PostMapping
    @PreAuthorize("@el.check('database:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Database resources) {
        databaseService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改数据库")
    @ApiOperation(value = "修改数据库")
    @PutMapping
    @PreAuthorize("@el.check('database:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Database resources) {
        databaseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除数据库")
    @ApiOperation(value = "删除数据库")
    @DeleteMapping
    @PreAuthorize("@el.check('database:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<String> ids) {
        databaseService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("测试数据库链接")
    @ApiOperation(value = "测试数据库链接")
    @PostMapping("/testConnect")
    @PreAuthorize("@el.check('database:testConnect')")
    public ResponseEntity<Object> testConnect(@Validated @RequestBody Database resources) {
        return new ResponseEntity<>(databaseService.testConnection(resources), HttpStatus.CREATED);
    }

    @Log("执行SQL脚本")
    @ApiOperation(value = "执行SQL脚本")
    @PostMapping(value = "/upload")
    @PreAuthorize("@el.check('database:add')")
    public ResponseEntity<Object> upload(@RequestBody MultipartFile file, HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        DatabaseDto database = databaseService.findById(id);
        String fileName;
        if (database != null) {
            fileName = file.getOriginalFilename();
            File executeFile = new File(fileSavePath + fileName);
            FileUtil.del(executeFile);
            file.transferTo(executeFile);
            String result = SqlUtils.executeFile(database.getJdbcUrl(), database.getUserName(), database.getPwd(), executeFile);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new BadRequestException("Database not exist");
        }
    }
}
