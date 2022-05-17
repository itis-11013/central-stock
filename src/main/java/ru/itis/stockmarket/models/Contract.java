package ru.itis.stockmarket.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime contractDate;

    private LocalDateTime paymentDate;

    private Date deliveryDate;

    @ManyToOne
    private Product product;

    @OneToOne
    private Organization buyer;

    private double count;

}
