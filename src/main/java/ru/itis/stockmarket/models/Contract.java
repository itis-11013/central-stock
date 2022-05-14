package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankGen")
    @SequenceGenerator(name = "bankGen", sequenceName = "bank_seq", allocationSize = 1)
    private Long id;

    private UUID innerId;
    private Date contractDate;
    private Date paymentDate;
    private Date deliveryDate;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> product;
    private double count;

}
