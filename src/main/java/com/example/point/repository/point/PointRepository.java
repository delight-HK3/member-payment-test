package com.example.point.repository.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.point.domain.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long>{
    
}
