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
    @GeneratedValue
    private UUID innerId;
    private Date contractDate;
    private Date paymentDate;
    private Date deliveryDate;

    @ManyToOne
    private Product product;
    private double count;

}
