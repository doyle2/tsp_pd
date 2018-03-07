/*
 * City.java
 * Models a city
 */

package tsp;

public class Vertex {
    public int x;
    int y;
    public int customerId; //id of target (pickup), or current id (delivery vertex), -1 if vertex is a current supplier position
    public int numberOfOders; //0 if vertex is a pickup point

    // Constructs a randomly placed city
    public Vertex(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }

    // Constructs a city at chosen x, y location

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfOders() {
        return numberOfOders;
    }

    public void setNumberOfOders(int numberOfOders) {
        this.numberOfOders = numberOfOders;
    }

    public Vertex(int x, int y){
        this.x = x;
        this.y = y;
        this.customerId = 0;
        this.numberOfOders = 0;
    }

    public Vertex(int x, int y, int customerId){
        this.x = x;
        this.y = y;
        this.customerId = customerId;
        this.numberOfOders = 0;
    }

    public Vertex(int x, int y, int customerId, int numberOfOders){
        this.x = x;
        this.y = y;
        this.customerId = customerId;
        this.numberOfOders = numberOfOders;
    }

    // Gets city's x coordinate
    public double getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public double getY(){
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(Vertex city){
        double xDistance = Math.abs(getX() - city.getX());
        double yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }

    @Override
    public String toString(){
        return getX()+", "+getY()+", "+getCustomerId()+", "+getNumberOfOders();
    }
}