package org.tes.productservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DefaultProduct extends BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public DefaultProduct(
            @NotNull String title,
            String description,
            @NotNull String condition,
            @NotNull int price,
            @NotNull int quantity
    ) {
        super(
                title,
                description,
                condition,
                price,
                quantity
        );
        this.id = id;
    }

    @Override
    public String toString() {
        return "DefaultProduct{" +
                "id=" + id +
                '}';
    }
}
