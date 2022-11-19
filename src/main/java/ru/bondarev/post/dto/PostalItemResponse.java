package ru.bondarev.post.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Dto отправления для фронта
 */
@Data
@Builder
public class PostalItemResponse {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Индекс отделения куда отправлено
     */
    private Long postalOfficeInIndex;

    /**
     * Индекс отделения откуда отправлено
     */
    private Long postalOfficeOutIndex;

    /**
     * Адресс отправителя
     */
    private String personAddress;

    /**
     * Фио отправителя
     */
    private String personName;
}
