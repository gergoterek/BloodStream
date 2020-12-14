package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer applyId;


    @Column
    private Date appliedDate;

    @Nullable
    @Column(updatable = false)
    private Long directedDonationCode;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "PLACE_ID", name = "place_id")
    private Place place;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "DONOR_ID", name = "donor_id")
    private Donor donor;

    @OneToOne()
    @JoinColumn(referencedColumnName = "donationId", name = "donation_id")
    private Donation donation;
}