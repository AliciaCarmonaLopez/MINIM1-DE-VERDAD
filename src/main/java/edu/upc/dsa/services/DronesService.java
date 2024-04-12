package edu.upc.dsa.services;
import edu.upc.dsa.DronesManager;
import edu.upc.dsa.DronesManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/drones", description = "Endpoint to Partida Service")
@Path("/drones")
public class DronesService {
    private DronesManager dm;
    public DronesService() {
        this.dm = DronesManagerImpl.getInstance();

        if (dm.getListaDrones().size() == 0) {
            dm.addDron("11111", "DronChiquito", "Nike", "airforce");
            dm.addDron("22222", "DronGrande", "Nike", "airforce1");
            dm.addDron("33333", "DronMediano", "Nike", "airforce2");

            dm.addPiloto("aaaaa", "Alicia", "Carmona");
            dm.addPiloto("bbbbb", "Bruno", "Colitti");
            dm.addPiloto("aaaaa", "Vicenç", "Peñalver");

            dm.addReservaPlanVuelo("aaaaa", "11111", "2/23", 12, "12º/12º", "100º/100º");
            dm.addReservaPlanVuelo("bbbbb", "22222", "2/23", 10, "12º/12º", "100º/100º");
        }
    }
    @GET
    @ApiOperation(value = "listado de pilotos ordenados descendentemente por horas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PilotoTransfer.class, responseContainer="List")
    })
    @Path("/pilotosByHours")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPilotosByHours() {
        List<Piloto> pilotos = this.dm.pilotosByHours();
        List<PilotoTransfer> lp = new ArrayList<>();
        for(Piloto p: pilotos){
            PilotoTransfer pil = new PilotoTransfer(p.getIdentificador(),p.getNombre(),p.getApellidos(),p.getHorasVueloP());
            lp.add(pil);
        }
        GenericEntity<List<PilotoTransfer>> entity = new GenericEntity<List<PilotoTransfer>>(lp){};
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "listado de drones ordenados descendentemente por horas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = DronTransfer.class, responseContainer="List")
    })
    @Path("/dronesByHours")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDronesByHours() {
        List<Dron> drones = this.dm.dronesByHours();
        List<DronTransfer> lp = new ArrayList<>();
        for(Dron p: drones){
            DronTransfer pil = new DronTransfer(p.getIdentificador(), p.getNombre(),p.getFabricante(),p.getModelo(),p.getHorasVueloD());
            lp.add(pil);
        }
        GenericEntity<List<DronTransfer>> entity = new GenericEntity<List<DronTransfer>>(lp){};
        return Response.status(201).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "plan de vuelo del piloto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PlanVuelo.class, responseContainer="List")
    })
    @Path("/planVueloByPiloto/{idPiloto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanVueloByPiloto(@PathParam("idPiloto") String id) {
        List<PlanVuelo> p = this.dm.planVueloByPiloto(id);

        GenericEntity<List<PlanVuelo>> entity = new GenericEntity<List<PlanVuelo>>(p){};
        return Response.status(201).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "plan de vuelo del dron", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PlanVuelo.class, responseContainer="List")
    })
    @Path("/planVueloByDron/{idDron}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanVueloByDron(@PathParam("idDron") String id) {
        List<PlanVuelo> p = this.dm.planVueloByDron(id);

        GenericEntity<List<PlanVuelo>> entity = new GenericEntity<List<PlanVuelo>>(p){};
        return Response.status(201).entity(entity).build();
    }

    @PUT
    @ApiOperation(value = "Guardar dron en almacen de reparciones", notes = "indica en 'texto' el id del dron que quiere guardar")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/guardarEnAlmacen")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarEnAlmacen(Mensaje m) {
        dm.guardarEnAlmacen(m.getTexto());
        m.setTexto("Dron a la espera de ser reparado");
        return Response.status(201).entity(m).build();
    }

    @PUT
    @ApiOperation(value = "Reparar dron", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Dron not found")
    })
    @Path("/repararDron")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response repararDron() {
        Dron p = dm.repararDron();
        if(p == null){
            Mensaje m = new Mensaje("No hay drones a reparar");
            return Response.status(404).entity(m).build();
        }
        else{

            DronTransfer pil = new DronTransfer(p.getIdentificador(), p.getNombre(),p.getFabricante(),p.getModelo(),p.getHorasVueloD());
            return Response.status(201).entity(pil).build();
        }
    }

    @PUT
    @ApiOperation(value = "reservarPlanVuelo", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/reservarPlanVuelo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reservarPlanVuelo(PlanVueloTransfer p) {
        int res = dm.addReservaPlanVuelo(p.getIdPiloto(), p.getIdDron(),p.getFecha(), p.getDuracion(), p.getInicio(), p.getDestino());
        Mensaje m = new Mensaje();
        if(res == -1){
            m.setTexto("El Piloto o el Dron estan ocupados en ese periodo");
            return Response.status(404).entity(m).build();
        }
        else if(res == -2){
            m.setTexto("El dron esta en reparación");
            return Response.status(404).entity(m).build();
        }
        else{
            m.setTexto("Reserva realizada con exito");
            return Response.status(201).entity(m).build();
        }
    }
}
