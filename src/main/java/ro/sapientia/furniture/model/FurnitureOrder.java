package ro.sapientia.furniture.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "furniture_order")
public class FurnitureOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_order")
    @SequenceGenerator(name="pk_order",sequenceName="pk_order")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "furniture_user")
    private double furnitureUser;

    @Column(name = "comment")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FurnitureOrder furnitureOrder = (FurnitureOrder) o;
        return id != null && Objects.equals(id, furnitureOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}