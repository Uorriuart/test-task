package com.rincentral.test.services.impl;

import com.rincentral.test.storages.Storage;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;
import com.rincentral.test.services.api.CarService;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class CarServiceImpl implements CarService {
    private final List<CarFullInfo> carsStorage = Storage.getCarsStorage();
    private static final String PRESENT = "present";

    @Override
    public List<? extends CarInfo> getCars(CarRequestParameters params) {
        List<CarFullInfo> cars = carsStorage.stream()
                .filter(it -> isBlank(params.getCountry()) ||
                        it.getCountry().equals(params.getCountry()))

                .filter(it -> isBlank(params.getSegment()) ||
                        it.getSegment().equals(params.getSegment()))

                .filter(it -> params.getMinEngineDisplacement() == null ||
                        it.getEngine().getEngineDisplacement() >= params.getMinEngineDisplacement())

                .filter(it -> params.getMinEngineHorsepower() == null ||
                        it.getEngine().getHp() >= params.getMinEngineHorsepower())

                .filter(it -> params.getMinMaxSpeed() == null ||
                        it.getMaxSpeed() >= params.getMinMaxSpeed())

                .filter(it -> isBlank(params.getSearch()) ||
                        it.getModel().equals(params.getSearch()) ||
                        it.getGeneration().equals(params.getSearch()) ||
                        it.getModification().equals(params.getSearch()))

                .filter(it -> isBlank(params.getBodyStyle()) ||
                        it.getBody().getBodyStyle().equals(params.getBodyStyle()))

                .filter(it -> params.getYear() == null ||
                        isReleasedThisYear(it, params.getYear()))

                .collect(Collectors.toList());

        if (params.getIsFull() != null && params.getIsFull())
            return cars;
        else
            return cars.stream().map(this::toCarInfo).collect(Collectors.toList());
    }

    private boolean isReleasedThisYear(CarFullInfo carFullInfo, int year) {
        String[] releaseYearRange = carFullInfo.getYearsRange().split("-");
        int startYearRel = Integer.parseInt(releaseYearRange[0]);
        int endYearRel;

        if (releaseYearRange[1].equals(PRESENT))
            endYearRel = Year.now().getValue();
        else
            endYearRel = Integer.parseInt(releaseYearRange[1]);

        return year >= startYearRel && year <= endYearRel;
    }

    private CarInfo toCarInfo(CarFullInfo carFullInfo) {
        return CarInfo.builder()
                .id(carFullInfo.getId())
                .segment(carFullInfo.getSegment())
                .brand(carFullInfo.getBrand())
                .model(carFullInfo.getModel())
                .country(carFullInfo.getCountry())
                .generation(carFullInfo.getGeneration())
                .modification(carFullInfo.getModification())
                .build();
    }
}
