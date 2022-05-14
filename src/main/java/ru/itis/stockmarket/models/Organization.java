package ru.itis.stockmarket.models;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizationGen")
    @SequenceGenerator(name = "organizationGen", sequenceName = "organization_seq", allocationSize = 1)
    private Long id;

    private UUID innerId;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
