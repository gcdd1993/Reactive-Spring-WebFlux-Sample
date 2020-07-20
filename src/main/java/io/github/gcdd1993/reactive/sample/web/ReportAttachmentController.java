package io.github.gcdd1993.reactive.sample.web;

import io.github.gcdd1993.reactive.sample.form.ReportAttachmentForm;
import io.github.gcdd1993.reactive.sample.mappers.ReportAttachmentMapper;
import io.github.gcdd1993.reactive.sample.service.ReportAttachmentService;
import io.github.gcdd1993.reactive.sample.vo.ReportAttachmentVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class ReportAttachmentController {

    private final ReportAttachmentService reportAttachmentService;

    private final ReportAttachmentMapper reportAttachmentMapper;

    @GetMapping("/download/report")
    public Mono<ReportAttachmentVo> downloadReport(@RequestParam("date")
                                                   @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date,
                                                   @RequestParam("project_id") Integer projectId,
                                                   @RequestParam("type") String type) {
        return reportAttachmentService
                .downloadReport(date, projectId, type)
                .map(reportAttachmentMapper::toVO)
                ;
    }

    @PostMapping("/report")
    public Mono<ReportAttachmentVo> create(@RequestBody Mono<ReportAttachmentForm> form) {
        return reportAttachmentService
                .create(form)
                .map(reportAttachmentMapper::toVO)
                ;
    }

    @GetMapping("/{projectId:[0-9]+}")
    public Flux<ReportAttachmentVo> get(@PathVariable Integer projectId) {
        return reportAttachmentService
                .get(Mono.just(projectId))
                .map(reportAttachmentMapper::toVO)
                ;
    }

    @PostMapping("/createOrUpdate")
    public Mono<ReportAttachmentVo> createOrUpdate(@RequestBody Mono<ReportAttachmentForm> form) {
        return reportAttachmentService
                .createOrUpdate(form)
                .map(reportAttachmentMapper::toVO)
                ;
    }

}
