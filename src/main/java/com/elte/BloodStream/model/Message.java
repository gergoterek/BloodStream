package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer msgId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime posted;

    @JsonIgnore
    //@JsonBackReference
    //@Column(name = "donor_id")
    @ManyToOne
    private Donor donor;

    //@JsonIgnore
    @OneToMany(mappedBy = "message")
    private List<Donation> donation;

}
