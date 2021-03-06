package io.github.gcdd1993.reactive.sample.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportAttachmentBo {
    private Long id;
    private Integer projectId;
    private Integer type;
    private String url;
    private LocalDate date;
}
