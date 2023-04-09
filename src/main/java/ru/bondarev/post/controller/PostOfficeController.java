package ru.bondarev.post.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.services.impl.PostOfficeServiceImpl;


import java.util.List;

/**
 * Контроллер работы с отделения
 */
@RestController
@RequestMapping("/offices")
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
     * @return
     */
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody  PostOfficeRequest postOfficeRequest) {
        postOfficeService.savePostOffice(postOfficeRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * удаление почтового отделения по id
     * если в нем нет отправлений
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePostOfficeById(@PathVariable("id") Long id) {
        postOfficeService.deletePostOffice(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}





