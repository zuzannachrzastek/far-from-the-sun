package pl.edu.agh.farfromthesun.algorithm.model;

public class GeneticAlgorithm {
	private double mutationRate;
	private int tournamentSize;
	private Crossover crossover;
	private Mutation mutation;
	private boolean found = false;
	private int minimumFitness;

	public Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.populationSize(), false);

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			Tour parent1 = tournamentSelection(pop);
			Tour parent2 = tournamentSelection(pop);
			if (found) {
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

	private void mutate(Tour tour) {
		if (Math.random() < mutationRate) {
			mutation.mutate(tour);
		}
	}

	private Tour tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false);
		
		Tour fittest = pop.getFittest();
		
		if (fittest.getFitness() > minimumFitness) {
			found = true;
			return fittest;
		}
		
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.setTour(i, pop.getTour(randomId));
		}

		return tournament.getFittest();
	}
}
