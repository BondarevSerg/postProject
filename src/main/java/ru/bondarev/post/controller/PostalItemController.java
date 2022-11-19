package ru.bondarev.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.entity.PostalItem;
import ru.bondarev.post.services.PostalItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер работы с отправлениями
 */
@RestController
@RequestMapping("/postal-item")
@RequiredArgsConstructor
public class PostalItemController {

    private final PostalItemService postalItemService;



    /**
     * Получение списка отправлеий
     *
     * @return список отправлений
     */
    @GetMapping()
    public List<PostalItemResponse> getAllPostalItems() {
        return postalItemService.getAllPostalItems();
    }

    /**
     * Получение отправления по идентификатору
     *
     * @param id идентификатор
     * @return отправление
     */
    @GetMapping("/{id}")
    public PostalItemResponse getPostOfficeById(@PathVariable("id") Long id) {
        return postalItemService.getPostalItemById(id);
    }

    /**
     * создание почтового отрпавления
     * @param postalItemRequest
     * @param bindingResult
     * @return
     */
    @PostMapping("/newpostal_item")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PostalItemRequest postalItemRequest,
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
        postalItemService.savePostItem(postalItemRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * удаление почтового отправления по id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete_item/{id}")
    public ResponseEntity<HttpStatus> deletePostalItemById (@PathVariable("id") Long id) {
        postalItemService.deletePostalItem(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * внесение изменений в отправление
     *
     * @param postalItemRequest дто с фронта
     * @return
     */

    @PutMapping
    public ResponseEntity<PostalItemResponse>  updatePostalItem(@RequestBody PostalItemRequest postalItemRequest) {
        return new ResponseEntity<> (postalItemService.updatePostalItem(postalItemRequest), HttpStatus.OK);
    }

}
