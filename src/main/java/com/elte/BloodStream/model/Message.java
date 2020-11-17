package com.elte.BloodStream.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = READ_ONLY)
    @Column(updatable = false)
    private Integer msgId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime sendDate;

    @NotNull
    @Column
    private Boolean seen = false;

    //@JsonBackReference
    @ManyToOne()
    @JoinColumn(referencedColumnName = "DONOR_ID", name = "donor_id")
    //@JsonIgnore
    private Donor donor;


//    @JsonIgnore
    @OneToOne
    @JoinColumn(referencedColumnName = "applyId", name = "apply_id")
    private Application application;
}
