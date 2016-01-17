package pl.edu.agh.farfromthesun.algorithm.model;


public class Population {
	private Tour[] tours;

	public Population(int size, TourManager manager) {
		this(size);
		initializeTours(manager);
	}
	
	public Population(int size){
		tours = new Tour[size];
	}
	
	private void initializeTours(TourManager manager){
		for (int i = 0; i < populationSize(); i++) {
			setTour(i, new Tour(manager));
		}
	}

	public void setTour(int i, Tour tour) {
		tours[i] = tour;
	}

	public Tour getTour(int i) {
		return tours[i];
	}

	public int populationSize() {
		return tours.length;
	}

	public Tour getFittest() {
		Tour fittest = getTour(0);
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getTour(i).getFitness()) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}
}
