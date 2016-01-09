package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pl.edu.agh.farfromthesun.algorithm.model.Crossover;
import pl.edu.agh.farfromthesun.algorithm.model.Mutation;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.map.Location;

public class AlgorithmModel extends JPanel {
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
	private ParametersSpinner start;

	public AlgorithmModel(JFrame frame, Algorithm ctrl) {
		this.parameters = ctrl.getParametersInstance();
		JPanel container = new JPanel();

		this.setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnConfig = new JButton("Config");

		// values created to test

		List<Location> list = new LinkedList<Location>();
		list.add(new Location(60, 200));
		list.add(new Location(180, 200));
		list.add(new Location(80, 180));
		list.add(new Location(140, 180));
		list.add(new Location(20, 160));
		list.add(new Location(100, 160));
		list.add(new Location(200, 160));
		list.add(new Location(140, 140));
		list.add(new Location(40, 120));

		btnStart.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame, "Starting algorithm");
			// TODO get list from map
			ctrl.findOptimalTour(list);
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
		frame.getContentPane().add(this, BorderLayout.PAGE_END);
	}

	private void updateView(ParametersDialog dialog) {
		population = new ParametersSpinner(parameters.getPopulationSize(), 0,
				200, 1);
		generations = new ParametersSpinner(
				parameters.getNumberOfGenerations(), 0, 200, 1);
		fitness = new ParametersSpinner(parameters.getMinimumFitness(), 0, 100,
				1);
		mutation = new ParametersSpinner(parameters.getMutationRate(), 0, 1,
				0.05);
		tournament = new ParametersSpinner(parameters.getTournamentSize(), 0,
				20, 1);

		mutations = new ParametersComboBox(parameters.getMutations());
		crossovers = new ParametersComboBox(parameters.getCrossovers());
		
		start = new ParametersSpinner(parameters.getTournamentSize(), 0,
				20, 1);

		ParametersPanel algorithmPanel = new ParametersPanel("Algorithm");
		ParametersPanel tripPanel = new ParametersPanel("Trip");

		LinkedList<ParametersPanel> panels = new LinkedList<ParametersPanel>();
		panels.add(algorithmPanel);
		panels.add(tripPanel);

		algorithmPanel
				.addParameter("Population size", "population", population);
		algorithmPanel.addParameter("Number of generations", "generations",
				generations);
		algorithmPanel.addParameter("Minimum fitness", "fitness", fitness);
		algorithmPanel.addParameter("Mutation rate", "mutation", mutation);
		algorithmPanel
				.addParameter("Tournament size", "tournament", tournament);
		algorithmPanel.addParameter("Mutation type", "mutationType", mutations);
		algorithmPanel.addParameter("Crossover type", "crossoverType",
				crossovers);
		
		tripPanel.addParameter("Start date", "date", start);

		dialog.addPanels(panels);
	}

	private void updateModel(ParametersDialog dialog) {
		parameters.setPopulationSize(((Double) population.getInputValue())
				.intValue());
		parameters
				.setNumberOfGenerations(((Double) generations.getInputValue())
						.intValue());
		parameters.setMinimumFitness(((Double) fitness.getInputValue())
				.intValue());
		parameters.setMutationRate((Double) mutation.getInputValue());
		parameters.setTournamentSize(((Double) tournament.getInputValue())
				.intValue());
		parameters.setMutation((Mutation) mutations.getInputValue());
		parameters.setCross((Crossover) crossovers.getInputValue());
	}
}
