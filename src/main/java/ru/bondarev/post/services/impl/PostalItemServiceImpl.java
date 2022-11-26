package ru.bondarev.post.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.entity.PostOffice;
import ru.bondarev.post.entity.PostalItem;
import ru.bondarev.post.repositories.PostOfficeRepository;
import ru.bondarev.post.repositories.PostalItemRepository;
import ru.bondarev.post.services.PostMapper;
import ru.bondarev.post.services.PostalItemService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostalItemServiceImpl implements PostalItemService {

    private final PostalItemRepository repository;

    private final PostOfficeRepository repositoryPostOffice;

    private final PostMapper mapper;

    /**
     * Получение dto отправления по id
     *
     * @param id идентификатор
     * @return
     */
    @Override
    public PostalItemResponse getPostalItemById(Long id) {
        var postalItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправление c идентификатором: " + id));
        return mapper.entityToResponse(postalItem);
    }

    /**
     * получение списка отправлений
     *
     * @return
     */
    @Override
    public List<PostalItemResponse> getAllPostalItems() {
        var postalItems = repository.findAll();
        return mapper.listPostalItemToResponse(postalItems);
    }

    /**
     * сохранение отправления с фронта
     *
     * @param postalItemRequest
     */
    @Override
    public void savePostItem(PostalItemRequest postalItemRequest) {
        PostalItem postalItem = mapper.requestToEntity(postalItemRequest);
        repository.save(postalItem);
    }

    /**
     * удаление отправления по id
     *
     * @param id
     */
    @Override
    public void deletePostalItem(Long id) {
        var postalItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправлени по идентификатору: " + id));
        repository.delete(postalItem);
    }


    /**
     * Изменение отправления
     *
     * @param postalItemRequest с фронта
     * @return
     */
    @Override
    public PostalItemResponse updatePostalItem(PostalItemRequest postalItemRequest) {
        //нужна ли проверка такая есть отправление или нет?
        repository.findById(postalItemRequest.getId())
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправлени"));

        repository.save(mapper.requestToEntity(postalItemRequest));

        return mapper.entityToResponse(mapper.requestToEntity(postalItemRequest));

    }


}
