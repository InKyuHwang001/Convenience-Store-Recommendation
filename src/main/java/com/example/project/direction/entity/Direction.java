package com.example.project.direction.entity;

import com.example.project.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Entity(name = "direction")
@NoArgsConstructor(access = PROTECTED)
public class Direction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //현위치
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    //목적지
    private String targetStoreName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    private double distance;

    @Builder
    public Direction(Long id, String inputAddress, double inputLatitude, double inputLongitude, String targetStoreName, String targetAddress, double targetLatitude, double targetLongitude, double distance) {
        this.id = id;
        this.inputAddress = inputAddress;
        this.inputLatitude = inputLatitude;
        this.inputLongitude = inputLongitude;
        this.targetStoreName = targetStoreName;
        this.targetAddress = targetAddress;
        this.targetLatitude = targetLatitude;
        this.targetLongitude = targetLongitude;
        this.distance = distance;
    }
}