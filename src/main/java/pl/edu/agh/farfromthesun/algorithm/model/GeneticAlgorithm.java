package pl.edu.agh.farfromthesun.algorithm.model;

public class GeneticAlgorithm {
	private final Parameters params;
	private final TourManager manager;
	private boolean found = false;
	
	public GeneticAlgorithm(TourManager manager){
		this.manager = manager;
		this.params = manager.getParameters();
	}

	public Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.populationSize(), false, manager);

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			Tour parent1 = tournamentSelection(pop);
			Tour parent2 = tournamentSelection(pop);
			if (found) {
				return pop;
			}
			Tour child = params.getCross().cross(parent1, parent2);
			newPopulation.setTour(i, child);
		}

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getTour(i));
		}

		return newPopulation;
	}

	private void mutate(Tour tour) {
		if (Math.random() < params.getMutationRate()) {
			params.getMutation().mutate(tour);
		}
	}

	private Tour tournamentSelection(Population pop) {
		Population tournament = new Population(params.getPopulationSize(), false, manager);
		
		Tour fittest = pop.getFittest();
		
		if (fittest.getFitness() > params.getMinimumFitness()) {
			found = true;
			return fittest;
		}
		
		for (int i = 0; i < params.getTournamentSize(); i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.setTour(i, pop.getTour(randomId));
		}

		return tournament.getFittest();
	}
}
