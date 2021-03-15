package com.rincentral.test.services.api;

import com.rincentral.test.models.params.MaxSpeedRequestParameters;

public interface SpeedService {

    double getAverageMaxSpeed(MaxSpeedRequestParameters requestParameters);
}
