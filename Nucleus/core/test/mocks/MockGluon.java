package mocks;

import com.nucleus.IGluonPoint;
import com.nucleus.Vector;

/**
 * Created by erik on 22/04/16.
 */
public class MockGluon implements IGluonPoint {

    private Vector position;
    private int protonsNeeded;
    private int neutronsNeeded;
    private final int radius = 2; //DUmmy value will be defined later

    public MockGluon(int posX, int posY, int pNeeded, int nNeeded){
        this.position = new Vector(posX, posY);
        this.protonsNeeded = pNeeded;
        this.neutronsNeeded = nNeeded;
    }

    public boolean isFull(){
       return false;
    }
    public Vector getPosition(){
        return position;
    }
    public void setPosition(int x, int y){
        position.setCoordinates(x, y);
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public boolean addProton() {
        return true;
    }

    @Override
    public int getProtons() {
        return 0;
    }

    @Override
    public boolean addNeutron() {
        return true;
    }

    @Override
    public int getNeutrons() {
        return 0;
    }
}
