package ru.bondarev.post.services;

import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;

import java.util.List;

public interface PostOfficeService {

    /**
     * Получение почтового отделения по идентификатору
     *
     * @param id идентификатор
     * @return почтовое отделение
     */
    PostOfficeResponse getPostOfficeById(Long id);

    /**
     * Получения всех почтовых отделений
     *
     * @return список почтовых отделений
     */
    List<PostOfficeResponse> getAllPostOffices();


    void savePostOffice(PostOfficeRequest postOfficeRequest);


    void deletePostOffice(Long id);

    List<PostalItemResponse> getPostalItemsInByPostOffice(Long id);


  List<PostalItemResponse> getPostalItemsOutByPostOffice(Long id);

}
