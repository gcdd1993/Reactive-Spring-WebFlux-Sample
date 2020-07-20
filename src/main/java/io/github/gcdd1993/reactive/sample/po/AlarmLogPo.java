package io.github.gcdd1993.reactive.sample.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@Table("alarm_log")
public class AlarmLogPo {

    @Id
    private Long id;

    private Long alertRuleId;

    private Long dataPointPointId;

    private Integer machineId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Timestamp happenTime;

    private Timestamp recoverTime;

}
