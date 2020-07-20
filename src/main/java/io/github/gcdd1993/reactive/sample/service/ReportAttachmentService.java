package io.github.gcdd1993.reactive.sample.service;

import io.github.gcdd1993.reactive.sample.bo.ReportAttachmentBo;
import io.github.gcdd1993.reactive.sample.constants.ReportJobType;
import io.github.gcdd1993.reactive.sample.form.ReportAttachmentForm;
import io.github.gcdd1993.reactive.sample.mappers.ReportAttachmentMapper;
import io.github.gcdd1993.reactive.sample.po.ReportAttachmentPo;
import io.github.gcdd1993.reactive.sample.repositories.ReportAttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportAttachmentService {

    private final ReportAttachmentRepository reportAttachmentRepository;

    private final ReportAttachmentMapper reportAttachmentMapper;

    public Mono<ReportAttachmentBo> downloadReport(LocalDate date, Integer projectId, String type) {
        return reportAttachmentRepository.findByProjectIdAndDateAndType(
                Mono.just(projectId),
                Mono.just(date),
                Mono.just(type).map(ReportJobType::valueOf).map(ReportJobType::ordinal)
        )
                .doOnError(ex -> {
                    throw new NullPointerException("找不到");
                })
                .defaultIfEmpty(new ReportAttachmentPo())
                .map(reportAttachmentMapper::toBO)
                ;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 5)
    public Mono<ReportAttachmentBo> create(Mono<ReportAttachmentForm> form) {
        return form
                .map(reportAttachmentMapper::form2PO)
                .flatMap(reportAttachmentRepository::save)
                .doOnSuccess(__ -> {
                    throw new RuntimeException("test transaction");
                })
                .doOnError(ex -> {
                    log.error("save report attachment error", ex);
                })
                .map(reportAttachmentMapper::toBO)
                ;
    }

    @Transactional(timeout = 5, readOnly = true)
    public Flux<ReportAttachmentBo> get(Mono<Integer> projectId) {
        return reportAttachmentRepository
                .findByProjectId(projectId)
//                .map(it -> {
//                    it.setProjectId(it.getProjectId() + 100);
//                    return it;
//                })
//                .flatMap(reportAttachmentRepository::save)
                .map(reportAttachmentMapper::toBO)
                ;

    }

}
