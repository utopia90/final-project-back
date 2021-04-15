package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String created_at;
    private String updated_at;
    private String linkedln;
    private String state;
    private String rating;


    @ManyToMany(mappedBy="expert", cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();


    public Expert() {
    }

    public Expert(String name, String surname, String dni, String address, String mail, String phone, String created_at, String updated_at, String linkedln, String state, String rating, List<Tag> tags) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.linkedln = linkedln;
        this.state = state;
        this.rating = rating;
        this.tags = tags;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    @Override
    public String toString() {
        return "Expert{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", linkedln='" + linkedln + '\'' +
                ", state='" + state + '\'' +
                ", rating='" + rating + '\'' +
                ", tags=" + tags +
                '}';
    }
}
