package ru.bondarev.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.services.PostalItemService;

import java.util.List;

/**
 * Контроллер работы с отправлениями
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class PostalItemController {

    private final PostalItemService postalItemService;


    /**
     * Получение списка всех отправлеий
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
     * Получение всех входящих отправлений по почтовому индексу отделения
     *
     * @param indexPost
     * @return список отправлений
     */
    @GetMapping("/oficceIn/{indexPost}")
    public  List<PostalItemResponse> getPostalItemsInByPostOfficeId(@PathVariable("indexPost") Long indexPost) {
        return postalItemService. getPostalItemsInByPostOfficeId(indexPost);
    }

    /**
     * Получение всех исходящих отправлений  по почтовому индексу отделения
     *
     * @param indexPost
     * @return список отправлений
     */
    @GetMapping("/oficceOut/{indexPost}")
    public  List<PostalItemResponse> getPostalItemsOutByPostOfficeId(@PathVariable("indexPost") Long indexPost) {
        return postalItemService. getPostalItemsOutByPostOfficeId(indexPost);
    }

    /**
     * создание почтового отрпавления
     *
     * @param postalItemRequest
     * @return
     */
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody PostalItemRequest postalItemRequest) {

        postalItemService.savePostItem(postalItemRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * удаление почтового отправления по id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePostalItemById(@PathVariable("id") Long id) {
        postalItemService.deletePostalItem(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * обновление отправления по id
     * @param id
     * @param postalItemRequest
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePostalItem (@PathVariable("id") Long id,
                                                        @RequestBody  PostalItemRequest postalItemRequest) {
        postalItemService.updatePostalItem(id, postalItemRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
