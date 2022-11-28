package ru.bondarev.post.dto.request;

import lombok.Data;
import ru.bondarev.post.entity.PostOffice;

/**
 * Dto отправления с фронта
 */

@Data
public class PostalItemRequest {

    /**
     * id отправления
     */
    private long id;
    /**
     * Индекс входящего отделения
     */
    private long postOfficeInIndex;
    /**
     * индекс исходящего отделения
     */
    private long postOfficeOutIndex;
    /**
     * Адрес получателя
     */
    private String personAddress;
    /**
     * Имя получателя
     */
    private String personName;

}
