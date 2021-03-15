package com.rincentral.test.models.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestParameters {
    @Size(max = 56)
    private String country;

    @Size(max = 50)
    private String segment;

    @Min(value = 0)
    @Max(Integer.MAX_VALUE)
    private Double minEngineDisplacement;

    @Min(value = 0)
    @Max(Integer.MAX_VALUE)
    private Integer minEngineHorsepower;

    @Min(value = 0)
    @Max(Integer.MAX_VALUE)
    private Integer minMaxSpeed;

    @Size(max = 50)
    private String search;

    private Boolean isFull;

    @Min(value = 0)
    @Max(Integer.MAX_VALUE)
    private Integer year;

    @Size(max = 50)
    private String bodyStyle;
}
