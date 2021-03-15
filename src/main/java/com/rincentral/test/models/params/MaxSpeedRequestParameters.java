package com.rincentral.test.models.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaxSpeedRequestParameters {
    @Size(max = 50)
    private String brand;

    @Size(max = 50)
    private String model;
}
