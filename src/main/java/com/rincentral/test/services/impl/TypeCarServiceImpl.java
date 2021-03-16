package com.rincentral.test.services.impl;

import com.rincentral.test.storages.Storage;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.services.api.TypeCarService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TypeCarServiceImpl implements TypeCarService {
    private final List<CarFullInfo> carsStorage = Storage.getCarsStorage();
    private List<String> bodyStyles;
    private List<String> fuelTypes;
    private List<String> engineTypes;
    private List<String> wheelDrives;
    private List<String> gearboxTypes;

    @Override
    public List<String> getFuelTypes() {
        if (fuelTypes == null) {
            fuelTypes = carsStorage.stream()
                    .map(it -> it.getEngine().getFuelType().name())
                    .distinct()
                    .collect(toList());
        }
        return fuelTypes;
    }

    @Override
    public List<String> getBodyStyles() {
        if (bodyStyles == null) {
            bodyStyles = carsStorage.stream()
                    .flatMap(it -> Arrays.stream(it.getBody().getBodyStyle().split("\\s*,\\s*")))
                    .distinct()
                    .collect(toList());
        }
        return bodyStyles;
    }

    @Override
    public List<String> getEngineTypes() {
        if (engineTypes == null) {
            engineTypes = carsStorage.stream()
                    .map(it -> it.getEngine().getEngineType().name())
                    .distinct()
                    .collect(toList());
        }
        return engineTypes;
    }

    @Override
    public List<String> getWheelDrives() {
        if (wheelDrives == null) {
            wheelDrives = carsStorage.stream()
                    .map(it -> it.getWheelDriveType().name())
                    .distinct()
                    .collect(toList());
        }
        return wheelDrives;
    }

    @Override
    public List<String> getGearboxTypes() {
        if (gearboxTypes == null) {
            gearboxTypes = carsStorage.stream()
                    .map(it -> it.getGearboxType().name())
                    .distinct()
                    .collect(toList());
        }
        return gearboxTypes;
    }
}
