package com.elte.BloodStream.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DONOR_ID", updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String username;


    @JsonProperty(access = WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String donorName;

    @Column(nullable = false)
    private boolean male;

    @Column(columnDefinition="tinyint(1) default 0")
    private int totalDonations = 0;


    @Enumerated(EnumType.STRING)
    private BloodTypes bloodType;



    public enum BloodTypes {
        A_POZ, A_NEG, B_POZ, B_NEG, ZERO_POZ, ZERO_NEG, AB_POZ, AB_NEG
    }

    @Column(nullable = false)
    private int TAJ;

    @Column(nullable = false)
    private String idCard;

    @Column(nullable = false)
    private Date birthDate;

    @Column
    private LocalDateTime nextDonationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_GUEST, ROLE_DONOR, ROLE_NURSE, ROLE_ADMIN
    }

    @JsonIgnore
    @OneToMany(mappedBy = "donor")
    private List<Message> messages;


    @JsonIgnore
    @OneToMany(mappedBy = "donor")
    private List<Application> applications;

}
