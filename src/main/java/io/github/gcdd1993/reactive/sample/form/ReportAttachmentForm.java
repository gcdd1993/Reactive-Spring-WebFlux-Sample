package io.github.gcdd1993.reactive.sample.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Gcdd1993
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportAttachmentForm {

    @NotNull
    private Integer projectId;

    @NotNull
    private Integer type;

    @NotEmpty
    private String url;

    @NotNull
    private LocalDate date;
}
