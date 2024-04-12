package edu.upc.dsa.models;

public class PlanVueloTransfer {
    private String idPiloto;
    private String idDron;
    private String fecha;

    public PlanVueloTransfer() {
    }

    public PlanVueloTransfer(String idPiloto, String idDron, String fecha, double duracion, String inicio, String destino) {
        this.idPiloto = idPiloto;
        this.idDron = idDron;
        this.fecha = fecha;
        this.duracion = duracion;
        this.inicio = inicio;
        this.destino = destino;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getIdDron() {
        return idDron;
    }

    public void setIdDron(String idDron) {
        this.idDron = idDron;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    private double duracion; //horas
    private String inicio; // lat/long
    private String destino;

}
