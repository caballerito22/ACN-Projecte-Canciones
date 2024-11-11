package com.caballero;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cantante {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("añoNacimiento")
    private int añoNacimiento;

    @JsonProperty("pais")
    private String pais;

    @JsonProperty("imagen")
    private String imagen;

    @JsonProperty("canciones")
    private List<Cancion> canciones;



    public Cantante(String nombre, int añoNacimiento, String pais, List<Cancion> canciones) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.pais = pais;
        this.imagen = imagen;
        this.canciones = canciones;
    }
public Cantante() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Cantante{" +
                "nombre='" + nombre + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", pais='" + pais + '\'' +
                ", imagen='" + imagen + '\'' +
                ", canciones=" + canciones +
                '}';
    }
}

