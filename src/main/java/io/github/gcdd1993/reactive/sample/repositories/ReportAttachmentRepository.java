package io.github.gcdd1993.reactive.sample.repositories;

import io.github.gcdd1993.reactive.sample.po.ReportAttachmentPo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
public interface ReportAttachmentRepository extends R2dbcRepository<ReportAttachmentPo, Long> {
    Mono<ReportAttachmentPo> findByProjectIdAndDateAndType(Mono<Integer> projectId,
                                                           Mono<LocalDate> date,
                                                           Mono<Integer> type);

    Flux<ReportAttachmentPo> findByProjectId(Mono<Integer> projectId);
}
