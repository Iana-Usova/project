package FIT_9202_Usova.SinForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

public class MainWindow extends JFrame {

	JMenuItem jLoad;
	JMenuItem jSave;
	JMenuItem jExit;
	JFileChooser fc;
	ControlPanel southPanel;	

	public void createGUI() {

		JPanel mainPanel = new JPanel(); // main panel
		mainPanel.setLayout(new BorderLayout());

		JMenuBar menuBar;
		JMenu menu = new JMenu();

		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		jLoad = new JMenuItem("Load ");
		menu.add(jLoad);
		jSave = new JMenuItem("Save ");
		menu.add(jSave);
		jExit = new JMenuItem("Exit ");
		menu.add(jExit);
	
		JPanel LabelPanel = new JPanel(); // new panel with label
		LabelPanel.setLayout(new BorderLayout());
		Border LPanelBorder = BorderFactory.createTitledBorder("SINFORM");
		LabelPanel.setBorder(LPanelBorder);
		mainPanel.add(LabelPanel, BorderLayout.CENTER);

		SinusPanel centerPanel = new SinusPanel(); // center panel
		LabelPanel.add(centerPanel, BorderLayout.CENTER);

		southPanel = new ControlPanel(centerPanel); // south panel
		Border southBorder = BorderFactory.createTitledBorder("CONTROLS");
		southPanel.setBorder(southBorder);
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);
		setPreferredSize(new Dimension(500, 700));
		pack();
		
		
		setMinimumSize(new Dimension((southPanel.getPreferredSize().width), 
				menuBar.getHeight() + southPanel.getPreferredSize().height + getInsets().bottom + getInsets().top)
		);

		mainPanel.setVisible(true);

		setLocationRelativeTo(null);
		setVisible(true);

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		jLoad.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e2) {
				int returnVal = fc.showOpenDialog(MainWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {						
						southPanel.getSettings(file);
					} catch (InvalidPropertiesFormatException e) {
						JOptionPane.showMessageDialog(MainWindow.this, e.getLocalizedMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainWindow.this, e1.getLocalizedMessage());
					}
				}
			}
		});

		jSave.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 	{
				int returnVal = fc.showSaveDialog(MainWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						southPanel.setSettings(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainWindow.this, e1
								.getLocalizedMessage());
					}
				}
			}

		});

		jExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.dispose();
			}
		});

	}
}