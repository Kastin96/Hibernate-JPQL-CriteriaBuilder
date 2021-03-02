package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@PersistenceUnit(name = "client")
public class Client extends AbstractUser {
    @Column(name = "full_name")
    private String name;

    private Integer age;

    @Column(name = "role")
    private Integer roleNumber;

    @ManyToOne()
    @JoinColumn(name = "role", insertable = false, updatable = false)
    private Roles role;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_booking",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id")
    )
    private Set<Booking> bookings = new LinkedHashSet<>();

    public Client withId(Integer id) {
        setId(id);
        return this;
    }

    public Client withLogin(String login) {
        setLogin(login);
        return this;
    }

    public Client withPassword(String password) {
        setPassword(password);
        return this;
    }

    public Client withName(String name) {
        setName(name);
        return this;
    }

    public Client withAge(int age) {
        setAge(age);
        return this;
    }

    public Client withRoleNumber(Integer roleNumber) {
        setRoleNumber(roleNumber);
        return this;
    }

    public Client withRole(Roles role) {
        setRole(role);
        return this;
    }

    public Client withOrders(Set<Order> orders) {
        setOrders(orders);
        return this;
    }

    public Client withBookings(Set<Booking> bookings) {
        setBookings(bookings);
        return this;
    }
}
