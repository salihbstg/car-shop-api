package com.bastug.carservice.repository;


import com.bastug.carservice.entity.Car;
import com.bastug.carservice.enums.FuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findByBrandContainingIgnoreCase(String brand, Pageable pageable);

    Page<Car> findByFuelType(FuelType fuelType, Pageable pageable);

    Page<Car> findByFuelTypeAndBrandContainingIgnoreCase(FuelType fuelType, String brand, Pageable pageable);

    boolean existsByPlate(String plate);
}
