package com.bastug.carservice.service;

import com.bastug.carservice.dtos.CarResponse;
import com.bastug.carservice.dtos.CreateCarRequest;
import com.bastug.carservice.entity.Car;
import com.bastug.carservice.enums.CarColor;
import com.bastug.carservice.enums.FuelType;
import com.bastug.carservice.enums.TransmissionType;
import com.bastug.carservice.exception.DuplicatePlateException;
import com.bastug.carservice.mapper.CarMapper;
import com.bastug.carservice.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void shouldThrowDuplicatePlateExceptionWhenPlateAlreadyExists() {

        CreateCarRequest createCarRequest = new CreateCarRequest();
        createCarRequest.setPlate("Plate");
        createCarRequest.setModel("Model");
        createCarRequest.setYear(2020);
        createCarRequest.setCarColor(CarColor.BEIGE);
        createCarRequest.setBrand("Brand");
        createCarRequest.setTransmissionType(TransmissionType.AUTOMATIC);
        createCarRequest.setFuelType(FuelType.HYBRID);

        when(carRepository.existsByPlate("Plate")).thenReturn(true);

        assertThrows(DuplicatePlateException.class, () -> carService.createCar(createCarRequest));
        verify(carRepository).existsByPlate("Plate");

    }

    @Test
    void shouldReturnCarResponseById() {

        CarResponse carResponse = new CarResponse();
        carResponse.setPlate("Plate");
        carResponse.setModel("Model");
        carResponse.setYear(2020);
        carResponse.setCarColor(CarColor.BEIGE);
        carResponse.setBrand("Brand");
        carResponse.setTransmissionType(TransmissionType.AUTOMATIC);
        carResponse.setFuelType(FuelType.HYBRID);

        Car car = new Car();
        car.setPlate("Plate");
        car.setModel("Model");
        car.setYear(2020);
        car.setCarColor(CarColor.BEIGE);
        car.setBrand("Brand");
        car.setTransmissionType(TransmissionType.AUTOMATIC);
        car.setFuelType(FuelType.HYBRID);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carMapper.toCarResponse(car)).thenReturn(carResponse);

        CarResponse result = carService.getCarById(1L);

        assertEquals(carResponse.getPlate(), result.getPlate());
        assertEquals(carResponse.getModel(), result.getModel());
        assertEquals(carResponse.getYear(), result.getYear());
        assertEquals(carResponse.getCarColor(), result.getCarColor());
        assertEquals(carResponse.getBrand(), result.getBrand());
        assertEquals(carResponse.getTransmissionType(), result.getTransmissionType());
        assertEquals(carResponse.getFuelType(), result.getFuelType());

        verify(carRepository).findById(1L);
        verify(carMapper).toCarResponse(car);


    }

    @Test
    void shouldReturnCarsByFuelType() {

        Car car1 = new Car();
        car1.setFuelType(FuelType.HYBRID);
        Car car2 = new Car();
        car2.setFuelType(FuelType.HYBRID);
        List<Car> cars = List.of(car1, car2);
        CarResponse carResponse1=new CarResponse();
        carResponse1.setFuelType(FuelType.HYBRID);
        CarResponse carResponse2=new CarResponse();
        carResponse2.setFuelType(FuelType.HYBRID);

        Pageable pageable= PageRequest.of(0,2);
        Page<Car> carPage= new PageImpl<>(cars,pageable,cars.size());

        when(carRepository.findByFuelTypeContainingIgnoreCase(FuelType.HYBRID,pageable)).thenReturn(carPage);
        when(carMapper.toCarResponse(car1)).thenReturn(carResponse1);
        when(carMapper.toCarResponse(car2)).thenReturn(carResponse2);

        Page<CarResponse> result=carService.getCarsByFuelType(FuelType.HYBRID,pageable);

        assertEquals(2,result.getContent().size());

        verify(carRepository).findByFuelTypeContainingIgnoreCase(FuelType.HYBRID,pageable);
        verify(carMapper,times(2)).toCarResponse(any(Car.class));
    }
}
