package by.roman.ventskus.entity;

import javax.persistence.*;

/**
 * Created by Roman Ventskus on 22.01.2016.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
