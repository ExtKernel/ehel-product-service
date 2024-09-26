package org.tes.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TableGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class CompProduct extends BaseProduct {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "compTableGenerator"
    )
    @TableGenerator(
            name = "compTableGenerator",
            table = "COMP_ID_GENERATOR",
            pkColumnName = "COMP_ID_KEY",
            valueColumnName = "COMP_ID_VALUE",
            pkColumnValue = "COMP_ID"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @NotNull
    @Column(name = "cpu")
    private String cpu;

    @NotNull
    @Column(name = "ram_capacity")
    private int ramCapacity;

    @NotNull
    @Column(name = "ram_type")
    private String ramType;

    @NotNull
    @Column(name = "gpu_type")
    private String gpuType;

    @Column(name = "gpu_model")
    private String gpuModel;

    @Column(name = "gpu_vram_capacity")
    private int gpuVramCapacity;

    @Column(name = "gpu_vram_type")
    private String gpuVramType;

    @Column(name = "storage_unit_type")
    private String storageUnitType;

    @Column(name = "storage_unit_capacity")
    private int storageUnitCapacity;

    @Column(name = "os")
    private String os;

    @NotNull
    @Column(name = "good_for")
    private String goodFor;

    public CompProduct(
            Long id,
            @NotNull String title,
            String description,
            @NotNull String condition,
            @NotNull int price,
            @NotNull int quantity,
            String cpu,
            int ramCapacity,
            String ramType,
            String gpuType,
            String goodFor
    ) {
        super(
                title,
                description,
                condition,
                price,
                quantity
        );
        this.id = id;
        this.cpu = cpu;
        this.ramCapacity = ramCapacity;
        this.ramType = ramType;
        this.gpuType = gpuType;
        this.goodFor = goodFor;
    }
}
