package edu.upc.dsa;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.PlanVuelo;
import org.apache.log4j.Logger;
import edu.upc.dsa.Queue.Queue;
import edu.upc.dsa.Queue.QueueImpl;
import edu.upc.dsa.Queue.EmptyQueueException;
import edu.upc.dsa.Queue.FullQueueException;

import java.util.*;

public class DronesManagerImpl implements DronesManager {
    final static Logger logger = Logger.getLogger(DronesManagerImpl.class);
    private HashMap<String, Piloto> listaPilotos;
    private HashMap<String, Dron> listaDrones;
    private Queue<Dron> colaAlmacenReparaciones;
    public DronesManagerImpl(){
        listaDrones = new HashMap<>();
        listaPilotos = new HashMap<>();
        colaAlmacenReparaciones = new QueueImpl<>(3);
    }

    private static DronesManager instance;
    public static DronesManager getInstance() {
        if (instance==null) instance = new DronesManagerImpl();
        return instance;
    }

    @Override
    public void addDron(String id, String nom, String fabricante, String modelo) {
        logger.info("Dron a añadir: " + id + nom + fabricante + modelo);
        Dron d = new Dron(id, nom, fabricante, modelo);
        listaDrones.put(id, d);
        logger.info("Dron añadido correctamente");
    }

    @Override
    public void addPiloto(String id, String nom, String apellido) {
        logger.info("Dron a añadir: " + id + nom + apellido);
        Piloto p = new Piloto(id, nom, apellido);
        listaPilotos.put(id, p);
        logger.info("Dron añadido correctamente");
    }

    @Override
    public List<Dron> dronesByHours() {
        logger.info("Lista de drones por horas");
        List<Dron> ld = new ArrayList<>();
        for(String s:listaDrones.keySet()){
            ld.add(listaDrones.get(s));
        }
        Comparator<Dron> hourscomparator = Comparator.comparingDouble(Dron::getHorasVueloD);
        ld.sort(hourscomparator.reversed());
        for(Dron d:ld){
            logger.info(d.getIdentificador()+ d.getHorasVueloD());
        }
        return ld;
    }

    @Override
    public List<Piloto> pilotosByHours() {
        logger.info("Lista de drones por horas");
        List<Piloto> lp = new ArrayList<>();
        for(String s:listaPilotos.keySet()){
            lp.add(listaPilotos.get(s));
        }
        Comparator<Piloto> hourscomparator = Comparator.comparingDouble(Piloto::getHorasVueloP);
        lp.sort(hourscomparator.reversed());
        for(Piloto p:lp){
            logger.info(p.getIdentificador()+ p.getHorasVueloP());
        }
        return lp;
    }

    @Override
    public void guardarEnAlmacen(String idDron) {
        logger.info("El dron con id: " + idDron + " quiere entrar en almacen reparaciones");
        Dron d = listaDrones.get(idDron);
        try{colaAlmacenReparaciones.push(d);}
        catch(FullQueueException fullExp){
            logger.warn("El almacen esta lleno");
            fullExp.printStackTrace();
        }
        listaDrones.remove(idDron);
        logger.info("Dron añadidio correctamente");

    }

    @Override
    public Dron repararDron() {
        Dron d = new Dron();
        try{d = colaAlmacenReparaciones.pop();}
        catch(EmptyQueueException empExp){
            logger.warn("El almacen esta vacío");
            empExp.printStackTrace();
            return null;
        }
        listaDrones.put(d.getIdentificador(), d);
        logger.info("El dron con id: " + d.getIdentificador() + " ha sido reparado");
        return d;
    }

    @Override
    public int addReservaPlanVuelo(String idPiloto, String idDron, String fecha, double duracion, String inicio, String destino) {
        logger.info("Se quiere hacer una reserva para:" + idPiloto + idDron);
        Piloto p = listaPilotos.get(idPiloto);
        Dron d = listaDrones.get(idDron);
        double dia1 = Double.parseDouble(fecha.split("/")[0]);
        double hora1 = Double.parseDouble(fecha.split("/")[1]);
        double dia2 = duracion/24;
        double hora2 = (duracion%24)*24;
        if(d!=null){
            List<PlanVuelo> planPiloto = p.getPlanVueloP();
            List<PlanVuelo> planDron = d.getPlanVueloD();
            if((planDron.size() == 0) && (planPiloto.size() == 0)){
                PlanVuelo plan = new PlanVuelo(idPiloto, idDron, fecha, duracion,inicio,destino);
                listaPilotos.get(idPiloto).addPlanP(plan);
                listaDrones.get(idDron).addPlanD(plan);
                logger.info("Reserva hecha con exito");
                listaPilotos.get(idPiloto).setHorasVueloP(listaPilotos.get(idPiloto).getHorasVueloP() + duracion);
                listaDrones.get(idDron).setHorasVueloD(listaDrones.get(idDron).getHorasVueloD()+duracion);
                return 0;
            }
            else{
                boolean encontradoP =  mirarDisponiblidad(planPiloto, dia1, dia2, hora1, hora2);
                boolean encontradoD = mirarDisponiblidad(planDron, dia1, dia2, hora1, hora2);
                if((encontradoD==true) && (encontradoP==true)){
                    PlanVuelo plan = new PlanVuelo(idPiloto, idDron, fecha, duracion,inicio,destino);
                    listaPilotos.get(idPiloto).addPlanP(plan);
                    listaDrones.get(idDron).addPlanD(plan);
                    logger.info("Reserva hecha con exito");
                    listaPilotos.get(idPiloto).setHorasVueloP(listaPilotos.get(idPiloto).getHorasVueloP() + duracion);
                    listaDrones.get(idDron).setHorasVueloD(listaDrones.get(idDron).getHorasVueloD()+duracion);
                    return 0;
                }
                else{
                    logger.info("Dron o piloto ocupados");
                    return -1; // Fecha ocupada
                }


            }
        }
        else{
            logger.info("Dron en el almacen");
            return -2; // dron en el almacen
        }

    }
    public boolean mirarDisponiblidad(List<PlanVuelo> planPiloto, double dia1, double dia2, double hora1, double hora2){

        for(PlanVuelo pl: planPiloto){
            if((dia1>pl.getDia())||(pl.getDia()<dia2)){
                if((hora1>pl.getHora())||(pl.getHora()<hora2)){
                    return true; //
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        return true;
    }
    @Override
    public List<PlanVuelo> planVueloByPiloto(String idPiloto) {
        logger.info("Plan de vuelo solicitado para: " + idPiloto);
        List<PlanVuelo> p = listaPilotos.get(idPiloto).getPlanVueloP();
        return p;
    }

    @Override
    public List<PlanVuelo> planVueloByDron(String idDron) {
        logger.info("Plan de vuelo solicitado para: " + idDron);
        List<PlanVuelo> p = listaDrones.get(idDron).getPlanVueloD();
        return p;
    }

    @Override
    public HashMap<String, Piloto> getListaPilotos() {
        return listaPilotos;
    }

    @Override
    public HashMap<String, Dron> getListaDrones() {
        return listaDrones;
    }
}
