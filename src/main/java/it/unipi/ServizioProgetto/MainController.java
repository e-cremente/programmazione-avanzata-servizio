/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.ServizioProgetto;

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
    
    public long count(){
        return animeRepository.count();
    }
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Anime> getAllAnime(){
        return animeRepository.findAll();
    }
    
    @PostMapping(path="/driver")
    public @ResponseBody Anime getAnimeByName(@RequestParam String name){
        return animeRepository.findByName(name);
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewAnime(@RequestBody Anime a){
        animeRepository.save(a);
        return "Saved";
    }
}
