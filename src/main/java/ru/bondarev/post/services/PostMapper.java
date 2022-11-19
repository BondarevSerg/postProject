package ru.bondarev.post.services;

import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.entity.PostOffice;
import ru.bondarev.post.entity.PostalItem;

import java.util.List;

/**
 * Простой маппер для сущностей
 */
public interface PostMapper {

    /**
     * Маппинг ентити в дто для фронта(отделение)
     *
     * @param postOffice ентити
     * @return дто
     */
    PostOfficeResponse entityToResponse(PostOffice postOffice);

    /**
     * Маппинг ентити в дто для фронта(отправление)
     *
     * @param postalItem ентити
     * @return дто
     */
    PostalItemResponse entityToResponse(PostalItem postalItem);

    /**
     * Маппинг ентити в дто для фронта(отправление)
     *
     * @param postalItems список ентити
     * @return дто
     */
    List<PostalItemResponse> listPostalItemToResponse(List<PostalItem> postalItems);

    /**
     * Маппинг списка ентити в список дто для фронта(отделение)
     *
     * @param postOffices спискок ентити
     * @return дто
     */
    List<PostOfficeResponse> listPostOfficeToResponse(List<PostOffice> postOffices);

    /**
     * Маппинг  реквест(с фронта) в ентити для отделения
     *
     * @param postOfficeRequest дто с фронта
     * @return ентити
     */

    PostOffice requestToEntity(PostOfficeRequest postOfficeRequest);

    PostalItem requestToEntity(PostalItemRequest postalItemRequest);
}
