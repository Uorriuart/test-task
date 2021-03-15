package com.rincentral.test.services.impl;

import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.params.MaxSpeedRequestParameters;
import com.rincentral.test.services.api.SpeedService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
@AllArgsConstructor
public class SpeedServiceImpl implements SpeedService {
    private final List<CarFullInfo> carsStorage;

    @Override
    public double getAverageMaxSpeed(MaxSpeedRequestParameters requestParameters) {
        OptionalDouble opDouble;
        if (requestParameters.getModel() != null) {
            opDouble = carsStorage.stream()
                    .filter(it -> requestParameters.getModel().equals(it.getModel()))
                    .mapToDouble(CarFullInfo::getMaxSpeed)
                    .average();
        } else {
            opDouble = carsStorage.stream()
                    .filter(it -> requestParameters.getBrand().equals(it.getBrand()))
                    .mapToDouble(CarFullInfo::getMaxSpeed)
                    .average();
        }
        return opDouble.orElse(-1.0);
    }
}
