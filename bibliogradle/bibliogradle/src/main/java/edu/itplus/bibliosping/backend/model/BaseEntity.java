package edu.itplus.bibliosping.backend.model;

import jakarta.persistence.*;

@MappedSuperclass //ha egy os osztaly akit csak orokolnek
public class BaseEntity extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
