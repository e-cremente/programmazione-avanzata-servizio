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
@Table(name="utentianime", schema="564817")
public class UtenteAnime implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="username")
    private String username;
    @Column(name="idanime")
    private String idanime;
    @Column(name="ownscore")
    private Double ownscore = 0.0;
    @Column(name="notes", columnDefinition="varchar(1000)")
    private String notes = "";
    
    public UtenteAnime() {
    }

    public UtenteAnime(String username, String idanime) {
        this.username = username;
        this.idanime = idanime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdanime() {
        return idanime;
    }

    public void setIdanime(String idanime) {
        this.idanime = idanime;
    }

    public Double getOwnscore() {
        return ownscore;
    }

    public void setOwnscore(Double ownscore) {
        this.ownscore = ownscore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
