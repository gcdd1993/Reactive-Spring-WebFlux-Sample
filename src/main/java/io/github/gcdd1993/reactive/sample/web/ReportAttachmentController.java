package io.github.gcdd1993.reactive.sample.web;

import io.github.gcdd1993.reactive.sample.constants.ReportJobType;
import io.github.gcdd1993.reactive.sample.mappers.ReportAttachmentMapper;
import io.github.gcdd1993.reactive.sample.po.ReportAttachmentPo;
import io.github.gcdd1993.reactive.sample.repositories.ReportAttachmentRepository;
import io.github.gcdd1993.reactive.sample.vo.ReportAttachmentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class ReportAttachmentController {

    private final ReportAttachmentRepository reportAttachmentRepository;

    private final ReportAttachmentMapper reportAttachmentMapper;

    @GetMapping("/download/report")
    public Mono<ReportAttachmentVo> downloadReport(@RequestParam("date")
                                                   @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date,
                                                   @RequestParam("project_id") Integer projectId,
                                                   @RequestParam("type") String type) {
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
                .map(reportAttachmentMapper::toVO);
    }

}
