package ru.bondarev.post.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.services.impl.PostOfficeServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер работы с отделения
 */
@RestController
@RequestMapping("/post-office")
@RequiredArgsConstructor
public class PostOfficeController {

    private final PostOfficeServiceImpl postOfficeService;

    /**
     * Получение списка отделений
     *
     * @return список отделений
     */
    @GetMapping()
    public List<PostOfficeResponse> getAllPostOffice() {
        return postOfficeService.getAllPostOffices();
    }

    /**
     * Получение отделения по идентификатору
     *
     * @param id идентификатор
     * @return отделение
     */
    @GetMapping("/{id}")
    public PostOfficeResponse getPostOfficeById(@PathVariable("id") Long id) {
        return postOfficeService.getPostOfficeById(id);
    }

    /**
     * Сохранение почтового отделения
     *
     * @param postOfficeRequest
     * @param bindingResult
     * @return
     */
    @PostMapping("/newoffice")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PostOfficeRequest postOfficeRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }

        }
        postOfficeService.savePostOffice(postOfficeRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * удаление почтового отделения по ID
     * если в нем нет отправлений
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete_office/{id}")
    public ResponseEntity<HttpStatus> deletePostOfficeById(@PathVariable("id") Long id) {
        postOfficeService.deletePostOffice(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * получение списка входящих отправлений отделения
     *
     * @param id
     * @return
     */
    @GetMapping("/itemsIn/{id}")
    public List<PostalItemResponse> getPostalItemsByPostOfficeId(@PathVariable("id") Long id) {
        return postOfficeService.getPostalItemsInByPostOffice(id);
    }

    /**
     * получение списка исходящих отправлений отделения
     *
     * @param id
     * @return
     */
    @GetMapping("/itemsOut/{id}")
    public List<PostalItemResponse> getPostalItemsByPostOfficeOut(@PathVariable("id") Long id) {
        return postOfficeService.getPostalItemsOutByPostOffice(id);
    }


}





