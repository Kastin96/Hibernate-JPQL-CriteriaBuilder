package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = "clients")
@EqualsAndHashCode(exclude = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    private Integer cost;

    @ManyToMany(mappedBy = "bookings", cascade = CascadeType.ALL)
    private Set<Client> clients  = new LinkedHashSet<>();

    public Booking withId(Integer id) {
        setId(id);
        return this;
    }

    public Booking withProductName(String productName) {
        setProductName(productName);
        return this;
    }

    public Booking withCost(Integer cost) {
        setCost(cost);
        return this;
    }
}
