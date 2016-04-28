package com.nucleus;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by erik on 19/04/16.
 */
public class NucleonGun implements INucleonGun{

    private ArrayList<INucleon> nucleonList = new ArrayList<INucleon>();
    private int arrayIndex;
    private int width;
    private int height;

    public NucleonGun(int width, int height, ArrayList<INucleon> nucleons){
        nucleonList = nucleons;
        this.height = height;
        this.width = width;
        arrayIndex = nucleonList.size() - 1;
    }
    //Temporary method
    public int getAmmoLeft(){
        return nucleonList.size();
    }

    @Override
    public INucleon shoot() {
        INucleon a = nucleonList.get(arrayIndex);
        nucleonList.remove(arrayIndex);
        arrayIndex --;

        //sets the start position for the nucleon
        Random rand = new Random();
        int x = rand.nextInt(4);
        
        switch(x){
            //along the top screen edge
            case 0:
                a.setPosition(rand.nextInt(width) , height);
                break;
            //along the bottom screen edge
            case 1:
                a.setPosition(rand.nextInt(width) , 0);
                break;

            //along the left screen edge
            case 2:
                a.setPosition(0 , rand.nextInt(height));
                break;

            //along the right screen edge
            case 3:
                a.setPosition(width, rand.nextInt(height));
                break;

            default:
                break;
            }

            //sets the next position for the nucleon ELLER?
            //a.setVelocity((int)(2*(Math.random()/10+0.01)), (int)(height/2*(Math.random()/10+0.01)));

        //Test -  super temp!!
        Vector velocityVect = new Vector(width/2,height/2).subtract(a.getPosition());
        a.setVelocity(velocityVect.getX(), velocityVect.getY());
        //a.setVelocity((int)(velocityVect.getX()*0.5), (int)(velocityVect.getY()*0.5));
        return a;
    }

    @Override
    public boolean isEmpty() {
        if (arrayIndex >= 0) {
            return false;
        } else {
            return true;
        }
    }
}
