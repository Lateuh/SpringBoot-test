package com.example.SpringBoottest.repositories;

import com.example.SpringBoottest.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
