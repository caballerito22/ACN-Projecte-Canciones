package com.caballero;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Cancion {
    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("añoLanzamiento")
    private int añoLanzamiento;

    @JsonProperty("reproducciones")
    private int reproducciones;

    @JsonProperty("imagen")
    private String imagen;

    // Constructor con todos los atributos
    public Cancion(String titulo, int añoLanzamiento, int reproducciones, String imagen) {
        this.titulo = titulo;
        this.añoLanzamiento = añoLanzamiento;
        this.reproducciones = reproducciones;
        this.imagen = imagen;
    }

    // Constructor vacío
    public Cancion() {}

    // Getters y setters
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

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", añoLanzamiento=" + añoLanzamiento +
                ", reproducciones=" + reproducciones +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
