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
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer msgId;

    @Column(nullable = false)
    private String message;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime sendAt;

    @JsonIgnore
    @ManyToOne
    private Donors donor;

}
