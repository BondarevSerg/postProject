package ru.bondarev.post.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bondarev.post.dto.PostOfficeResponse;
import ru.bondarev.post.dto.PostalItemResponse;
import ru.bondarev.post.dto.request.PostOfficeRequest;

import ru.bondarev.post.mappers.PostOfficeMapper;
import ru.bondarev.post.mappers.PostalItemMapper;
import ru.bondarev.post.repositories.PostOfficeRepository;
import ru.bondarev.post.services.PostOfficeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostOfficeServiceImpl implements PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;



    /**
     * Получение отделения по id
     *
     * @param id идентификатор
     * @return
     */
    @Override
    public PostOfficeResponse getPostOfficeById(Long id) {

        var postOffice = postOfficeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));

        return PostOfficeMapper.INSTANCE.toDTO(postOffice);
    }

    /**
     * Получение списка отделений
     *
     * @return
     */
    @Override
    public List<PostOfficeResponse> getAllPostOffices() {
        var postOffices = postOfficeRepository.findAll();
        return postOffices.stream()
                .map(PostOfficeMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * сохранение нового отделения
     *
     * @param postOfficeRequest
     */
    @Override
    public void savePostOffice(PostOfficeRequest postOfficeRequest) {

        postOfficeRepository.save(PostOfficeMapper.INSTANCE.toEntity(postOfficeRequest));

    }

    /**
     * удаление отделения
     * @param id
     */
    @Override
    public void deletePostOffice(Long id) {
        var postOffice = postOfficeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено почтовое отделение по идентификатору: " + id));
        if (postOffice.getInPostalItems() == null) {  //если нет входящих отправлений
            postOfficeRepository.deleteById(id);
        }
    }

}
