package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = READ_ONLY)
    private Integer applyId;

    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    //"appliedTime": "2016-10-30T14:22:25",

    @Column
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    //"appliedTime": "2019-04-02 11:45",
    private LocalDateTime appliedTime;

    @Column
    private boolean hasAppeared;

    //@JsonIgnore
    //@JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "PLACE_ID", name = "place_id")
    private DonationPlace place;
	
    //@JsonBackReference
	//@JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "DONOR_ID", name = "donor_id")
    private Donor donor;

}