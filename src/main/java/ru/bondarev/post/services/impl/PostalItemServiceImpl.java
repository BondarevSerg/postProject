package ru.bondarev.post.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostalItemRequest;
import ru.bondarev.post.entity.PostalItem;
import ru.bondarev.post.mappers.PostalItemMapper;
import ru.bondarev.post.repositories.PostOfficeRepository;
import ru.bondarev.post.repositories.PostalItemRepository;
import ru.bondarev.post.services.PostalItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostalItemServiceImpl implements PostalItemService {

    private final PostalItemRepository postalItemRepository;
    private final PostOfficeRepository postOfficeRepository;

    /**
     * Получение  отправления по id
     *
     * @param id идентификатор
     * @return
     */
    @Override
    public PostalItemResponse getPostalItemById(Long id) {
        var postalItem = postalItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправление c идентификатором: " + id));
        return PostalItemMapper.INSTANCE.toDTO(postalItem);
    }

    /**
     * получение списка отправлений
     *
     * @return
     */
    @Override
    public List<PostalItemResponse> getAllPostalItems() {
        var postalItems = postalItemRepository.findAll();
        return postalItems.stream()
                .map(PostalItemMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * сохранение отправления с фронта
     *
     * @param postalItemRequest
     */
    @Override
    public void savePostItem(PostalItemRequest postalItemRequest) {

        postalItemRepository.save(PostalItem.builder()
                .personName(postalItemRequest.getPersonName())
                .personAddress(postalItemRequest.getPersonAddress())
                .postOfficeIn(postOfficeRepository.findPostOfficeByIndexPost(postalItemRequest.getPostOfficeInIndex()))
                .postOfficeOut(postOfficeRepository.findPostOfficeByIndexPost(postalItemRequest.getPostOfficeOutIndex()))
                .build());
    }

    /**
     * удаление отправления по id
     *
     * @param id
     */
    @Override
    public void deletePostalItem(Long id) {
        var postalItem = postalItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправлени по идентификатору: " + id));
        postalItemRepository.delete(postalItem);
    }

    /**
     *
     * @param id
     * @param postalItemRequest с фронта
     */
    @Override
    public void updatePostalItem(Long id, PostalItemRequest postalItemRequest) {

        PostalItem postalItem = postalItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отправлени"));
        postalItem.setPersonName(postalItemRequest.getPersonName());
        postalItem.setPersonAddress(postalItemRequest.getPersonAddress());
        postalItem.setPostOfficeIn(postOfficeRepository.findPostOfficeByIndexPost(postalItemRequest.getPostOfficeInIndex()));
        postalItem.setPostOfficeOut(postOfficeRepository.findPostOfficeByIndexPost
                (postalItemRequest.getPostOfficeOutIndex()));
         postalItemRepository.save(postalItem);


    }
    /**
     * Получение всех входящих отправлений  по id отделения
     *
     * @param indexPost
     * @return
     */
    @Override
    public List<PostalItemResponse> getPostalItemsInByPostOfficeId(Long indexPost) {

        return postalItemRepository.findAllByPostOfficeInIndexPost(indexPost).stream()
                .map(PostalItemMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Получение всех исходящих отправлений  по id отделения
     *
     * @param indexPost
     * @return
     */
    @Override
    public List<PostalItemResponse> getPostalItemsOutByPostOfficeId(Long indexPost) {

        return postalItemRepository.findAllByPostOfficeOutIndexPost(indexPost).stream()
                .map(PostalItemMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


}
