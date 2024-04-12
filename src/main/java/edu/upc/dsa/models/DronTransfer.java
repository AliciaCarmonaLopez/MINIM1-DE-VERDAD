package edu.upc.dsa.models;

public class DronTransfer {
    private String identificador;
    private String nombre;
    private String fabricante;
    private String modelo;
    private double horasVueloD;

    public DronTransfer() {
    }

    public DronTransfer(String identificador, String nombre, String fabricante, String modelo, double horasVueloD) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.horasVueloD = horasVueloD;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getHorasVueloD() {
        return horasVueloD;
    }

    public void setHorasVueloD(double horasVueloD) {
        this.horasVueloD = horasVueloD;
    }
}
