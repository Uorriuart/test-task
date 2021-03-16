package com.rincentral.test.services.api;

import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;

import java.util.List;

public interface CarService {

    List<? extends CarInfo> getCars(CarRequestParameters params);
}
