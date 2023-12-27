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
public interface AnimeRepository extends CrudRepository<Anime, Integer>{
    Anime findByName(String n);
    long count();
}
