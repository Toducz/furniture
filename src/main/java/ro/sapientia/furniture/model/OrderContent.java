package ro.sapientia.furniture.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "order_content")
public class OrderContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_order_content")
    @SequenceGenerator(name="pk_order_content",sequenceName="pk_order_content")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "furniture_order")
    private FurnitureOrder furnitureOrderId;

    @ManyToOne
    @JoinColumn(name = "furniture_item_join")
    private FurnitureItemJoin furnitureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderContent that = (OrderContent) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
