package pl.edu.agh.farfromthesun.algorithm.model;

public class Population {
	private Tour[] tours;
	
	public Population(int size, boolean first){
		tours = new Tour[size];
		if(first){
			for(int i = 0; i < size; i++){
				Tour newTour = new Tour();
				setTour(i, newTour);
			}
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
        Tour fittest = tours[0];
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }
}