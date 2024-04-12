package edu.upc.dsa.models;

public class PlanVuelo {
    private String idPiloto;
    private String idDron;
    private double dia;// Dia/hora
    private double hora;
    private double duracion; //horas
    private String inicio; // lat/long
    private String destino;

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


    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
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

    public PlanVuelo() {
    }

    public double getDia() {
        return dia;
    }

    public void setDia(double dia) {
        this.dia = dia;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }

    public PlanVuelo(String idPiloto, String idDron, String fecha, double duracion, String inicio, String destino) {
        this.idPiloto = idPiloto;
        this.idDron = idDron;
        this.dia = Double.parseDouble(fecha.split("/")[0]);
        this.hora = Double.parseDouble(fecha.split("/")[1]);
        this.duracion = duracion;
        this.inicio = inicio;
        this.destino = destino;
    }
}
