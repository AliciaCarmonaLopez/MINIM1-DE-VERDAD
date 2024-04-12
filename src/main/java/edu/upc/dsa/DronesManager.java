package edu.upc.dsa;
import edu.upc.dsa.Queue.Queue;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.PlanVuelo;

import java.util.HashMap;
import java.util.List;

public interface DronesManager {
    public void addDron(String id, String nom, String fabricante, String modelo);
    public void addPiloto(String id, String nom, String apellido);
    public List<Dron> dronesByHours();
    public List<Piloto> pilotosByHours();
    public void guardarEnAlmacen(String idDron);
    public Dron repararDron();
    public int addReservaPlanVuelo(String idPiloto, String idDron, String fecha, double duracion, String inicio, String destino);
    public List<PlanVuelo> planVueloByPiloto(String idPiloto);
    public List<PlanVuelo> planVueloByDron(String idDron);

    public HashMap<String, Piloto> getListaPilotos();
    public HashMap<String, Dron> getListaDrones();
}
