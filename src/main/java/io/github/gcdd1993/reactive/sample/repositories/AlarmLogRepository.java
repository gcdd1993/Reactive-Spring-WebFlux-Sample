package io.github.gcdd1993.reactive.sample.repositories;

import io.github.gcdd1993.reactive.sample.po.AlarmLogPo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface AlarmLogRepository extends R2dbcRepository<AlarmLogPo, Long> {
    Flux<AlarmLogPo> findByAlertRuleIdOrderById(Long alertRuleId, Pageable pageable);
}
