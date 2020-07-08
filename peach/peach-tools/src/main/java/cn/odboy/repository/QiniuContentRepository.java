package cn.odboy.repository;

import cn.odboy.domain.QiniuContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @date 2018-12-31
 */
public interface QiniuContentRepository extends JpaRepository<QiniuContent, Long>, JpaSpecificationExecutor<QiniuContent> {

    /**
     * 根据key查询
     *
     * @param key 文件名
     * @return QiniuContent
     */
    QiniuContent findByKey(String key);
}
