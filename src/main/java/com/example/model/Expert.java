package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="experts")
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String dni;
    private String address;
    private String mail;
    private String phone;
    private String linkedln;
    private String state;
    private String rating;
    private String availability;



    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "experts_tags",
            joinColumns = {@JoinColumn(name="tags_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="experts_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();


    public Expert() {
    }

    public Expert(String name, String surname, String dni, String address, String mail, String phone, String linkedln, String state, String rating, List<Tag> tags, String availability) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.linkedln = linkedln;
        this.state = state;
        this.rating = rating;
        this.tags = tags;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedln() {
        return linkedln;
    }

    public void setLinkedln(String linkedln) {
        this.linkedln = linkedln;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", linkedln='" + linkedln + '\'' +
                ", state='" + state + '\'' +
                ", rating='" + rating + '\'' +
                ", tags=" + tags +
                '}';
    }
}
