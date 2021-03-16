package com.rincentral.test.services.impl;

import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.params.MaxSpeedRequestParameters;
import com.rincentral.test.services.api.SpeedService;
import com.rincentral.test.storages.Storage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

@Service
public class SpeedServiceImpl implements SpeedService {
    private final List<CarFullInfo> carsStorage = Storage.getCarsStorage();

    @Override
    public OptionalDouble getAverageMaxSpeed(MaxSpeedRequestParameters requestParameters) {
        Stream<CarFullInfo> filteredCarsStream;

        if (requestParameters.getModel() != null) {
            filteredCarsStream = carsStorage.stream()
                    .filter(it -> requestParameters.getModel().equals(it.getModel()));
        } else {
            filteredCarsStream = carsStorage.stream()
                    .filter(it -> requestParameters.getBrand().equals(it.getBrand()));
        }

        return filteredCarsStream.mapToDouble(CarFullInfo::getMaxSpeed).average();
    }
}
