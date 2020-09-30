package com.elte.BloodStream.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;                 //donorID

    @Column(nullable = false)
    @JsonProperty(access = WRITE_ONLY)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String donorName;

    @Column(columnDefinition="tinyint(1) default 0")            //(name = "correct_q", columnDefinition="tinyint(1) default 0")
    private int totalDonations = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BloodTypes bloodType;

    public enum BloodTypes {
        A_POZ, A_NEG, B_POZ, B_NEG, ZERO_POZ, ZERO_NEG, AB_POZ, AB_NEG
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }

    @OneToMany(mappedBy = "donor")
    private List<Messages> messages;

    @OneToMany(mappedBy = "donor")
    private List<Donations> donations;

    @OneToMany(mappedBy = "donor")
    private List<AppliedUsers> appliedUsers;

}
