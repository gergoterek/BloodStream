package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonProperty(access = READ_ONLY)
    private Integer donationId;

    @Column
    @CreationTimestamp
    private LocalDateTime donationDate;

    @Column(nullable = false)
    private boolean isUsed;


    //    @JsonIgnore


    //@JsonIgnore
    //@Column(name = "DONOR_ID")
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "DONOR_ID", name = "DONOR_ID")
//    private Donor donor;

    //@JsonIgnore
//    @JoinColumn(referencedColumnName = "PLACE_ID", name = "PLACE_ID")
//    @ManyToOne
//    private DonationPlace place;

}
