package pl.edu.agh.farfromthesun.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

class ParametersDialog extends JPanel {
	private static final long serialVersionUID = 1635902385403603282L;
	private LinkedList<ParametersPanel> panels;
	private ParametersPanel algorithmPanel;
	private ParametersPanel weatherPanel;

	public ParametersDialog() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		panels = new LinkedList<ParametersPanel>();

		algorithmPanel = new ParametersPanel("Algorithm");

		/*algorithmPanel.addParameter("Population size", "population",
				new ParametersSpinner(100, 0, 200, 1));
		algorithmPanel.addParameter("Number of generations", "generations",
				new ParametersSpinner(50, 0, 200, 1));
		algorithmPanel.addParameter("Minimum fitness", "fitness",
				new ParametersSpinner(70, 0, 200, 1));
		algorithmPanel.addParameter("Mutation rate", "mutation",
				new ParametersSpinner(0.25, 0, 1, 0.05));
		algorithmPanel.addParameter("Tournament size", "tournament",
				new ParametersSpinner(5, 0, 20, 1));*/

		weatherPanel = new ParametersPanel("Weather");

		panels.add(algorithmPanel);
		panels.add(weatherPanel);

		ListIterator<ParametersPanel> it = panels.listIterator();

		while (it.hasNext()) {
			this.add(it.next());
		}
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
	
	public void addParameter(String name, String slug, ParametersInput obj){
		algorithmPanel.addParameter(name, slug, obj);
	}

}