package org.tes.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseProduct {

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "condition")
    private String condition;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "quantity")
    private int quantity;
}
