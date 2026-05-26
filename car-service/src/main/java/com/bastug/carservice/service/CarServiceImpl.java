package com.bastug.carservice.service;

import com.bastug.carservice.dtos.CarResponse;
import com.bastug.carservice.dtos.CreateCarRequest;
import com.bastug.carservice.dtos.CustomerResponse;
import com.bastug.carservice.dtos.UpdateCarRequest;
import com.bastug.carservice.entity.Car;
import com.bastug.carservice.enums.FuelType;
import com.bastug.carservice.exception.CarNotFoundException;
import com.bastug.carservice.exception.DuplicatePlateException;
import com.bastug.carservice.feign.CustomerClient;
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
    private final CustomerClient customerClient;

    @Override
    public CarResponse createCar(CreateCarRequest createCarRequest) {
        if(carRepository.existsByPlate(createCarRequest.getPlate())){
            throw new DuplicatePlateException(createCarRequest.getPlate());
        }
        CustomerResponse customer=customerClient.getCustomer(createCarRequest.getCustomerId());

        Car car=carMapper.toCar(createCarRequest);
        car.setCustomerId(createCarRequest.getCustomerId());
        carRepository.save(car);
        CarResponse carResponse= carMapper.toCarResponse(car);
        carResponse.setCustomerResponse(customer);
        return carResponse;
    }

    @Override
    public Page<CarResponse> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(car->{
                    CarResponse carResponse=carMapper.toCarResponse(car);
                    carResponse.setCustomerResponse(customerClient.getCustomer(car.getCustomerId()));
                    return carResponse;
                });
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    @Override
    public CarResponse updateCar(Long id, UpdateCarRequest request) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car=optionalCar.get();
            car.setCustomerId(request.getCustomerId());
            carMapper.updateCar(request,car);
            carRepository.save(car);
            return carMapper.toCarResponse(car);
        }
        return null;
    }

    @Override
    public CarResponse getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map((car)->{
            CarResponse carResponse=carMapper.toCarResponse(car);
            carResponse.setCustomerResponse(customerClient.getCustomer(car.getCustomerId()));
            return carResponse;
        }).orElseThrow(()-> new CarNotFoundException(id));
    }

    @Override
    public Page<CarResponse> getCarsByBrand(String brand, Pageable pageable) {
        return carRepository.findByBrandContainingIgnoreCase(brand,pageable).map(car->{
            CarResponse carResponse=carMapper.toCarResponse(car);
            carResponse.setCustomerResponse(customerClient.getCustomer(car.getCustomerId()));
            return carResponse;
        });
    }

    @Override
    public Page<CarResponse> getCarsByFuelType(FuelType fuelType, Pageable pageable) {
        return carRepository.findByFuelType(fuelType,pageable).map(car->{
            CarResponse carResponse=carMapper.toCarResponse(car);
            carResponse.setCustomerResponse(customerClient.getCustomer(car.getCustomerId()));
            return carResponse;
        });
    }

    @Override
    public Page<CarResponse> getCarsByBrandAndFuelType(FuelType fuelType, String brand, Pageable pageable) {
        return carRepository.findByFuelTypeAndBrandContainingIgnoreCase(fuelType,brand,pageable).map(car->{
            CarResponse carResponse=carMapper.toCarResponse(car);
            carResponse.setCustomerResponse(customerClient.getCustomer(car.getCustomerId()));
            return carResponse;
        });
    }
}
