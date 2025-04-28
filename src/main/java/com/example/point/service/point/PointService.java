package com.example.point.service.point;

import org.springframework.stereotype.Service;

import com.example.point.repository.point.PointRepository;

/**
 * 포인트 관련 Service
 */
@Service
public class PointService {
    
    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository){
        this.pointRepository = pointRepository;
    }

}
