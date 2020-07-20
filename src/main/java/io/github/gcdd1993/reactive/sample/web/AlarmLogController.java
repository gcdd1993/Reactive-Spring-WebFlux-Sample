package io.github.gcdd1993.reactive.sample.web;

import io.github.gcdd1993.reactive.sample.po.AlarmLogPo;
import io.github.gcdd1993.reactive.sample.repositories.AlarmLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/alarm-log")
@RequiredArgsConstructor
public class AlarmLogController {

    private final AlarmLogRepository alarmLogRepository;

    private final DatabaseClient databaseClient;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/list")
    public Flux<AlarmLogPo> list() {
//        return alarmLogRepository.findAll();
        return Flux.empty();
    }

    @GetMapping("/{id:[0-9]+}")
    public Mono<AlarmLogPo> get(@PathVariable Long id) {
//        return alarmLogRepository.findById(id);
        return Mono.empty();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @GetMapping("/query")
    public Mono<Page<AlarmLogPo>> page(@RequestParam Long alertRuleId,
                                       @RequestParam Integer page,
                                       @RequestParam Integer size,
                                       Mono<Principal> principal) {
        var pageable = PageRequest.of(page, size);
        return alarmLogRepository.count()
                .flatMap(alarmLogCount ->
                        alarmLogRepository.findByAlertRuleIdOrderById(alertRuleId, pageable)
                                .collectList()
                                .map(alarmLogs -> new PageImpl<>(alarmLogs, pageable, alarmLogCount)))
                ;
//        var result = databaseClient
//                .select()
//                .from(AlarmLogEntity.class)
//                .matching(
//                        Criteria.where("alertRuleId").is(alertRuleId)
//                )
//                .page(pageable)
//                .fetch()
//                .all();
//        return databaseClient
//                .execute("SELECT count(*) FROM alarm_log where alert_rule_id = " + alertRuleId)
//                .as(Long.class)
//                .fetch()
//                .one()
//                .flatMap(count -> result
//                        .collectList()
//                        .map(alarmLogs -> new PageImpl<>(alarmLogs, pageable, count)))
//                ;
    }
}
