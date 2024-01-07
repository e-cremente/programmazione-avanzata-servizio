/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.ServizioProgetto;

import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edoar
 */
@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private UtenteAnimeRepository utenteAnimeRepository;
    private static final Logger logger = LogManager.getLogger(MainController.class);
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Anime> getAllAnime(){
        return animeRepository.findAll();
    }
    
    @PostMapping(path="/title")
    public @ResponseBody Anime getAnimeByName(@RequestParam String name){
        return animeRepository.findByName(name);
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewAnime(@RequestBody Anime a){
        animeRepository.save(a);
        return "Saved";
    }
    
    @PostMapping(path="/utente")
    public @ResponseBody String addNewUtente(@RequestBody Utente u){
        Utente trovato = utenteRepository.findByUsername(u.getUsername());
        if(trovato == null){
            utenteRepository.save(u);
            return "OK";
        }
        return "NO";
    }
    
    @PostMapping(path="/login")
    public @ResponseBody String loginUtente(@RequestBody Utente u){
        Utente trovato = utenteRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
        if(trovato == null){
            return "NO";
        }
        return "OK";
    }
    
    @PostMapping(path="/join")
    public @ResponseBody Iterable<Anime> joinUtenteAnime(@RequestParam String username){
        return animeRepository.findAnimeByUsername(username);
    }
    
    @PostMapping(path="/addtouserlist")
    public @ResponseBody String addToUserList(@RequestBody UtenteAnime ua){
        UtenteAnime trovato = utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        if(trovato == null){
            utenteAnimeRepository.save(ua);
            return "OK";
        }
        return "NO";
    }
    
    @PostMapping(path="/findjoin")
    public @ResponseBody String findJoin(@RequestBody UtenteAnime ua){
        UtenteAnime trovato = utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        if(trovato == null){
            return "NO";
        }
        return "OK";
    }
    
    @PostMapping(path="/addscorenotes")
    public @ResponseBody String addScoreNotes(@RequestBody UtenteAnime ua){
        UtenteAnime trovato = utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        if(trovato == null){
            return "NO";
        }
        trovato.setNotes(ua.getNotes());
        trovato.setOwnscore(ua.getOwnscore());
        utenteAnimeRepository.save(trovato);
        return "OK";
    }
    
    @PostMapping(path="/getscorenotes")
    public @ResponseBody UtenteAnime getScoreNotes(@RequestBody UtenteAnime ua){
        return utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
    }
    
    //annotazione da inserire se si usa il comando di delete
    @Transactional
    @PostMapping(path="/remove")
    public @ResponseBody String removeAnime(@RequestBody UtenteAnime ua){
        utenteAnimeRepository.deleteByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        return "OK";
    }
   
}
