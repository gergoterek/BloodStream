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
public class AppliedUsers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    @Column
    @CreationTimestamp
    private LocalDateTime appliedAt;

    @JsonIgnore
    @ManyToOne
    private DonationPlaces place;
	
	@JsonIgnore
    @ManyToOne
    private Donors donor;
}
