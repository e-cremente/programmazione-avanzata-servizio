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
    
    //restituisce tutti i record della tabella anime
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Anime> getAllAnime(){
        return animeRepository.findAll();
    }
    
    //restituisce un anime in base al titolo
    @PostMapping(path="/title")
    public @ResponseBody Anime getAnimeByName(@RequestParam String name){
        return animeRepository.findByName(name);
    }
    
    //aggiunge un anime alla tabella degli anime
    @PostMapping(path="/add")
    public @ResponseBody String addNewAnime(@RequestBody Anime a){
        animeRepository.save(a);
        return "Saved";
    }
    
    //se l'utente non è presente nel database, lo salva nella tabella utenti
    @PostMapping(path="/utente")
    public @ResponseBody String addNewUtente(@RequestBody Utente u){
        Utente trovato = utenteRepository.findByUsername(u.getUsername());
        if(trovato == null){
            utenteRepository.save(u);
            return "OK";
        }
        return "NO";
    }
    
    //cerca un utente tramite nome e password per vedere se i dati sono corretti. se lo sono da l'ok per il login
    @PostMapping(path="/login")
    public @ResponseBody String loginUtente(@RequestBody Utente u){
        Utente trovato = utenteRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
        if(trovato == null){
            return "NO";
        }
        return "OK";
    }
    
    //restituisce tutti gli anime appartenenti a un determinato utente da mostrare nella lista personale
    @PostMapping(path="/join")
    public @ResponseBody Iterable<Anime> joinUtenteAnime(@RequestParam String username){
        return animeRepository.findAnimeByUsername(username);
    }
    
    //se non è già presente, aggiunge un anime alla lista personale dell'utente
    @PostMapping(path="/addtouserlist")
    public @ResponseBody String addToUserList(@RequestBody UtenteAnime ua){
        UtenteAnime trovato = utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        if(trovato == null){
            utenteAnimeRepository.save(ua);
            return "OK";
        }
        return "NO";
    }
    
    //controlla se un anime appartiene a un determinato utente
    @PostMapping(path="/findjoin")
    public @ResponseBody String findJoin(@RequestBody UtenteAnime ua){
        UtenteAnime trovato = utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        if(trovato == null){
            return "NO";
        }
        return "OK";
    }
    
    //aggiorna i campi dello score personale e delle note personali per uno specifico anime di uno specifico utente
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
    
    //restituisce un oggetto UtenteAnime dal quale verranno letti le note e lo score personali
    @PostMapping(path="/getscorenotes")
    public @ResponseBody UtenteAnime getScoreNotes(@RequestBody UtenteAnime ua){
        return utenteAnimeRepository.findByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
    }
    
    //annotazione da inserire se si usa il comando di delete
    @Transactional
    //rimuove un anime dalla lista personale dell'utente
    @PostMapping(path="/remove")
    public @ResponseBody String removeAnime(@RequestBody UtenteAnime ua){
        utenteAnimeRepository.deleteByIdanimeAndUsername(ua.getIdanime(), ua.getUsername());
        return "OK";
    }
   
}
