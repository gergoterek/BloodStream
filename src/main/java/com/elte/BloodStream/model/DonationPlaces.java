package com.elte.BloodStream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationPlaces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;                         //placeID

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "place")
    private List<Donations> donations;

    @OneToMany(mappedBy = "place")
    private List<AppliedUsers> appliedUsers;

}
