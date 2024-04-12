package edu.upc.dsa.models;

public class PilotoTransfer {
    private String identificador;
    private String nombre;
    private String apellidos;
    private double horasVueloP;
    public PilotoTransfer() {
    }

    public PilotoTransfer(String identificador, String nombre, String apellidos, double horasVueloP) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horasVueloP = horasVueloP;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getHorasVueloP() {
        return horasVueloP;
    }

    public void setHorasVueloP(double horasVueloP) {
        this.horasVueloP = horasVueloP;
    }
}
