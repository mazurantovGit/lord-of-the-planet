package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NamedEntityGraph(name = "lord.listPlanets",
        attributeNodes = @NamedAttributeNode("listPlanets")
)
public class Lord {
    private String name;
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lordOfPlanet")
    private List<Planet> listPlanets;

    public void addToListPlanet(Planet planet){
        listPlanets.add(planet);
    }

}
