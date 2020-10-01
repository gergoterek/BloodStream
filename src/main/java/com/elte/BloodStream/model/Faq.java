package com.elte.BloodStream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer faqId;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(nullable = false, length = 1000)
    private String answer;
}
