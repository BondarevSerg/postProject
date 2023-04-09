package ru.bondarev.post.dto.request;

import lombok.Data;


/**
 * Dto отправления с фронта
 */

@Data
public class PostalItemRequest {


    /**
     * Индекс отделения прибытия
     */
    private Long postOfficeInIndex;
    /**
     * индекс  отделения отправления
     */
    private Long postOfficeOutIndex;
    /**
     * Адрес получателя
     */
    private String personAddress;
    /**
     * Имя получателя
     */
    private String personName;

}
