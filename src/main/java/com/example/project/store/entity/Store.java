package com.example.project.store.entity;

import com.example.project.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity(name = "store")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String storeName;

    @Column(name = "address")
    private String storeAddress;

    private double latitude;

    private double longitude;


    @Builder
    public Store(Long id, String storeName, String storeAddress, double latitude, double longitude) {
        this.id = id;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
