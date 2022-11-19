package ru.bondarev.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bondarev.post.entity.PostOffice;

import java.util.Optional;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

    PostOffice findPostOfficeByIndexPost(Long indexPost);
}
