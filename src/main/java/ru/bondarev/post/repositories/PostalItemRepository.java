package ru.bondarev.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bondarev.post.entity.PostalItem;

import java.util.List;

public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {
  List<PostalItem> findAllByPostOfficeInIndexPost(Long indexPost);
  List<PostalItem> findAllByPostOfficeOutIndexPost(Long indexPost);
}
