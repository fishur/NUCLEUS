package Model;

import org.junit.Test;
import static org.junit.Assert.*;

import com.nucleus.Model.Nucleon;
import com.nucleus.Model.INucleon;
import com.nucleus.Utils.Vector;

/**
 * Created by erik on 19/04/16.
 */
public class NucleonTest {

    @Test
    public void testUpdate() {
        INucleon nucleon = new Nucleon(new Vector(10,10), new Vector(10,10));
        nucleon.update(100);
        assertFalse(nucleon.getPosition().getX() == 10);
        assertFalse(nucleon.getPosition().getY() == 10);
        assertTrue(nucleon.getPosition().getX() == 1010);
        assertTrue(nucleon.getPosition().getY() == 1010);
        nucleon.update(-100);
        assertTrue(nucleon.getPosition().getX() == 10);
        assertTrue(nucleon.getPosition().getY() == 10);
    }

    @Test
    public void testGetPosition() {
        INucleon nucleon = new Nucleon(new Vector(1, 1), new Vector(2, 2));
        assertTrue(nucleon.getPosition().getX() == 1);
        assertTrue(nucleon.getPosition().getY() == 1);
    }


    @Test
    public void testGetVelocity(){
        INucleon nucleon = new Nucleon(new Vector(2,2), new Vector(3,3));
        assertTrue(nucleon.getVelocity().getX() == 3);
        assertTrue(nucleon.getVelocity().getY() == 3);
    }

    @Test
    public void testSetPosition(){
        INucleon nucleon = new Nucleon(new Vector(1,1), new Vector(2,2));
        assertTrue(nucleon.getPosition().getX() == 1);
        assertTrue(nucleon.getPosition().getY() == 1);
        nucleon.setPosition(7,7);
        assertTrue(nucleon.getPosition().getY() == 7);
        assertTrue(nucleon.getPosition().getY() == 7);
    }

    @Test
    public void testSetVelocity(){
        INucleon nucleon = new Nucleon(new Vector(2,2), new Vector(5,5));
        assertTrue(nucleon.getVelocity().getX() == 5);
        assertTrue(nucleon.getVelocity().getY() == 5);
        nucleon.setVelocity(10,10);
        assertTrue(nucleon.getVelocity().getY() == 10);
        assertTrue(nucleon.getVelocity().getY() == 10);
    }

    @Test
    public void testGetRadius(){
        INucleon nucleon = new Nucleon(new Vector(1,1), new Vector(1,1));
        assertFalse(nucleon.getRadius() == 0);
        assertTrue(nucleon.getRadius() == 10);
    }
}
