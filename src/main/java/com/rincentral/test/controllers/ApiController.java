package com.rincentral.test.controllers;

import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;
import com.rincentral.test.models.params.MaxSpeedRequestParameters;
import com.rincentral.test.services.api.CarService;
import com.rincentral.test.services.api.SpeedService;
import com.rincentral.test.services.api.TypeCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    CarService service;
    TypeCarService typesCarService;
    SpeedService speedService;

    @Autowired
    public ApiController(CarService service, TypeCarService typesCarService, SpeedService speedService) {
        this.service = service;
        this.typesCarService = typesCarService;
        this.speedService = speedService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<? extends CarInfo>> getCars(@Valid CarRequestParameters requestParameters) {
        List<? extends CarInfo> result = service.getCars(requestParameters);
        return result.isEmpty()? ResponseEntity.notFound().build() : ResponseEntity.ok(service.getCars(requestParameters));
    }

    @GetMapping("/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes() {
        return ResponseEntity.ok(typesCarService.getFuelTypes());
    }

    @GetMapping("/body-styles")
    public ResponseEntity<List<String>> getBodyStyles() {
        return ResponseEntity.ok(typesCarService.getBodyStyles());
    }

    @GetMapping("/engine-types")
    public ResponseEntity<List<String>> getEngineTypes() {
        return ResponseEntity.ok(typesCarService.getEngineTypes());
    }

    @GetMapping("/wheel-drives")
    public ResponseEntity<List<String>> getWheelDrives() {
        return ResponseEntity.ok(typesCarService.getWheelDrives());
    }

    @GetMapping("/gearboxes")
    public ResponseEntity<List<String>> getGearboxTypes() {
        return ResponseEntity.ok(typesCarService.getGearboxTypes());
    }

    @GetMapping("/max-speed")
    public ResponseEntity<Double> getMaxSpeed(MaxSpeedRequestParameters requestParameters) {
        if ((requestParameters.getBrand() == null && requestParameters.getModel() == null) ||
                (requestParameters.getBrand() != null && requestParameters.getModel() != null)) {
            return ResponseEntity.badRequest().build();
        }
        Double result = speedService.getAverageMaxSpeed(requestParameters);

        return result.equals(-1.0)? ResponseEntity.notFound().build() : ResponseEntity.ok(speedService.getAverageMaxSpeed(requestParameters));
    }
}