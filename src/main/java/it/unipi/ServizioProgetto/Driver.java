/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.ServizioProgetto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edoar
 */

@Entity
@Table(name="progetto", schema="progetto")
public class Driver implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    //il nome della colonna si può anche mettere in maiuscolo, ma solo la prima lettera,
    //altrimenti non gli piace. per fare prima metto tutto minuscolo e via
    @Column(name="name")
    private String name;
    @Column(name="lastname")
    private String lastName;
    @Column(name="nationality")
    private String nationality;
    @Column(name="birthdate")
    private String birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Driver() {
    }

    public Driver(Integer id, String name, String lastName, String nationality, String birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
    }
    
    
}
