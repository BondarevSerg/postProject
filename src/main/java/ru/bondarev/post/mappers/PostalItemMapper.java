package ru.bondarev.post.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.entity.PostalItem;
@Mapper
public interface PostalItemMapper {
    PostalItemMapper INSTANCE = Mappers.getMapper( PostalItemMapper.class );
    @Mapping(source = "id", target = "id")
    @Mapping(source = "postOfficeIn.indexPost", target = "postOfficeInIndex")
    @Mapping(source = "postOfficeOut.indexPost", target = "postOfficeOutIndex")
    PostalItemResponse toDTO(PostalItem postalItem);


}
