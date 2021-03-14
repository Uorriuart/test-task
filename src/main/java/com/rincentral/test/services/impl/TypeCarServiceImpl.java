package com.rincentral.test.services.impl;

import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.services.api.TypeCarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class TypeCarServiceImpl implements TypeCarService {
    private final List<CarFullInfo> carsStorage;

    @Override
    public List<String> getFuelTypes()  {
        return carsStorage.stream()
                .map(it -> it.getEngine().getFuelType().name())
                .distinct()
                .collect(toList());
    }

    @Override
    public List<String> getBodyStyles()  {
        return carsStorage.stream()
                .flatMap(it -> Arrays.stream(it.getBody().getBodyStyle().split("\\s*,\\s*")))
                .distinct()
                .collect(toList());
    }

    @Override
    public List<String> getEngineTypes()  {
        return carsStorage.stream()
                .map(it -> it.getEngine().getEngineType().name())
                .distinct()
                .collect(toList());
    }

    @Override
    public List<String> getWheelDrives()  {
        return carsStorage.stream()
                .map(it -> it.getWheelDriveType().name())
                .distinct()
                .collect(toList());
    }

    @Override
    public List<String> getGearboxTypes()  {
        return carsStorage.stream()
                .map(it -> it.getGearboxType().name())
                .distinct()
                .collect(toList());
    }
}
