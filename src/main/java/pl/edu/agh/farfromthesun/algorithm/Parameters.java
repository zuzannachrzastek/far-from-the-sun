package pl.edu.agh.farfromthesun.algorithm;

public class Parameters {
	
	private int populationSize;
	private int numberOfGenerations;
	private int minimumFitness;
	private double mutationRate;
	private int tournamentSize;

	
	public Parameters(int populationSize, int numberOfGenerations, int minimumFitness, double mutationRate, int tournamentSize) {
		this.setPopulationSize(populationSize);
		this.setNumberOfGenerations(numberOfGenerations);
		this.setMinimumFitness(minimumFitness);
		this.setMutationRate(mutationRate);
		this.setTournamentSize(tournamentSize);
	}


	private Parameters() {
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
	
	public static final Parameters newParameters() {
		return new Parameters();
	}
}
