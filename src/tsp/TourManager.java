/*
 * TourManager.java
 * Holds the cities of a tour
 */

package tsp;

import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<Vertex>();

    // Adds a destination city
    public static void addCity(Vertex city) {
        destinationCities.add(city);
    }

    // Get a city
    public static Vertex getCity(int index){
        return (Vertex)destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
}