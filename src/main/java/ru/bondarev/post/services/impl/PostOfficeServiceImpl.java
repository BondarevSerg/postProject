package ru.bondarev.post.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;
import ru.bondarev.post.entity.PostOffice;
import ru.bondarev.post.entity.PostalItem;
import ru.bondarev.post.repositories.PostOfficeRepository;
import ru.bondarev.post.services.PostMapper;
import ru.bondarev.post.services.PostOfficeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostOfficeServiceImpl implements PostOfficeService {

    private final PostOfficeRepository repository;

    private final PostMapper mapper;


    @Override
    public PostOfficeResponse getPostOfficeById(Long id) {

        var postOffice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));

        return mapper.entityToResponse(postOffice);
    }

    @Override
    public List<PostOfficeResponse> getAllPostOffices() {
        var postOffices = repository.findAll();
        return mapper.listPostOfficeToResponse(postOffices);
    }

    @Override
    @Transactional
    public void savePostOffice(PostOfficeRequest postOfficeRequest) {

        repository.save(mapper.requestToEntity(postOfficeRequest));

    }

    @Override
    @Transactional
    public void deletePostOffice(Long id) {
        var postOffice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));
        if (postOffice.getInPostalItems() == null) {  //если нет входящих
            repository.deleteById(id);
        }
    }

    /**
     * Получение всех входящих отправлений в отделении по id
     *
     * @param id
     * @return
     */
    @Override
    public List<PostalItemResponse> getPostalItemsInByPostOffice(Long id) {
        var postOffice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));
        List<PostalItem> postalItems = postOffice.getInPostalItems();
        List<PostalItemResponse> postalItemResponses = new ArrayList<>();
        for (PostalItem postalItem : postalItems)
            postalItemResponses.add(mapper.entityToResponse(postalItem));


        return postalItemResponses;
    }

    /**
     * получение всех исходящих отправлений в отделении
     *
     * @param id
     * @return
     */
    public List<PostalItemResponse> getPostalItemsOutByPostOffice(Long id) {
        var postOffice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));
        List<PostalItem> postalItems = postOffice.getOutPostalItems();
        List<PostalItemResponse> postalItemResponses = new ArrayList<>();
        for (PostalItem postalItem : postalItems)
            postalItemResponses.add(mapper.entityToResponse(postalItem));


        return postalItemResponses;
    }
}
