package ru.bondarev.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bondarev.post.entity.PostOffice;

import java.util.Optional;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    /**
     * поиск отделения по индексу
     * @param indexPost
     * @return
     */
    PostOffice findPostOfficeByIndexPost(Long indexPost);
}
