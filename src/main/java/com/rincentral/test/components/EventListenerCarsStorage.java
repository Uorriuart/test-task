package com.rincentral.test.components;

import com.rincentral.test.models.BodyCharacteristics;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.EngineCharacteristics;
import com.rincentral.test.models.external.ExternalBrand;
import com.rincentral.test.models.external.ExternalCar;
import com.rincentral.test.models.external.ExternalCarInfo;
import com.rincentral.test.services.api.ExternalCarsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventListenerCarsStorage {
    private final ExternalCarsApiService service;
    private final List<CarFullInfo> carFullInfoStorage;

    @EventListener
    public void parseDataToStorage(ContextRefreshedEvent event) {
        List<ExternalCar> exCarsList = service.loadAllCars();
        Map<Integer, ExternalBrand> exBrandsMap = service.loadAllBrands().stream()
                .collect(Collectors.toMap(ExternalBrand::getId, exBrand -> exBrand));

        exCarsList.forEach(it -> {
            ExternalCarInfo exCarInfo = service.loadCarInformationById(it.getId());

            ExternalBrand exBrand = exBrandsMap.get(it.getBrandId());
            String brand = exBrand.getTitle();
            String country = exBrand.getCountry();

            var engine = getEngineCharacteristics(exCarInfo);
            var body = getBodyCharacteristics(exCarInfo);
            var carFullInfo = getCarFullInfo(exCarInfo, brand, country, engine, body);

            carFullInfoStorage.add(carFullInfo);
        });
    }

    private EngineCharacteristics getEngineCharacteristics(ExternalCarInfo exCarInfo) {
        return EngineCharacteristics.builder()
                .fuelType(exCarInfo.getFuelType())
                .engineType(exCarInfo.getEngineType())
                .engineDisplacement(exCarInfo.getEngineDisplacement())
                .hp(exCarInfo.getHp())
                .build();
    }

    private BodyCharacteristics getBodyCharacteristics(ExternalCarInfo exCarInfo) {
        return BodyCharacteristics.builder()
                .bodyLength(exCarInfo.getBodyLength())
                .bodyWidth(exCarInfo.getBodyWidth())
                .bodyHeight(exCarInfo.getBodyHeight())
                .bodyStyle(exCarInfo.getBodyStyle())
                .build();
    }

    private CarFullInfo getCarFullInfo(ExternalCarInfo exCarInfo, String brand, String country, EngineCharacteristics engine, BodyCharacteristics body) {
        return CarFullInfo.builder()
                .id(exCarInfo.getId())
                .segment(exCarInfo.getSegment())
                .brand(brand)
                .model(exCarInfo.getModel())
                .country(country)
                .generation(exCarInfo.getGeneration())
                .modification(exCarInfo.getModification())
                .yearsRange(exCarInfo.getYearsRange())
                .engine(engine)
                .gearboxType(exCarInfo.getGearboxType())
                .wheelDriveType(exCarInfo.getWheelDriveType())
                .body(body)
                .acceleration(exCarInfo.getAcceleration())
                .maxSpeed(exCarInfo.getMaxSpeed())
                .build();
    }

    @Bean
    public List<CarFullInfo> getCarFullInfoStorage() {
        return carFullInfoStorage;
    }
}
