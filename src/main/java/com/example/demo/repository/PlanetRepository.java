package com.example.demo.repository;


import com.example.demo.entity.Lord;
import com.example.demo.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    @Query
    List<Planet> getAllByLordOfPlanetIsNull();
}
