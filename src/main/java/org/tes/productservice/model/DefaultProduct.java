package org.tes.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    public DefaultProduct(
            Long id,
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
}
