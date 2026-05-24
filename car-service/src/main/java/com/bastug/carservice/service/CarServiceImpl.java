package com.bastug.carservice.service;

import com.bastug.carservice.dtos.CarResponse;
import com.bastug.carservice.dtos.CreateCarRequest;
import com.bastug.carservice.dtos.UpdateCarRequest;
import com.bastug.carservice.entity.Car;
import com.bastug.carservice.enums.FuelType;
import com.bastug.carservice.exception.CarNotFoundException;
import com.bastug.carservice.exception.DuplicatePlateException;
import com.bastug.carservice.mapper.CarMapper;
import com.bastug.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarResponse createCar(CreateCarRequest createCarRequest) {
        if(carRepository.existsByPlate(createCarRequest.getPlate())){
            throw new DuplicatePlateException(createCarRequest.getPlate());
        }
        return carMapper.toCarResponse(carRepository.save(carMapper.toCar(createCarRequest)));
    }

    @Override
    public Page<CarResponse> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(carMapper::toCarResponse);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    @Override
    public CarResponse updateCar(Long id, UpdateCarRequest request) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            carMapper.updateCar(request, car.get());
            carRepository.save(car.get());
            return carMapper.toCarResponse(car.get());
        }
        return null;
    }

    @Override
    public CarResponse getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(carMapper::toCarResponse).orElseThrow(()-> new CarNotFoundException(id));
    }

    @Override
    public Page<CarResponse> getCarsByBrand(String brand, Pageable pageable) {
        return carRepository.findByBrandContainingIgnoreCase(brand,pageable).map(carMapper::toCarResponse);
    }

    @Override
    public Page<CarResponse> getCarsByFuelType(FuelType fuelType, Pageable pageable) {
        return carRepository.findByFuelTypeContainingIgnoreCase(fuelType,pageable).map(carMapper::toCarResponse);
    }

    @Override
    public Page<CarResponse> getCarsByBrandAndFuelType(FuelType fuelType, String brand, Pageable pageable) {
        return carRepository.findByFuelTypeAndBrandContainingIgnoreCase(fuelType,brand,pageable).map(carMapper::toCarResponse);
    }
}
