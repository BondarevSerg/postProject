package ru.bondarev.post.entity;

import lombok.*;

import javax.persistence.*;


/**
 * Почтовое отправление
 */
@Entity
@Table(name = "postal_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class PostalItem {

    /**
     * id отправления
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Адрес получателя
     */
    @Column(name = "person_address")
    private String personAddress;

    /**
     * Имя получателя
     */
    @Column(name = "person_name")
    private String personName;

    /**
     * Отделение прибытия
     */
    @ManyToOne
    @JoinColumn(name = "postal_office_in", referencedColumnName = "id")
    private PostOffice postOfficeIn;

    /**
     * Отделение отправления
     */
    @ManyToOne
    @JoinColumn(name = "postal_office_out", referencedColumnName = "id")
    private PostOffice postOfficeOut;



}
