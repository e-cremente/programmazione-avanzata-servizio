/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.ServizioProgetto;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author edoar
 */
public interface UtenteRepository extends CrudRepository<Utente, Long>{
    Utente findByUsername(String s);
    Utente findByUsernameAndPassword(String u, String p);
}
