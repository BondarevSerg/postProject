package ru.bondarev.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bondarev.post.entity.PostalItem;

public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {

}
