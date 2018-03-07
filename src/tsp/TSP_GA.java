/*
 * TSP_GA.java
 * Create a tour and evolve a solution
 */

package tsp;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.*;

public class TSP_GA extends JPanel  {



    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(350, 30, 530, 530);

        Random random = new Random();

        ArrayList<Vertex> vertexlist = new ArrayList<>();


        //delivery points:
        vertexlist.add(new Vertex(30, 100, 1, 2)); // 3rd arg - id of that customer, 4th arg - number of that client orders
        vertexlist.add(new Vertex(40, 250, 2, 2));
        vertexlist.add(new Vertex(300, 100, 3, 4));

        //pickup  points:
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 1, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 3, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 2, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 3, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 1, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 3, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 2, 0));
        vertexlist.add(new Vertex(random.nextInt(490), random.nextInt(490), 3, 0));

        Collections.shuffle(vertexlist);
        //current driver pos
        vertexlist.add(0, new Vertex(1, 200, -1, 0));

        for (int a = 0; a < vertexlist.size(); a++) {
            TourManager.addCity(vertexlist.get(a));
        }

        long start = System.currentTimeMillis();
        Population pop = new Population(1000, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());
        int initial = pop.getFittest().getDistance();
        // Evolve population for 100 generations

        for (int a = 0; a < 5000; a++) {
            pop = GA.evolvePopulation(pop);
//            System.out.println("Current distance: " + pop.getFittest().getDistance() + " Initial: " + initial);
            //a--;
        }

        System.out.println("Final distance: " + pop.getFittest().getDistance());
        long stop = System.currentTimeMillis();
        System.out.println("Time(ms): " + (stop - start));

        window.setVisible(false);
        window.getContentPane().add(new MyCanvas(pop.getFittest().toTour()));
        window.setVisible(true);
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
}