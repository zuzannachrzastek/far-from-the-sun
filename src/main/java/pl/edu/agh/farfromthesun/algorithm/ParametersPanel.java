package pl.edu.agh.farfromthesun.algorithm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParametersPanel extends JPanel {
	private static final long serialVersionUID = -5285744560585584731L;
	private LinkedHashMap<String, ParametersInput> inputs;
	private int count = 0;

	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 15);
	private static final Insets EAST_INSETS = new Insets(5, 15, 5, 0);

	public ParametersPanel(String name) {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(name),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		inputs = new LinkedHashMap<String, ParametersInput>();
	}

	public void addParameter(String name, String slug, ParametersInput obj) {
		this.add(new JLabel(name + ":", JLabel.LEFT), createGbc(0, this.count));
		this.add((Component) obj, createGbc(1, this.count));
		this.inputs.put(slug, obj);
		this.count++;
	}

	public LinkedHashMap<String, ParametersInput> getInputs() {
		return this.inputs;
	}

	private GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;

		gbc.anchor = (x == 0) ? GridBagConstraints.WEST
				: GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		gbc.weightx = 0.5;
		gbc.weighty = 1.0;

		return gbc;
	}
}
