/*
 * Population.java
 * Manages a population of candidate tours
 */

package tsp;

import java.util.ArrayList;

public class Population {

    // Holds population of tours
    Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                //if(newTour.checkCustomers());
                    saveTour(i, newTour);
            }

        }
    }

    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }

    public void checkCustomersInPopulation() {
        int is = tours.length;
        for (int i = 0; i < is; i++) {
            if(!tours[i].checkCustomers())
                removeFromPopulation(i);
        }
    }

    public void sortCustomersInPopulation() {
        int is = tours.length;
        for (int i = 0; i < is; i++) {
            tours[i].sortCustomers();
        }
    }

    public static Boolean checkTourPropriety(Tour tour) {
        ArrayList<Vertex> vertexlist = tour.toTour();

        for(int a = 1; a < vertexlist.size(); a++) {
            if(vertexlist.get(a).numberOfOders > 0) { //if is a customer
                //System.out.println("done");
                //kupujacy -- sprawdzamy czy wszystkie jego punkty sa przed nim
                int currentCustomerId = vertexlist.get(a).customerId;
                int tempCustomerRank = 0;

                for(int b = 1; b<a; b++) {
                    if(vertexlist.get(b).customerId == currentCustomerId)
                        tempCustomerRank++;
                }
                if(tempCustomerRank != vertexlist.get(a).numberOfOders) {
                    return false;

                }
            }

        }
        //tour.tour = vertexlist;
        return true;
    }

    static void swapVertexes (ArrayList<Vertex> vertexlist, int a, int b) {
        Vertex temp = vertexlist.get(a);
        vertexlist.set(a, vertexlist.get(b));
        vertexlist.set(b, temp);

    }

    static void addVertexToEndOfList(ArrayList<Vertex> vertexlist, int a) {
        vertexlist.add(vertexlist.get(a));
        vertexlist.remove(a);
    }


    public void removeFromPopulation(int removedIdx) {
        Tour[] tempTour = new Tour[populationSize()-1];
        for(int a =0; a<removedIdx; a++) {
            tempTour[a] = tours[a];
        }


        System.arraycopy(tours, removedIdx + 1, tours, removedIdx, tours.length - 1 - removedIdx);
    }
}