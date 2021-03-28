package com.example.demo.repository;

import com.example.demo.entity.Lord;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
    @Query
    @EntityGraph(value= "lord.listPlanets")
    List<Lord> findTop10ByOrderByAge();

    @Query
    List<Lord> getAllByListPlanetsIsNull();

}