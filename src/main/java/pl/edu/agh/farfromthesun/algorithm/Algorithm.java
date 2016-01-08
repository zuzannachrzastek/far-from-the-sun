package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.algorithm.model.AlgorithmManager;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.app.Component;

public class Algorithm implements Component {

	@Override
	public void initialize(JFrame frame) {
		Parameters parameters = Parameters.newParameters();
		AlgorithmManager am = new AlgorithmManager(parameters);
		frame.getContentPane().add(new ActionButtons(frame, am, parameters),
				BorderLayout.PAGE_END);
	}

}
