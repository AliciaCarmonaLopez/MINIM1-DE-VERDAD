package edu.upc.dsa;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestDronesManagerImpl {
    DronesManager dm;

    @Before
    public void setUp() throws Exception {
        this.dm = new DronesManagerImpl();
        dm.addDron("11111", "DronChiquito", "Nike", "airforce");
        dm.addDron("22222", "DronGrande", "Nike", "airforce1");


        dm.addPiloto("aaaaa", "Alicia", "Carmona");
        dm.addPiloto("bbbbb", "Bruno", "Colitti");

        dm.addReservaPlanVuelo("aaaaa", "11111", "2/23", 12, "12º/12º", "100º/100º");
        dm.addReservaPlanVuelo("bbbbb", "22222", "2/23", 10, "12º/12º", "100º/100º");

    }
    @After
    public void tearDown(){this.dm = null;}


    @Test
    public void testAddPiloto() throws Exception{
        Assert.assertEquals(2, this.dm.getListaDrones().size());
        dm.addDron("33333", "DronMediano", "Nike", "airforce2");
        Assert.assertEquals(3, this.dm.getListaDrones().size());
    }
    @Test
    public void testAddDron() throws Exception{
        Assert.assertEquals(2, this.dm.getListaPilotos().size());
        dm.addPiloto("aaaaa", "Vicenç", "Peñalver");
        Assert.assertEquals(2, this.dm.getListaPilotos().size());
    }

    @Test
    public void testPilotosByHours() throws  Exception{
        List<Piloto> lp = dm.pilotosByHours();
        Assert.assertEquals("Alicia", lp.get(0).getNombre());
    }

    @Test
    public void testDronesByHours() throws  Exception{
        List<Dron> ld = dm.dronesByHours();
        Assert.assertEquals("11111", ld.get(0).getIdentificador());
    }



}