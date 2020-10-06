package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    @Column
    @CreationTimestamp
    private LocalDateTime appliedAt;

    @Column
    private boolean hasAppeared;

    //@JsonIgnore
    //@Column(name = "PLACE_ID")
    @ManyToOne
    private DonationPlace place;
	
	//@JsonIgnore
    @ManyToOne
    private Donor donor;
}
