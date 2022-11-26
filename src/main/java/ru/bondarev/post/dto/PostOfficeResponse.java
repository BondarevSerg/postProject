package ru.bondarev.post.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Dto отделения для фронта
 */
@Data
@Builder
public class PostOfficeResponse {

    /**
     * Идентификатор отделения
     */
    private Long id;

    /**
     * Индекс отделения
     */
    private Long index;

    /**
     * Город отделения
     */
    private String city;

    /**
     * Отправления входящие
     */
    private List<PostalItemResponse> inPostalItems;

    /**
     * Отправления исходящие
     */
    private List<PostalItemResponse> outPostalItems;
}
