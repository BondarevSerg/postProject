package ru.bondarev.post.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.entity.PostOffice;
import ru.bondarev.post.entity.PostalItem;
import ru.bondarev.post.repositories.PostOfficeRepository;
import ru.bondarev.post.services.PostMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {

    private final PostOfficeRepository repositoryPostOffice;


    @Override
    public PostOfficeResponse entityToResponse(PostOffice postOffice) {
        return PostOfficeResponse.builder()
                .id(postOffice.getId())
                .index(postOffice.getIndexPost())
                .city(postOffice.getCity())
                .inPostalItems(listPostalItemToResponse(postOffice.getInPostalItems()))
                .outPostalItems(listPostalItemToResponse(postOffice.getOutPostalItems()))
                .build();
    }

    @Override
    public PostalItemResponse entityToResponse(PostalItem postalItem) {
        return PostalItemResponse.builder()
                .id(postalItem.getId())
                .postalOfficeInIndex(getPostalOfficeIndex(postalItem.getPostOfficeIn()))
                .postalOfficeOutIndex(getPostalOfficeIndex(postalItem.getPostOfficeOut()))
                .personAddress(postalItem.getPersonAddress())
                .personName(postalItem.getPersonName())
                .build();
    }

    @Override
    public List<PostOfficeResponse> listPostOfficeToResponse(List<PostOffice> postOffices) {
        if (Objects.nonNull(postOffices)) {
            return postOffices.stream()
                    .map(this::entityToResponse)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    @Override
    public List<PostalItemResponse> listPostalItemToResponse(List<PostalItem> postalItems) {
        if (!postalItems.isEmpty()) {
            return postalItems.stream()
                    .map(this::entityToResponse)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    /**
     * Получение индека отделения(если отделение не найдено - заполняем null)
     *
     * @param postOffice почтовое отделение
     * @return индекс почтового отделения
     */
    private Long getPostalOfficeIndex(PostOffice postOffice) {
        if (postOffice != null) {
            return postOffice.getIndexPost();
        } else {
            return null;
        }
    }

    /**
     * Dto c фронта в энтити для отделения
     *
     * @param postOfficeRequest дто с фронта
     * @return
     */
    @Override
    public PostOffice requestToEntity(PostOfficeRequest postOfficeRequest) {
        return PostOffice.builder()
                .indexPost(postOfficeRequest.getIndex())
                .city(postOfficeRequest.getCity())
                .InPostalItems(new ArrayList<>())//Верно что при создании нового отделения создаются пустые списки отправлений?
                .OutPostalItems(new ArrayList<>())//или вообще убрать эти поля и они при создании сущности пустые будут
                .build();
    }

    /**
     * Dto c фронта в энтити для отправления
     *
     * @param postalItemRequest
     * @return
     */
    @Override
    public PostalItem requestToEntity(PostalItemRequest postalItemRequest) {

        return PostalItem.builder()
                .id(postalItemRequest.getId())
                .postOfficeIn(getPostOfficeByIndexPost(postalItemRequest.getPostOfficeInIndex()))
                .postOfficeOut(getPostOfficeByIndexPost(postalItemRequest.getPostOfficeOutIndex()))
                .personAddress(postalItemRequest.getPersonAddress())
                .personName(postalItemRequest.getPersonName())
                .build();
    }

    /**
     * получение отделения  по индексу из DTO отправления
     *
     * @param
     * @return
     */
    private PostOffice  getPostOfficeByIndexPost(Long indexPost) {
        if (indexPost != null) {
            var postOffice = repositoryPostOffice.findPostOfficeByIndexPost(indexPost);

            return postOffice;

        } else {
            return null;
        }

    }
}



