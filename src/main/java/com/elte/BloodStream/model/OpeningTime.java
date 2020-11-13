package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningTime {

    //@JsonProperty(access = READ_ONLY)
    @Id
    @Column(updatable = false)
    private int id;


    @OneToOne//(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(referencedColumnName = "PLACE_ID", name = "place_id")
    @MapsId
    private Place place;


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
    private int startTime;

    @NotNull
    @Column
    private int closingTime;

}
