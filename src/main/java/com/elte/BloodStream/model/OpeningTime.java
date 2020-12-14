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

    @Id
    @Column(updatable = false)
    private Integer id;


    @OneToOne
    @JsonBackReference
    @JoinColumn(referencedColumnName = "PLACE_ID", name = "place_id")
    @MapsId
    private Place place;

    @Column
    private boolean monday;

    @Column
    private boolean tuesday;

    @Column
    private boolean wednesday;

    @Column
    private boolean thursday;

    @Column
    private boolean friday;

    @Column
    private boolean saturday;

    @Column
    private boolean sunday;

    @Column
    private int startTime;

    @Column
    private int closingTime;

}
