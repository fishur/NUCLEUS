package com.nucleus;

/**
 * Created by erik on 19/04/16.
 */
public class Nucleon implements  INucleon {

    private Vector position;
    private Vector velocity;
    private final int radius = 0;   //Dummy value. Sizes will be used for collision detection purposes
                                    //and will be properly determined later.

    public Nucleon(){

    }

    public Nucleon(int posX, int posY, int velX, int velY){
        this.position = new Vector(posX, posY);
        this.velocity = new Vector(velX, velY);
    }

    public void update(float delta){
        position = position.add(velocity.multiply(delta));
    }

    public Vector getPosition(){
        return position;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public void setPosition(float x, float y){
        position.setCoordinates(x, y);
    }

    public void setVelocity(float x, float y) {
        velocity.setCoordinates(x, y);
    }

    public int getRadius(){
        return radius;
    }
}
