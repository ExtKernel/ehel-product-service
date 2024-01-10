package org.tes.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class LaptopProductEntity extends BaseProduct {

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

    @NotNull
    @Column(name = "release_date")
    private Date releaseDate;

    @NotNull
    @Column(name = "comes_with_charger")
    private boolean comesWithCharger;

    @NotNull
    @Column(name = "screen_type")
    private String screenType;

    @NotNull
    @Column(name = "screen_resolution")
    private String screenResolution;

    @NotNull
    @Column(name = "has_backlit")
    private boolean hasBacklit;

    @Column(name = "battery_health")
    private int batteryHealth;

    @NotNull
    @Column(name = "model")
    private String model;
}
