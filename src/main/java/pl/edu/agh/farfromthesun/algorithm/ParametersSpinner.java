package pl.edu.agh.farfromthesun.algorithm;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ParametersSpinner extends JSpinner implements ParametersInput {
	private static final long serialVersionUID = 3424664475988655830L;

	public ParametersSpinner(double current, double min, double max, double step) {
		super();

		this.setModel(new SpinnerNumberModel(current, min, max, step));
	}

	@Override
	public Object getInputValue() {
		return this.getValue();
	}

}
