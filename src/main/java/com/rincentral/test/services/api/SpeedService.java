package com.rincentral.test.services.api;

import com.rincentral.test.models.params.MaxSpeedRequestParameters;

import java.util.OptionalDouble;

public interface SpeedService {

    OptionalDouble getAverageMaxSpeed(MaxSpeedRequestParameters requestParameters);
}
