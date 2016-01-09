package pl.edu.agh.farfromthesun.algorithm.model;

import java.time.LocalDate;
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
	private double temperature;
	private LocalDate date;

	private static Parameters instance = null;

	public static Parameters getInstance() {
		if (instance == null) {
			instance = new Parameters();
		}

		return instance;
	}

	private Parameters(int populationSize, int numberOfGenerations,
			int minimumFitness, double mutationRate, int tournamentSize,
			int temperature) {
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
		this.temperature = temperature;
		this.date = LocalDate.now();
	}

	private Parameters() {
		this(100, 50, 70, 0.25, 5, 20);
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

	public Nameable[] getMutations() {
		return mutations.toArray(new Nameable[0]);
	}

	public Nameable[] getCrossovers() {
		return crossovers.toArray(new Nameable[0]);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
