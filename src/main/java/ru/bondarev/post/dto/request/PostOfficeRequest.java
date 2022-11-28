package ru.bondarev.post.dto.request;

import lombok.Data;

/**
 * Dto отделения с фронта
 */
@Data
public class PostOfficeRequest {
    /**
     * индекс отделения
     */
    private Long index;
    /**
     * город отделения
     */
    private String city;
}
