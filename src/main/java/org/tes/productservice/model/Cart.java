package org.tes.productservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "cart"
    )
    private List<DefaultProduct> defaultProducts;

    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "cart"
    )
    private List<CompProduct> compProducts;

    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "cart"
    )
    private List<LaptopProduct> laptopProducts;

    @JsonBackReference
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "product_user_id",
            referencedColumnName = "id"
    )
    private User user;

    public Cart(
            List<DefaultProduct> defaultProducts,
            List<CompProduct> compProducts,
            List<LaptopProduct> laptopProducts
    ) {
        this.defaultProducts = defaultProducts;
        this.compProducts = compProducts;
        this.laptopProducts = laptopProducts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", defaultProducts=" + defaultProducts +
                ", compProducts=" + compProducts +
                ", laptopProducts=" + laptopProducts +
                '}';
    }
}
