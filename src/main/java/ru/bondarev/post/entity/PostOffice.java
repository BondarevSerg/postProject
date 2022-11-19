package ru.bondarev.post.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Почтовое отделение
 */
@Entity
@Table(name = "post_office")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class PostOffice {

    /**
     * Идентификаор отделения
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Индекс отделения
     */
    @NotNull
    @Column(name = "office_index")
    private Long indexPost;

    /**
     * Список исходящих отправлений
     */
    @OneToMany(mappedBy = "postOfficeOut")
    private List<PostalItem> OutPostalItems;

    /**
     * Список входящих отправления
     */
    @OneToMany(mappedBy = "postOfficeIn")
    private List<PostalItem> InPostalItems;

    /**
     * Город отделения
     */
    @Column(name = "city")
    private String city;

}
