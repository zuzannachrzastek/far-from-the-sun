package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.app.Component;

public class Algorithm implements Component {

	@Override
	public void initialize(JFrame frame) {
		frame.getContentPane().add(new ActionButtons(frame, this),
				BorderLayout.PAGE_END);
	}

}
