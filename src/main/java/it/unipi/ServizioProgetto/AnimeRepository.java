/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.ServizioProgetto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author edoar
 */
public interface AnimeRepository extends CrudRepository<Anime, Long>{
    @Query(value = "SELECT A.* FROM utentianime UA INNER JOIN anime A ON A.id = UA.idanime WHERE UA.Username = ?1",
           nativeQuery = true)
    Iterable<Anime> findAnimeByUsername(String u);
    
    Anime findByName(String n);
}
