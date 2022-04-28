package ru.itis.stockmarket.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dict_country")
public class Country extends AbstractDictionary {
}
