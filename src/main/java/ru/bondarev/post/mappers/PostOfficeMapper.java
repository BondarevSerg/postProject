package ru.bondarev.post.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.entity.PostOffice;

@Mapper(uses = { PostalItemMapper.class })
public interface PostOfficeMapper {
    PostOfficeMapper INSTANCE = Mappers.getMapper( PostOfficeMapper.class );
    @Mapping(source = "id", target = "id")
    @Mapping(source = "indexPost", target = "index")
    PostOfficeResponse toDTO(PostOffice postOffice);

    @Mapping(source = "index", target = "indexPost")
    @Mapping(source = "city", target = "city")
    PostOffice toEntity(PostOfficeRequest postOfficeRequest);
}
