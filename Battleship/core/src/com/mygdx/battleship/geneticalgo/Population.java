package com.mygdx.battleship.geneticalgo;

import java.util.ArrayList;
import java.util.Random;

import com.mygdx.battleship.Point;

public class Population {
	public Individual[] individuals;
	public int individualSize;
	//PriorityQueue
	 public Population(int size, int iS) {
        individuals = new Individual[size];
        individualSize = iS;
        // Initialise population
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual(individualSize);
        }
	}
	public int getSize(){
		return individuals.length;
	}
	public int getIndividualSize(){
		return individualSize;
	}
	public Individual getIndividual(int i){
		return individuals[i];
	}
	public void setIndividual(int i, Individual indiv){
		individuals[i] = indiv;
	}
    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < individuals.length; i++) {
            if (fittest.getFitness() > getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
}
