package com.caballero;

public class Cancion {
    private String titulo;
    private int añoLanzamiento;
    private String cantante;

    public Cancion(String titulo, int añoLanzamiento, String cantante) {
        this.titulo = titulo;
        this.añoLanzamiento = añoLanzamiento;
        this.cantante = cantante;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public String getCantante() {
        return cantante;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAñoLanzamiento(int añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", añoLanzamiento=" + añoLanzamiento +
                ", cantante='" + cantante + '\'' +
                '}';
    }
}
