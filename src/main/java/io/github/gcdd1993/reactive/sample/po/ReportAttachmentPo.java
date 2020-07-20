package io.github.gcdd1993.reactive.sample.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("report_attachment")
public class ReportAttachmentPo {
    @Id
    private Long id;

    private Integer projectId;

    private Integer type;
    private String url;
    private LocalDate date;
}
