package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.List;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class GeneticAlgorithm {
	private final Parameters params;
	private boolean found = false;
	private Population pop;
	
	public GeneticAlgorithm(TourManager manager){
		this.params = manager.getParameters();
		this.pop = new Population(params.getPopulationSize(), manager);
		
		evolvePopulation();
	}

	public void evolvePopulation() {
		Population newPopulation = new Population(pop.populationSize());

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			Tour parent1 = tournamentSelection(pop);
			Tour parent2 = tournamentSelection(pop);
			if (found) {
				return;
			}
			Tour child = params.getCross().cross(parent1, parent2);
			newPopulation.setTour(i, child);
		}

		for (int i = 0; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getTour(i));
		}

		pop = newPopulation;
	}

	private void mutate(Tour tour) {
		if (Math.random() < params.getMutationRate()) {
			params.getMutation().mutate(tour);
		}
	}

	private Tour tournamentSelection(Population pop) {
		Population tournament = new Population(params.getTournamentSize());
		
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
	
	public List<WeatherLocation> getResult(){
		return pop.getFittest().getPoints();
	}
}
