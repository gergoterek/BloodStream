package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String response;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime sendAt;

    //@JsonIgnore
    @OneToOne//(mappedBy = "donationId")
    @JoinColumn(referencedColumnName = "donationId", name = "donation_id")
    private Donation donation;
}
