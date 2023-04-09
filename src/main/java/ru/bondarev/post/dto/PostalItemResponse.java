package ru.bondarev.post.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Dto отправления
 */
@Data
@Builder
public class PostalItemResponse {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Индекс входящего отделения
     */
    private Long postOfficeInIndex;

    /**
     * Индекс исходящего отделения
     */
    private Long postOfficeOutIndex;

    /**
     * Адресс отправителя
     */
    private String personAddress;

    /**
     * Фио отправителя
     */
    private String personName;
}
