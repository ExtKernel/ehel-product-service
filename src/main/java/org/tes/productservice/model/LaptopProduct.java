package org.tes.productservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
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
@Entity
public class LaptopProduct extends CompProduct {

    @NotNull
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
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


    public LaptopProduct(
            Long id,
            String title,
            String description,
            String condition,
            int price,
            int quantity,
            String cpu,
            int ramCapacity,
            String ramType,
            String gpuType,
            String goodFor,
            Date releaseDate,
            boolean comesWithCharger,
            String screenType,
            String screenResolution,
            boolean hasBacklit,
            String model
    ) {
        super(
                id,
                title,
                description,
                condition,
                price,
                quantity,
                cpu,
                ramCapacity,
                ramType,
                gpuType,
                goodFor
        );
        this.releaseDate = releaseDate;
        this.comesWithCharger = comesWithCharger;
        this.screenType = screenType;
        this.screenResolution = screenResolution;
        this.hasBacklit = hasBacklit;
        this.model = model;
    }
}
