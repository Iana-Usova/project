package FIT_9202_Usova.SinForm;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.ParseException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ControlPanel extends JPanel {

	Properties properties;
	JSpinner amplSpinner;
	JSpinner gridWidthSpinner;
	JSpinner phaseSpinner;
	JSpinner gridHeightSpinner;

	SinusPanel panel;

	JSlider phaseSlider;
	JSlider amplSlider;
	JSlider gwSlider;
	JSlider ghSlider;
	
	boolean window = true;
	
	private final String AMPLITUDE_KEYWORD = "Amplitude";
	private final String PHASE_KEYWORD = "Phase";
	private final String GRID_WIDTH_KEYWORD = "GridWidth";
	private final String GRID_HEIGHT_KEYWORD = "GridHeight";

	int MIN_AMPL = -50;
	int MAX_AMPL = 50;
	int DEFAULT_AMPL = 20;

	int MIN_PHASE = 1;
	int MAX_PHASE = 100;
	int DEFAULT_PHASE = 100;

	int MIN_GH = 1;
	int MAX_GH = 100;
	int DEFAULT_GH = 20;

	int MIN_GW = 1;
	int MAX_GW = 100;
	int DEFAULT_GW = 20;
	

	private final String CONFIG_COMMENT = "SinFrame properties";

	ControlPanel(SinusPanel _panel) {
		panel = _panel;
		GridBagConstraints c = new GridBagConstraints();
		setMaximumSize(new Dimension(getWidth(), 150));
		setMinimumSize(new Dimension(getWidth(), 150));
		Border controlBorder = BorderFactory.createTitledBorder("Control");
		setBorder(controlBorder);
		setLayout(new GridBagLayout());

		// Phase 
		JLabel phaseLabel = new JLabel("Phase");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(phaseLabel, c);

		// Phase Spinner
		phaseSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_PHASE,
				MIN_PHASE, MAX_PHASE, 1));

		phaseSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSpinner jSpinner = (JSpinner) ce.getSource();
				phaseSlider.setValue((Integer) jSpinner.getValue());
				panel.setPhase((Integer) jSpinner.getValue());
				panel.repaint();
				}
		});

		((JSpinner.NumberEditor) phaseSpinner.getEditor()).getTextField().addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							try {
								phaseSpinner.commitEdit();
							} catch (ParseException e1) {
									window = false;
									JOptionPane.showMessageDialog(null, "Value not within [" + MIN_PHASE + "," + MAX_PHASE + "] range.");
									window = true;
									int textInEditor = phaseSlider.getValue();
									String textInEditorS = Integer.toString(textInEditor);
									((JSpinner.NumberEditor)phaseSpinner.getEditor()).getTextField().setText(textInEditorS);
								}
						}
					}
					@Override
					public void keyReleased(KeyEvent e) {}
					@Override
					public void keyTyped(KeyEvent e) {};
				});

		((JSpinner.NumberEditor) phaseSpinner.getEditor()).getTextField().addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {}
					@Override
					public void focusLost(FocusEvent e) {
						try {
							phaseSpinner.commitEdit();
						} catch (ParseException e1) {
							if (window)
							JOptionPane.showMessageDialog(null,
									"Value not within [" + MIN_PHASE + ","
											+ MAX_PHASE + "] range.");
						}
					}
				});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(phaseSpinner, c);

		// PHASE Slider
		phaseSlider = new JSlider(JSlider.HORIZONTAL, MIN_PHASE, MAX_PHASE,
				DEFAULT_PHASE);
		phaseSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSlider jSlider = (JSlider) ce.getSource();
				phaseSpinner.setValue(jSlider.getValue());
				panel.setPhase(jSlider.getValue());
				panel.repaint();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		add(phaseSlider, c);

		// Ampl
		JLabel amplLabel = new JLabel("Amplitude");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(amplLabel, c);

		amplSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_AMPL,
				MIN_AMPL, MAX_AMPL, 1));
		amplSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSpinner jSpinner = (JSpinner) ce.getSource();
				amplSlider.setValue((Integer) jSpinner.getValue());
				panel.setAmplitude((Integer) jSpinner.getValue());
				panel.repaint();
			}
		});
		
		((JSpinner.NumberEditor) amplSpinner.getEditor()).getTextField()
		.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						amplSpinner.commitEdit();
					} catch (ParseException e1) {
						window = false;
						JOptionPane.showMessageDialog(null,
							"Value not within [" + MIN_PHASE + ","
									+ MAX_PHASE + "] range.");
						window = true;
						int textInEditor = amplSlider.getValue();
						String textInEditorS = Integer.toString(textInEditor);
						((JSpinner.NumberEditor)amplSpinner.getEditor()).getTextField().setText(textInEditorS);

					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			};
		});
		
		
		((JSpinner.NumberEditor) amplSpinner.getEditor()).getTextField()
				.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
					}

					@Override
					public void focusLost(FocusEvent e) {
						try {
							amplSpinner.commitEdit();
						} catch (ParseException e1) {
							if (window)
							JOptionPane.showMessageDialog(null,
									"Value not within [" + MIN_AMPL + ","
											+ MAX_AMPL + "] range.");
						}
					}
				});

		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		add(amplSpinner, c);

		amplSlider = new JSlider(JSlider.HORIZONTAL, MIN_AMPL, MAX_AMPL,
				DEFAULT_AMPL);
		amplSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSlider jSlider = (JSlider) ce.getSource();
				amplSpinner.setValue(jSlider.getValue());
				panel.setAmplitude(jSlider.getValue());
				panel.repaint();
			}
		});

		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 2;
		add(amplSlider, c);

		
		// Grid width
		
		JLabel gwLabel = new JLabel("Grid width"); 

		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 0;
		add(gwLabel, c);

		gridWidthSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_GW,
				MIN_GW, MAX_GW, 1));
		gridWidthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSpinner jSpinner = (JSpinner) ce.getSource();
				gwSlider.setValue((Integer) jSpinner.getValue());
				panel.setGridWidth((Integer) jSpinner.getValue());
				panel.repaint();
			}
		});
		
		((JSpinner.NumberEditor) gridWidthSpinner.getEditor()).getTextField()
		.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						gridWidthSpinner.commitEdit();
					} catch (ParseException e1) {
						window = false;
						JOptionPane.showMessageDialog(null,
							"Value not within [" + MIN_PHASE + ","
									+ MAX_PHASE + "] range.");
						window = true;
						int textInEditor = gwSlider.getValue();
						String textInEditorS = Integer.toString(textInEditor);
						((JSpinner.NumberEditor)gridWidthSpinner.getEditor()).getTextField().setText(textInEditorS);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			};
		});
		
		((JSpinner.NumberEditor) gridWidthSpinner.getEditor()).getTextField()
				.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
					}

					@Override
					public void focusLost(FocusEvent e) {
						try {
							gridWidthSpinner.commitEdit();
						} catch (ParseException e1) {
							if (window)
							JOptionPane.showMessageDialog(null,
									"Value not within [" + MIN_GW + ","
											+ MAX_GW + "] range.");
						}
					}
				});

		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 1;
		add(gridWidthSpinner, c);

		gwSlider = new JSlider(JSlider.HORIZONTAL, MIN_GW, MAX_GW, DEFAULT_GW);
		gwSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSlider jSlider = (JSlider) ce.getSource();
				gridWidthSpinner.setValue(jSlider.getValue());
				panel.setGridWidth(jSlider.getValue());
				panel.repaint();
			}
		});

		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 2;
		add(gwSlider, c);

		
		// Grid height
		JLabel ghLabel = new JLabel("Grid height"); 

		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 0;
		add(ghLabel, c);

		gridHeightSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_GH,
				MIN_GH, MAX_GH, 1));
		gridHeightSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSpinner jSpinner = (JSpinner) ce.getSource();
				ghSlider.setValue((Integer) jSpinner.getValue());
				panel.setGridHeight((Integer) jSpinner.getValue());
				panel.repaint();
			}
		});

		((JSpinner.NumberEditor) gridHeightSpinner.getEditor()).getTextField()
		.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						gridHeightSpinner.commitEdit();
					} catch (ParseException e1) {
						window = false;
						JOptionPane.showMessageDialog(null,
							"Value not within [" + MIN_PHASE + ","
									+ MAX_PHASE + "] range.");
						window = true;
						int textInEditor = ghSlider.getValue();
						String textInEditorS = Integer.toString(textInEditor);
						((JSpinner.NumberEditor)gridHeightSpinner.getEditor()).getTextField().setText(textInEditorS);

					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			};
		});
		
		((JSpinner.NumberEditor) gridHeightSpinner.getEditor()).getTextField()
				.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
					}

					@Override
					public void focusLost(FocusEvent e) {
						try {
							gridHeightSpinner.commitEdit();
						} catch (ParseException e1) {
							if (window)
							JOptionPane.showMessageDialog(null,
									"Value not within [" + MIN_GH + ","
											+ MAX_GH + "] range.");
						}
					}
				});

		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 1;
		add(gridHeightSpinner, c);

		ghSlider = new JSlider(JSlider.HORIZONTAL, MIN_GH, MAX_GH, DEFAULT_GH);
		ghSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				JSlider jSlider = (JSlider) ce.getSource();
				gridHeightSpinner.setValue(jSlider.getValue());
				panel.setGridHeight(jSlider.getValue());
				panel.repaint();
			}
		});
		ghSlider.getHeight();

		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 2;
		add(ghSlider, c);
	}

	public void getSettings(File file) throws InvalidPropertiesFormatException,
			IOException {

		FileInputStream stream;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		stream = new FileInputStream(file);
		properties = new Properties();
		properties.loadFromXML(stream);
		String value;

		try {
			value = properties.getProperty(GRID_HEIGHT_KEYWORD, new Integer(
					panel.DEFAULT_GRID_HEIGHT).toString());
			gridHeightSpinner.setValue(Integer.parseInt(value));
		} catch (NumberFormatException ex) {
			gridHeightSpinner.setValue(panel.DEFAULT_GRID_HEIGHT);
		}
		try {
			value = properties.getProperty(GRID_WIDTH_KEYWORD, new Integer(
					panel.DEFAULT_GRID_WIDTH).toString());
			gridWidthSpinner.setValue(Integer.parseInt(value));
		} catch (NumberFormatException ex) {
			gridWidthSpinner.setValue(panel.DEFAULT_GRID_WIDTH);
		}
		try {
			value = properties.getProperty(AMPLITUDE_KEYWORD, new Integer(
					panel.DEFAULT_AMPLITUDE).toString());
			amplSpinner.setValue(Integer.parseInt(value));
		} catch (NumberFormatException ex) {
			amplSpinner.setValue(panel.DEFAULT_AMPLITUDE);
		}
		try {
			value = properties.getProperty(PHASE_KEYWORD, new Integer(
					panel.DEFAULT_PHASE).toString());
			phaseSpinner.setValue(Integer.parseInt(value));
		} catch (NumberFormatException ex) {
			phaseSpinner.setValue(panel.DEFAULT_PHASE);
		}
		stream.close();
	}

	public void setSettings(File file) throws IOException {
		FileOutputStream stream = new FileOutputStream(file);
		properties = new Properties();
		properties.setProperty(GRID_HEIGHT_KEYWORD, gridHeightSpinner
				.getValue().toString());
		properties.setProperty(GRID_WIDTH_KEYWORD, gridWidthSpinner.getValue()
				.toString());
		properties.setProperty(AMPLITUDE_KEYWORD, amplSpinner.getValue()
				.toString());
		properties.setProperty(PHASE_KEYWORD, phaseSpinner.getValue()
				.toString());

		properties.storeToXML(stream, CONFIG_COMMENT);
		stream.close();
	}
}
