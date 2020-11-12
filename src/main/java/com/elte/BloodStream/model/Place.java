package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;

import javax.persistence.*;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonProperty(access = READ_ONLY)
    @Column(name = "PLACE_ID")
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private List<Application> appliedUsers;


    @JsonManagedReference
    @OneToOne(mappedBy = "place")//, cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private OpeningTime openingTime;


}
