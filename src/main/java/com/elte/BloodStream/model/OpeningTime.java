package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningTime {

    //@JsonProperty(access = READ_ONLY)
    @Id
    private int timeId;

    @MapsId
    @OneToOne
    @JoinColumn(referencedColumnName = "PLACE_ID", name = "place_id")
    private DonationPlace donationPlace;

    @NotNull
    @Column
    private boolean monday;

    @NotNull
    @Column
    private boolean tuesday;

    @NotNull
    @Column
    private boolean wednesday;

    @NotNull
    @Column
    private boolean thursday;

    @NotNull
    @Column
    private boolean friday;

    @NotNull
    @Column
    private boolean saturday;

    @NotNull
    @Column
    private boolean sunday;

    @NotNull
    @Column
    private int openingTime;

    @NotNull
    @Column
    private int closingTime;

}
