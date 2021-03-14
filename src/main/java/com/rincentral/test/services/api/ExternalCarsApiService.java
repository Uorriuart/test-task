package com.rincentral.test.services.api;

import com.rincentral.test.models.external.ExternalBrand;
import com.rincentral.test.models.external.ExternalCar;
import com.rincentral.test.models.external.ExternalCarInfo;

import java.util.List;

public interface ExternalCarsApiService {

    List<ExternalCar> loadAllCars();

    ExternalCarInfo loadCarInformationById(int id);

    List<ExternalBrand> loadAllBrands();

}
