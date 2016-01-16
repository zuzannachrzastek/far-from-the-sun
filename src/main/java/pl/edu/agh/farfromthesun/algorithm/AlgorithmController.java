package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import pl.edu.agh.farfromthesun.algorithm.Algorithm.Task;
import pl.edu.agh.farfromthesun.algorithm.model.Crossover;
import pl.edu.agh.farfromthesun.algorithm.model.Mutation;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.app.App;

public class AlgorithmController extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 8491592003308755995L;
	private JButton btnStart, btnConfig;
	private final Parameters parameters = Parameters.getInstance();
	private ParametersSpinner population;
	private ParametersSpinner generations;
	private ParametersSpinner fitness;
	private ParametersSpinner mutation;
	private ParametersSpinner tournament;
	private ParametersComboBox mutations;
	private ParametersComboBox crossovers;
	private ParametersDatepicker date;
	private ParametersSpinner temperature;
	
	private ProgressMonitor progressMonitor;

	public AlgorithmController(JFrame frame, Algorithm ctrl) {
		JPanel container = new JPanel();

		this.setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnConfig = new JButton("Config");

		btnStart.addActionListener(e -> {
			progressMonitor = new ProgressMonitor(AlgorithmController.this,
					"Running algorithm...", "", 0, 100);
			progressMonitor.setProgress(0);
			ctrl.findOptimalTour(App.getMap().sendPlaces(), this);
		});

		btnConfig
				.addActionListener(e -> {
					ParametersDialog dialog = new ParametersDialog();

					updateView(dialog);

					int result = JOptionPane.showConfirmDialog(null, dialog,
							"Edit parameters", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						/*HashMap<String, Object> params = dialog
								.getInputValues();

						for (Entry<String, Object> entry : params.entrySet()) {
							System.out.println(entry.getKey() + ": "
									+ entry.getValue());
						}*/

						updateModel(dialog);

					}
				});

		container.add(btnConfig);
		container.add(btnStart);

		this.add(container, BorderLayout.LINE_END);
		frame.getContentPane().add(this, BorderLayout.PAGE_END);
	}

	private void updateView(ParametersDialog dialog) {
		LinkedList<ParametersPanel> panels = new LinkedList<ParametersPanel>();
		
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

		dialog.addPanels(panels);
	}

	private void updateModel(ParametersDialog dialog) {
		parameters.setPopulationSize(((Double) population.getInputValue()).intValue());
		parameters.setNumberOfGenerations(((Double) generations.getInputValue()).intValue());
		parameters.setMinimumFitness(((Double) fitness.getInputValue()).intValue());
		parameters.setMutationRate((Double) mutation.getInputValue());
		parameters.setTournamentSize(((Double) tournament.getInputValue()).intValue());
		parameters.setMutation((Mutation) mutations.getInputValue());
		parameters.setCross((Crossover) crossovers.getInputValue());
		parameters.setDate((LocalDate) date.getInputValue());
		parameters.setTemperature((Double) temperature.getInputValue());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			int progress = (Integer) evt.getNewValue();
			progressMonitor.setProgress(progress);
			String message = String.format("Completed %d%%.\n", progress);
			progressMonitor.setNote(message);
			if (progressMonitor.isCanceled()) {
				((Task) evt.getSource()).cancel(true);
			}
		}
	}
}
