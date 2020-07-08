package cn.odboy.repository;

import cn.odboy.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @date 2018-12-26
 */
public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
