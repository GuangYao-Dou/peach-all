package cn.odboy.modules.system.repository;

import cn.odboy.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * @date 2019-03-29
 */
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return /
     */
    Job findByName(String name);

    /**
     * 根据Id删除
     *
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}