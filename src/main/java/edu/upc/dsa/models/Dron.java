package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Dron {
    private String identificador;
    private String nombre;
    private String fabricante;
    private String modelo;
    private double horasVueloD;
    private List<PlanVuelo> planVueloD;
    public void addPlanD(PlanVuelo p){
        planVueloD.add(p);
    }
    public Dron() {
    }

    public Dron(String identificador, String nombre, String fabricante, String modelo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.horasVueloD = 0;
        this.planVueloD = new ArrayList<>();
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

    public List<PlanVuelo> getPlanVueloD() {
        return planVueloD;
    }

    public void setPlanVueloD(List<PlanVuelo> planVueloD) {
        this.planVueloD = planVueloD;
    }


}
