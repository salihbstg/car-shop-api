package com.bastug.carservice.service;

import com.bastug.carservice.dtos.CarResponse;
import com.bastug.carservice.dtos.CreateCarRequest;
import com.bastug.carservice.dtos.UpdateCarRequest;
import com.bastug.carservice.enums.FuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CarService {
    CarResponse createCar(CreateCarRequest createCarRequest,String token);

    Page<CarResponse> getAllCars(Pageable pageable);

    void deleteCar(Long id);

    CarResponse updateCar(Long id, UpdateCarRequest request);

    CarResponse getCarById(Long id);

    Page<CarResponse> getCarsByBrand(
            String brand,
            Pageable pageable
    );

    Page<CarResponse> getCarsByFuelType(
            FuelType fuelType,
            Pageable pageable);

    Page<CarResponse> getCarsByBrandAndFuelType(FuelType fuelType, String brand, Pageable pageable);

    List<CarResponse> getCarsByCustomerId(Long id);
}
