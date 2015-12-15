package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pl.edu.agh.farfromthesun.algorithm.model.*;

public class ActionButtons extends JPanel {
	private static final long serialVersionUID = 8491592003308755995L;
	private JButton btnStart, btnConfig;
	private Parameters parameters;
	private ParametersSpinner population;
	private ParametersSpinner generations;
	private ParametersSpinner fitness;
	private ParametersSpinner mutation;
	private ParametersSpinner tournament;
	private ParametersComboBox mutations;
	private ParametersComboBox crossovers;
	

	public ActionButtons(JFrame frame, Algorithm controller, Parameters parameters) {
		this.parameters = parameters;
		JPanel container = new JPanel();

		this.setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnConfig = new JButton("Config");

		btnStart.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame, "Starting algorithm");
		});

		btnConfig
				.addActionListener(e -> {
					ParametersDialog dialog = new ParametersDialog();
					
					updateView(dialog);
					
					int result = JOptionPane.showConfirmDialog(null, dialog,
							"Edit parameters", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						HashMap<String, Object> params = dialog
								.getInputValues();

						for (Entry<String, Object> entry : params.entrySet()) {
							System.out.println(entry.getKey() + ": "
									+ entry.getValue());
						}
						
						updateModel(dialog);
						
					}
				});

		container.add(btnConfig);
		container.add(btnStart);

		this.add(container, BorderLayout.LINE_END);
	}

	private void updateView(ParametersDialog dialog) {
		population = new ParametersSpinner(parameters.getPopulationSize(), 0, 200, 1);
		generations = new ParametersSpinner(parameters.getNumberOfGenerations(), 0, 200, 1);
		fitness = new ParametersSpinner(parameters.getMinimumFitness(), 0, 100, 1);
		mutation = new ParametersSpinner(parameters.getMutationRate(), 0, 1, 0.05);
		tournament = new ParametersSpinner(parameters.getTournamentSize(), 0, 20, 1);
		
		mutations = new ParametersComboBox(parameters.getMutations());
		crossovers = new ParametersComboBox(parameters.getCrossovers());
	
		
		dialog.addParameter("Population size", "population", population);
		dialog.addParameter("Number of generations", "generations", generations);
		dialog.addParameter("Minimum fitness", "fitness", fitness);
		dialog.addParameter("Mutation rate", "mutation", mutation);
		dialog.addParameter("Tournament size", "tournament", tournament);
		dialog.addParameter("Mutation type", "mutationType", mutations);
		dialog.addParameter("Crossover type", "crossoverType", crossovers);
		
	}

	private void updateModel(ParametersDialog dialog) {
		parameters.setPopulationSize(((Double) population.getInputValue()).intValue());
		parameters.setNumberOfGenerations(((Double)generations.getInputValue()).intValue());
		parameters.setMinimumFitness(((Double)fitness.getInputValue()).intValue());
		parameters.setMutationRate((Double)mutation.getInputValue());
		parameters.setTournamentSize(((Double)tournament.getInputValue()).intValue());
		parameters.setMutation((Mutation)mutations.getInputValue());
		parameters.setCross((Crossover)crossovers.getInputValue());
	}
}
