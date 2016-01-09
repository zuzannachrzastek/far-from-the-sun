package pl.edu.agh.farfromthesun.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

class ParametersDialog extends JPanel {
	private static final long serialVersionUID = 1635902385403603282L;
	private LinkedList<ParametersPanel> panels;

	public ParametersDialog() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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

	public void addPanels(List<ParametersPanel> panels) {
		ListIterator<ParametersPanel> it = panels.listIterator();

		while (it.hasNext()) {
			this.add(it.next());
		}
	}
}