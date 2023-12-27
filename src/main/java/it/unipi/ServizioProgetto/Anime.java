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
@Table(name="anime", schema="564817")
public class Anime implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    //il nome della colonna si può anche mettere in maiuscolo, ma solo la prima lettera,
    //altrimenti non gli piace. per fare prima metto tutto minuscolo e via
    @Column(name="name")
    private String name;
    /*@Column(name="lastname")
    private String lastName;
    @Column(name="nationality")
    private String nationality;
    @Column(name="birthdate")
    private String birthDate;*/

    public Anime() {
    }

    public Anime(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
       
}
