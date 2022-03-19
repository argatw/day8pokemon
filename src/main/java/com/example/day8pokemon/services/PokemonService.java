package com.example.day8pokemon.services;

import java.util.Optional;

import com.example.day8pokemon.models.Pokemon;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {
    private static final String URL = "https://pokeapi.co/api/v2/pokemon/%s";

    public Optional<Pokemon> findPokemon(String name) {
        String toSearch = URL.formatted(name);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            resp = template.getForEntity(toSearch, String.class);
        } catch (Exception ex) {
            //TODO: handle exception
            System.err.printf("Exception: %s\n", ex.getMessage());
            return Optional.empty();
        }

        if (resp.getStatusCodeValue() >= 400)
            return Optional.empty();


        try {
            // throw exception if status code >= 400
            Pokemon pokemon = Pokemon.create(resp.getBody());
            return Optional.of(pokemon);
        } catch (Exception ex) {
            //TODO: handle exception
            ex.printStackTrace();
        }
        return Optional.empty();

    }


}
