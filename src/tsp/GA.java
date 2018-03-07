/*
 * GA.java
 * Manages algorithms for evolving population
 */

package tsp;



import java.util.ArrayList;

public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.05;
    private static final int tournamentSize = 1;
    //private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation =  new Population(pop.populationSize(), false);
        int elitismOffset = 1;

        for(int a =0 ; a < elitismOffset; a++) {
            newPopulation.saveTour(a, pop.getFittest());

        }

        // Keep our best individual if elitism is enabled
//        if (elitism) {
//            newPopulation.saveTour(0, pop.getFittest());
//            elitismOffset = 1;
//        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        newPopulation.sortCustomersInPopulation();
       // newPopulation.checkCustomersInPopulation();

//        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
//            if(!checkTourPropriety(newPopulation.getTour(i)))
//                newPopulation.removeFromPopulation(i);
//        }
//        System.out.println(newPopulation.tours.length);

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        //System.out.println("cross");
        Tour tempParent1 = parent1;
        Tour tempParent2 = parent2;
//parent1.tour.remove(0);
//        tempParent1.removeFromTour(0);
//        tempParent2.removeFromTour(0);
//

//        int tempParent1Size = tempParent1.tourSize();
//        int tempParent2Size = tempParent2.tourSize();

        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (1+(Math.random() * (parent1.tourSize()-1)));
        int endPos = (int) (1+(Math.random() * (parent1.tourSize()-1)));

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour) {
        // Loop through tour cities
        for (int tourPos1 = 1; tourPos1 < tour.tourSize(); tourPos1++) {
            // Apply mutation rate
            if (Math.random() < mutationRate) {
                // Get a second random position in the tour
                int tourPos2 = (int) (1 + ((tour.tourSize() - 1) * Math.random()));

                // Get the cities at target position in tour
                Vertex city1 = tour.getCity(tourPos1);
                Vertex city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    public static void removeElement(Object[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }


//    // Selects candidate tour for crossover
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}