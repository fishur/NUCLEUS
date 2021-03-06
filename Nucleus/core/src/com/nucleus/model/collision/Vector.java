package com.nucleus.model.collision;

/**
 * Utility class used for positions and velocities in a two-dimensional space. Used throughout the
 * whole application.
 * All methods are different vector-mathematics, such as multiply and subtract.
 */
public class Vector {
    private float x;
    private float y;

    public Vector(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void setCoordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vect){
        return new Vector(x + vect.getX(), y + vect.getY());
    }

    public Vector subtract(Vector vect){
        return new Vector(x - vect.getX(), y - vect.getY());
    }

    public Vector multiply(float t){
        return new Vector(x*t, y*t);
    }

    public float abs(){
        return (float)Math.sqrt(x*x + y*y);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float scalar(Vector vect){
        return x * vect.getX() + y * vect.getY();
    }

}
