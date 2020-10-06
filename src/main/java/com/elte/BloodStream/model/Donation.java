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
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donationId;

    @Column
    @CreationTimestamp
    private LocalDateTime donationTime;

    @Column(nullable = false)
    private boolean isUsed;

    //@JsonIgnore
    //@Column(name = "DONOR_ID")
    @ManyToOne
    //@JoinColumn(referencedColumnName = "ID")
    private Donor donor;

    //@JsonIgnore
    //@Column(name = "PLACE_ID")
    @ManyToOne
    private DonationPlace place;

    //@Column(name = "MSG_ID")
    @ManyToOne
    private Message message;
}
