package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.List;

import pl.edu.agh.farfromthesun.map.Point;

public class AlgorithmManager {
	private Population pop;
	private int numberOfGenerations;
	private int populationSize;
	public AlgorithmManager(Parameters parameters){
		this.populationSize = parameters.getPopulationSize();
		new GeneticAlgorithm(parameters.getMutationRate(), parameters.getTournamentSize(), parameters.getCross(), parameters.getMutation(), parameters.getMinimumFitness());
		numberOfGenerations = parameters.getNumberOfGenerations();
	}
	
	public void update(Parameters parameters){
		this.populationSize = parameters.getPopulationSize();
		new GeneticAlgorithm(parameters.getMutationRate(), parameters.getTournamentSize(), parameters.getCross(), parameters.getMutation(), parameters.getMinimumFitness());
		numberOfGenerations = parameters.getNumberOfGenerations();
	}
	
	public List<Point> findOptimalTour(List<Point> points){
		if(points.size() < 3){
			return points;
		}
		new TourManager(points);
		pop = new Population(populationSize,true);
		pop = GeneticAlgorithm.evolvePopulation(pop);
		for (int i = 0; i < numberOfGenerations; i++) {
            pop = GeneticAlgorithm.evolvePopulation(pop);
        }
		System.out.print(pop.getFittest()); 
		return pop.getFittest().getPoints();		
	}
}
