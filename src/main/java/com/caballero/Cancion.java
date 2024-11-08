package com.caballero;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Cancion {
    @JsonProperty("titulo")
    private String titulo;
    @JsonProperty("añoLanzamiento")
    private int añoLanzamiento;

    public Cancion(String titulo, int añoLanzamiento) {
        this.titulo = titulo;
        this.añoLanzamiento = añoLanzamiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public void setAñoLanzamiento(int añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", añoLanzamiento=" + añoLanzamiento +
                '}';
    }
}
