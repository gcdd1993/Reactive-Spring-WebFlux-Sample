package io.github.gcdd1993.reactive.sample.mappers;

import io.github.gcdd1993.reactive.sample.bo.ReportAttachmentBo;
import io.github.gcdd1993.reactive.sample.po.ReportAttachmentPo;
import io.github.gcdd1993.reactive.sample.vo.ReportAttachmentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Gcdd1993
 */
@Mapper(componentModel = "spring")
public interface ReportAttachmentMapper {

//    ReportAttachmentMapper INSTANCE = Mappers.getMapper(ReportAttachmentMapper.class);

    ReportAttachmentBo toBO(ReportAttachmentPo po);

    @Mapping(source = "date", target = "otherDate")
    ReportAttachmentVo toVO(ReportAttachmentBo bo);
}
