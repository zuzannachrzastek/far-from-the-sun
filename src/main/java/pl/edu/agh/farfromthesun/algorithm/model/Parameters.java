package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.LinkedList;
import java.util.List;

public class Parameters {
	
	private int populationSize;
	private int numberOfGenerations;
	private int minimumFitness;
	private double mutationRate;
	private int tournamentSize;
	private Crossover cross;
	private List<Crossover> crossovers;
	private Mutation mutation;
	private List<Mutation> mutations;

	
	public Parameters(int populationSize, int numberOfGenerations, int minimumFitness, double mutationRate, int tournamentSize) {
		this.setPopulationSize(populationSize);
		this.setNumberOfGenerations(numberOfGenerations);
		this.setMinimumFitness(minimumFitness);
		this.setMutationRate(mutationRate);
		this.setTournamentSize(tournamentSize);
		this.crossovers = new LinkedList<Crossover>();
		this.crossovers.add(new EdgeCrossover());
		this.crossovers.add(new OrderCrossover());
		this.crossovers.add(new PMX());
		this.cross = crossovers.get(0);
		this.mutations = new LinkedList<Mutation>();
		this.mutations.add(new ScrambleMutation());
		this.mutations.add(new InversionMutation());
		this.mutation = mutations.get(0);
	}


	public Parameters() {
		this(100,50,70,0.25,5);
	}


	public int getPopulationSize() {
		return populationSize;
	}


	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}


	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}


	public void setNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}


	public int getMinimumFitness() {
		return minimumFitness;
	}


	public void setMinimumFitness(int minimumFitness) {
		this.minimumFitness = minimumFitness;
	}


	public double getMutationRate() {
		return mutationRate;
	}


	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}


	public int getTournamentSize() {
		return tournamentSize;
	}


	public void setTournamentSize(int tournamentSize) {
		this.tournamentSize = tournamentSize;
	}

	public Crossover getCross() {
		return cross;
	}


	public void setCross(Crossover cross) {
		this.cross = cross;
	}


	public Mutation getMutation() {
		return mutation;
	}


	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}
	
	public Nameable[] getMutations(){
		return mutations.toArray(new Nameable[0]);		
	}
	
	public Nameable[] getCrossovers(){
		return crossovers.toArray(new Nameable[0]);
	}
}
