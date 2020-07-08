package cn.odboy.base;

import cn.odboy.common.PeachPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;

/**
 * 基础控制器
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/8 20:45
 */
public interface BasePeachController<QP extends BasePeachQueryParameter, D extends BasePeachDto, M extends BasePeachModel> {
    ResponseEntity<Object> getList(PeachPage<QP, D> queryBody);

    ResponseEntity<Object> create(M resources);

    ResponseEntity<Object> update(M resources);

    ResponseEntity<Object> delete(Integer id);
}
