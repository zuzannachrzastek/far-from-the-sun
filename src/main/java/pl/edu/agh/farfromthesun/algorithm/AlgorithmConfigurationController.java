package pl.edu.agh.farfromthesun.algorithm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmController.Task;

public class AlgorithmConfigurationController extends JPanel {
	private static final long serialVersionUID = 8491592003308755995L;
	private JButton btnStart, btnConfig;
	private AlgorithmController ctrl;
	private ProgressMonitor progressMonitor;
	private Timer cancelMonitor;

	public AlgorithmConfigurationController(AlgorithmController ctrl) {
		JPanel container = new JPanel();
		JFrame frame = ctrl.getFrame();

		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnConfig = new JButton("Config");

		cancelMonitor = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Task task = ctrl.getTask();
				if (progressMonitor.isCanceled()) {
					task.cancel(true);
					cancelMonitor.stop();
				} else if (task.isDone()) {
					progressMonitor.close();
					cancelMonitor.stop();
				} else {
					int progress = task.getProgress();
					progressMonitor.setNote(String.format("Completed %d%%.\n",
							progress));
					progressMonitor.setProgress(progress);
				}
			}
		});

		btnStart.addActionListener(e -> {
			progressMonitor = new ProgressMonitor(ctrl.getFrame(),
					"Running algorithm...", "Setting up", 0, 100);
			progressMonitor.setMillisToPopup(0);
			progressMonitor.setMillisToDecideToPopup(0);
			
			cancelMonitor.start();

			ctrl.findOptimalTour(ctrl.getMap().sendPlaces());
		});

		btnConfig
				.addActionListener(e -> {
					ParametersDialog dialog = new ParametersDialog(ctrl
							.getParameters());

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
}
