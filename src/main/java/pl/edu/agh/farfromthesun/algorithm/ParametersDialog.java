package pl.edu.agh.farfromthesun.algorithm;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import pl.edu.agh.farfromthesun.algorithm.model.Crossover;
import pl.edu.agh.farfromthesun.algorithm.model.Mutation;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;

class ParametersDialog extends JPanel {
	private static final long serialVersionUID = 1635902385403603282L;
	private LinkedList<ParametersPanel> panels;
	
	private ParametersSpinner population;
	private ParametersSpinner generations;
	private ParametersSpinner fitness;
	private ParametersSpinner mutation;
	private ParametersSpinner tournament;
	private ParametersComboBox mutations;
	private ParametersComboBox crossovers;
	private ParametersDatepicker date;
	private ParametersSpinner temperature;

	public ParametersDialog(Parameters parameters) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		ParametersPanel algorithmPanel = new ParametersPanel("Algorithm");
		ParametersPanel tripPanel = new ParametersPanel("Trip");
		
		panels.add(algorithmPanel);
		panels.add(tripPanel);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		population = new ParametersSpinner(parameters.getPopulationSize(), 0, 200, 1);
		generations = new ParametersSpinner(parameters.getNumberOfGenerations(), 0, 200, 1);
		fitness = new ParametersSpinner(parameters.getMinimumFitness(), 0, 100, 1);
		mutation = new ParametersSpinner(parameters.getMutationRate(), 0, 1, 0.05);
		tournament = new ParametersSpinner(parameters.getTournamentSize(), 0, 20, 1);
		temperature = new ParametersSpinner(parameters.getTemperature(), -20, 40, 0.5);

		mutations = new ParametersComboBox(parameters.getMutations());
		crossovers = new ParametersComboBox(parameters.getCrossovers());

		date = new ParametersDatepicker(datePanel);

		algorithmPanel.addParameter("Population size", "population", population);
		algorithmPanel.addParameter("Number of generations", "generations", generations);
		algorithmPanel.addParameter("Minimum fitness", "fitness", fitness);
		algorithmPanel.addParameter("Mutation rate", "mutation", mutation);
		algorithmPanel.addParameter("Tournament size", "tournament", tournament);
		algorithmPanel.addParameter("Mutation type", "mutationType", mutations);
		algorithmPanel.addParameter("Crossover type", "crossoverType", crossovers);

		tripPanel.addParameter("Start date", "date", date);
		tripPanel.addParameter("Desired temperature", "temperature", temperature);
	}
	
	public Parameters getParameters(){
		Parameters parameters = new Parameters();
		parameters.setPopulationSize(((Double) population.getInputValue()).intValue());
		parameters.setNumberOfGenerations(((Double) generations.getInputValue()).intValue());
		parameters.setMinimumFitness(((Double) fitness.getInputValue()).intValue());
		parameters.setMutationRate((Double) mutation.getInputValue());
		parameters.setTournamentSize(((Double) tournament.getInputValue()).intValue());
		parameters.setMutation((Mutation) mutations.getInputValue());
		parameters.setCross((Crossover) crossovers.getInputValue());
		parameters.setDate((LocalDate) date.getInputValue());
		parameters.setTemperature((Double) temperature.getInputValue());
		return parameters;
	}

	public HashMap<String, Object> getInputValues() {
		HashMap<String, Object> values = new HashMap<String, Object>();

		ListIterator<ParametersPanel> it = panels.listIterator();

		while (it.hasNext()) {
			for (Entry<String, ParametersInput> e : it.next().getInputs()
					.entrySet()) {
				values.put(e.getKey(), e.getValue().getInputValue());
			}
		}

		return values;
	}

	public void addPanels() {
		for(JPanel panel : panels){
			this.add(panel);
		}
	}
}