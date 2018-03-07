/*
 * Tour.java
 * Stores a candidate tour
 */

package tsp;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    // Holds our tour of cities
    public ArrayList tour = new ArrayList<Vertex>();
    // Cache
    private double fitness = 0;
    private int distance = 0;
    private Boolean goindBackToStartLocaion = false;

    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        ArrayList tempTour = new ArrayList<Vertex>();

        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 1; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            //setCity(cityIndex, TourManager.getCity(cityIndex));

            tempTour.add( TourManager.getCity(cityIndex));
//            tempTour.add( TourManager.getCity(cityIndex));


            // If the tours been altered we need to reset the fitness and distance


        }
        // Randomly reorder the tour
        Collections.shuffle(tempTour);
        tempTour.add(0,null);
        tempTour.set(0,TourManager.getCity(0));

        tempTour = sortCustomers(tempTour);

        fitness = 0;
        distance = 0;
        tour = tempTour;

    }

    // Gets a city from the tour
    public Vertex getCity(int tourPosition) {
        return (Vertex)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, Vertex city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }

    Boolean checkCustomers() {
        ArrayList<Vertex> temp = tour;
        for(int a=1; a<tourSize(); a++) {
            if(temp.get(a).numberOfOders>0) {
                int currentCustomerOrders = 0;
                for(int b=1;b<a;b++) {
                    if(temp.get(b).customerId==temp.get(a).customerId)
                        currentCustomerOrders++;
                }
                if(currentCustomerOrders < temp.get(a).numberOfOders)
                    return false;
            }
        }
        return true;
    }

    void sortCustomers() {
        ArrayList<Vertex> vertexlist = this.tour;
        ArrayList<Vertex> customerList =  new ArrayList<>() ;
        ArrayList<Vertex> cityList =  new ArrayList<>();

        for (int a = 1; a < vertexlist.size(); a++) {
            if(vertexlist.get(a).numberOfOders>0) {
                customerList.add(vertexlist.get(a));
            } else {
                cityList.add(vertexlist.get(a));
            }
        }
        Collections.shuffle(cityList);

        for(int a=0;a<customerList.size();a++) {
            int currentCustomerOrders = 0;
            for(int b=0; b<cityList.size(); b++ ) {
                if(cityList.get(b).customerId == customerList.get(a).customerId)
                    currentCustomerOrders++;
                if(customerList.get(a).numberOfOders == currentCustomerOrders)
                    if((b+1)==cityList.size())
                        cityList.add(customerList.get(a));
                    else
                        cityList.add(b+1, customerList.get(a));
            }
        }

        cityList.add(0,vertexlist.get(0));
        vertexlist = cityList;

        this.tour = vertexlist;
    }

    ArrayList<Vertex> sortCustomers(ArrayList<Vertex> vertexlist) {

        ArrayList<Vertex> customerList =  new ArrayList<>() ;
        ArrayList<Vertex> cityList =  new ArrayList<>();

        for (int a = 1; a < vertexlist.size(); a++) {
            if(vertexlist.get(a).numberOfOders>0) {
                customerList.add(vertexlist.get(a));
            } else {
                cityList.add(vertexlist.get(a));
            }
        }
        Collections.shuffle(cityList);

        for(int a=0;a<customerList.size();a++) {
            int currentCustomerOrders = 0;
            for(int b=0; b<cityList.size(); b++ ) {
                if(cityList.get(b).customerId == customerList.get(a).customerId)
                    currentCustomerOrders++;
                if(customerList.get(a).numberOfOders == currentCustomerOrders)
                    if((b+1)==cityList.size())
                        cityList.add(customerList.get(a));
                    else
                        cityList.add(b+1, customerList.get(a));
            }
        }

        cityList.add(0,vertexlist.get(0));
        vertexlist = cityList;

        return vertexlist;
    }


    // Gets the total distance of the tour
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            int maxTourSize = goindBackToStartLocaion ?   tourSize() :  tourSize()-1;
            // Loop through our tour's cities
            for (int cityIndex=0; cityIndex < maxTourSize; cityIndex++) {
                // Get city we're travelling from
                Vertex fromCity = getCity(cityIndex);
                // City we're travelling to
                Vertex destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    public void removeFromTour(int index) {
        tour.remove(index);
    }

    public void addToTour(int index, Vertex obj) {
        tour.add(index, obj);
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(Vertex city){
        return tour.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }

    public ArrayList<Vertex> toTour() {
        ArrayList citiesTour = new ArrayList<Vertex>();
        for (int i = 0; i < tourSize(); i++) {
            citiesTour.add(getCity(i));
        }
        return tour;
    }
}