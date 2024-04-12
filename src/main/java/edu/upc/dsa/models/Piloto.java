package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    private String identificador;
    private String nombre;
    private String apellidos;
    private double horasVueloP;
    private List<PlanVuelo> planVueloP;
    public void addPlanP(PlanVuelo p){
        planVueloP.add(p);
    }

    public Piloto() {
    }

    public Piloto(String identificador, String nombre, String apellidos) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horasVueloP = 0;
        this.planVueloP = new ArrayList<>();
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

    public List<PlanVuelo> getPlanVueloP() {
        return planVueloP;
    }

    public void setPlanVueloP(List<PlanVuelo> planVueloP) {
        this.planVueloP = planVueloP;
    }
}
