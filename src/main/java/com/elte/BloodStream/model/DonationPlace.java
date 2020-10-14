package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonProperty(access = READ_ONLY)
    @Column(name = "PLACE_ID")
    private Integer ID;                         //placeID

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

//    @JsonIgnore
//    @OneToMany(mappedBy = "place")
//    private List<Donation> donations;

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private List<Application> appliedUsers;

}