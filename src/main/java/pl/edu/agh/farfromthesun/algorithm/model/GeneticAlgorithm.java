package pl.edu.agh.farfromthesun.algorithm.model;

public class GeneticAlgorithm {
	private static double mutationRate;
	private static int tournamentSize;
	private static Crossover crossover;
	private static Mutation mutation;
	private static boolean found = false;
	private static int minimumFitness;

	GeneticAlgorithm(double mutationRate, int tournamentSize, Crossover crossover, Mutation mutation, int minimumFitness) {
		GeneticAlgorithm.mutationRate = mutationRate;
		GeneticAlgorithm.tournamentSize = tournamentSize;
		GeneticAlgorithm.crossover = crossover;
		GeneticAlgorithm.mutation = mutation;
		GeneticAlgorithm.minimumFitness = minimumFitness;
	}

	public static Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.populationSize(), false);

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			Tour parent1 = tournamentSelection(pop);
			Tour parent2 = tournamentSelection(pop);
			if(found){
				return pop;
			}
			Tour child = crossover.cross(parent1, parent2);
			newPopulation.setTour(i, child);
		}

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getTour(i));
		}

		return newPopulation;
	}

	private static void mutate(Tour tour) {
		if (Math.random() < mutationRate) {
			mutation.mutate(tour);
		}
	}

	private static Tour tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false);
		if (pop.getFittest().getFitness() > minimumFitness) {
				found = true;
				return pop.getFittest();
			}
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.setTour(i, pop.getTour(randomId));
			
		}

		Tour fittest = tournament.getFittest();
		return fittest;
	}
}
