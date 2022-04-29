package ru.itis.stockmarket.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="country")
public class Country extends AbstractDictionary {
    @OneToMany(mappedBy = "country")
    private List<Organization> organizations;

    @OneToMany(mappedBy = "country")
    private List<Bank> banks;
}
