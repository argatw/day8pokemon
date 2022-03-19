package com.example.day8pokemon.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Pokemon {

    private static String[] IMAGES = {
        "sprites", "versions", "generation-i", "red-blue" };

    private String name;
    private List<String> images = new LinkedList<>();
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public void addImage(String img) { this.images.add(img); }

    @Override
    public String toString() {
        return "Pokemon [images=" + images + ", name=" + name + "]";
    }

    public static Pokemon create(String json) throws IOException {
        Pokemon p = new Pokemon();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is); //Reads a JSON object or an array structure from an input source
            JsonObject o = r.readObject();  //provides unmodifiable map view to the JSON object name/value mappings
            p.setName(o.getString("name"));

            for (String i: IMAGES)
                o = o.getJsonObject(i);
            List<String> l = o.values().stream()
                    .filter(v -> v != null) //filtering
                    .map(v -> v.toString().replaceAll("\"", "")) //fetch, simple lamda function, pass v, return v that replaceAll
                    .toList();
            p.setImages(l);
        }
        return p;
    }
    
    


}
