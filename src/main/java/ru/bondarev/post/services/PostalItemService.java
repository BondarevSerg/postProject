package ru.bondarev.post.services;

import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostalItemRequest;

import java.util.List;

public interface PostalItemService {

    /**
     * Метод получения отправления по идентификатору
     *
     * @param id идентификатор
     * @return отправление
     */
    PostalItemResponse getPostalItemById(Long id);

    /**
     * Метод получения всех отправлений
     *
     * @return список отправлений
     */
    List<PostalItemResponse> getAllPostalItems();

    /**
     * Метод сохранения отправления
     *
     * @param postalItemRequest
     */
    void savePostItem(PostalItemRequest postalItemRequest);

    /**
     * Метод удаления отправления
     *
     * @param id
     */
    void deletePostalItem(Long id);

    /**
     * Метод апдейта отправления
     *
     * @param postalItemRequest с фронта
     * @return
     */
    PostalItemResponse updatePostalItem(PostalItemRequest postalItemRequest);
}
