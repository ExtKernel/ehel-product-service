package org.tes.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseProduct implements Serializable {

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
