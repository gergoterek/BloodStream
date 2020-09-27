package com.elte.BloodStream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GYIK {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gyikId;

    @Column(nullable = false)
    private String text;
}
