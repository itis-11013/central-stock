package ru.itis.stockmarket.models;


import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizationGen")
    @SequenceGenerator(name = "organizationGen", sequenceName = "organization_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String address;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
