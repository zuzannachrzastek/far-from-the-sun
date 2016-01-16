package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmController.Task;

public class AlgorithmConfigurationController extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 8491592003308755995L;
	private JButton btnStart, btnConfig;
	private AlgorithmController ctrl;
	private ProgressMonitor progressMonitor;

	public AlgorithmConfigurationController(AlgorithmController ctrl) {
		JPanel container = new JPanel();
		JFrame frame = ctrl.getFrame();
		
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnConfig = new JButton("Config");

		btnStart.addActionListener(e -> {
			progressMonitor = new ProgressMonitor(AlgorithmConfigurationController.this,
					"Running algorithm...", "", 0, 100);
			progressMonitor.setProgress(0);
			ctrl.findOptimalTour(ctrl.getMap().sendPlaces(), this);
		});

		btnConfig
				.addActionListener(e -> {
					ParametersDialog dialog = new ParametersDialog(ctrl.getParameters());

					int result = JOptionPane.showConfirmDialog(null, dialog,
							"Edit parameters", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						updateModel(dialog);
					}
				});

		container.add(btnConfig);
		container.add(btnStart);

		this.add(container, BorderLayout.LINE_END);
		frame.getContentPane().add(this, BorderLayout.PAGE_END);
	}

	private void updateModel(ParametersDialog dialog) {
		ctrl.setParameters(dialog.getParameters());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
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
