package com.example.day8pokemon.controller;

import java.util.Optional;

import com.example.day8pokemon.models.Pokemon;
import com.example.day8pokemon.services.PokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonService pokemonSvc;

    @GetMapping(path="/search")
    public String search(
        @RequestParam(name = "pokemon_name") String pokemonName, Model model) {
            
            System.out.printf(">>> %s\n", pokemonName);

            Optional<Pokemon> opt = pokemonSvc.findPokemon(pokemonName);
            if (opt.isEmpty())
                return "404";

            Pokemon pokemon = opt.get();

            System.out.println("p = " + pokemon);
            
            model.addAttribute("pokemonName", pokemonName.toUpperCase());
            model.addAttribute("pokemon", pokemon);

            return "search_result";
        }
}
