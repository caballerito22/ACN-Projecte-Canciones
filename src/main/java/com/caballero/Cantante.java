package com.caballero;

public class Cantante {
    private String nombre;
    private int añoNacimiento;
    private String pais;

    public Cantante(String nombre, int añoNacimiento, String pais) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Cantante{" +
                "nombre='" + nombre + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", pais='" + pais + '\'' +
                '}';
    }
}
