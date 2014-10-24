package display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import processing.GravApplet;

public class DisplayFrame extends JFrame {
	//Panels
	private JPanel container;
	private JPanel controlPanel;
	private JPanel appletPanel;
	//Control Panel Components
	private JButton pauseButton;
	private JButton addPlanetButton;
	private JButton resetViewButton;
	private JButton randomizeButton;
	private JTextField massTextField;
	private JTextField pxTextField;
	private JTextField pyTextField;
	private JTextField pzTextField;
	private JTextField vxTextField;
	private JTextField vyTextField;
	private JTextField vzTextField;
	private JTextField gTextField;
	private JTextField zoomTextField;
	private JTextField moonMassTextField;
	private JTextField planetMassTextField;
	private JTextField gasGiantMassTextField;
	private JTextField sunMassTextField;
	private JTextField blackHoleMassTextField;
	private JCheckBox rainbowExplosionsCB;
	//Display Constants
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	
	//Constructor
	public DisplayFrame(String title) {
		//Initialize Window
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Initialize the Panels
		initializeControlPanel();
		initializeAppletPanel();
		
		//Initialize and add the container panel
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(controlPanel);
		container.add(appletPanel);
		getContentPane().add(container);
		
		//Make Visible
		setVisible(true);
	}
	
	//Make Control Panel
	private void initializeControlPanel() {
		//Initialize the Panel and Layout
		controlPanel = new JPanel();
		controlPanel.setBounds(0, 0, WIDTH / 2, HEIGHT);
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setLayout(new GridBagLayout());
		
		//Initialize Constraints
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//Init Pause Button
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().flipPause();
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 25, 0);
		controlPanel.add(pauseButton, c);
		
		//Init Planet Adder Label
		JLabel planetAdderLabel = new JLabel("     Planet Adder Variables     ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 0);
		controlPanel.add(planetAdderLabel, c);
		
		//Init Mass Label
		JLabel massLabel = new JLabel("Mass: ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 5);
		controlPanel.add(massLabel, c);
		
		//Init Mass TextField
		massTextField = new JTextField();
		massTextField.setText("100");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 0);
		controlPanel.add(massTextField, c);
		
		//Init Px Label
		JLabel pxLabel = new JLabel("Px: ");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(pxLabel, c);
		
		//Init Px TextField
		pxTextField = new JTextField();
		pxTextField.setText("0");
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(pxTextField, c);
		
		//Init Py Label
		JLabel pyLabel = new JLabel("Py: ");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(pyLabel, c);
		
		//Init Py TextField
		pyTextField = new JTextField();
		pyTextField.setText("0");
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(pyTextField, c);
		
		//Init Pz Label
		JLabel pzLabel = new JLabel("Pz: ");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 5);
		controlPanel.add(pzLabel, c);
		
		//Init Pz TextField
		pzTextField = new JTextField();
		pzTextField.setText("0");
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 0);
		controlPanel.add(pzTextField, c);
		
		//Init Vx Label
		JLabel vxLabel = new JLabel("Vx: ");
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(vxLabel, c);
		
		//Init Vx TextField
		vxTextField = new JTextField();
		vxTextField.setText("0");
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(vxTextField, c);
		
		//Init Vy Label
		JLabel vyLabel = new JLabel("Vy: ");
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(vyLabel, c);
		
		//Init Vy TextField
		vyTextField = new JTextField();
		vyTextField.setText("0");
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(vyTextField, c);
		
		//Init Vz Label
		JLabel vzLabel = new JLabel("Vz: ");
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 5);
		controlPanel.add(vzLabel, c);
		
		//Init Vz TextField
		vzTextField = new JTextField();
		vzTextField.setText("0");
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 0);
		controlPanel.add(vzTextField, c);
		
		//Init Add Planet Button
		addPlanetButton = new JButton("Add Planet");
		addPlanetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Ensure none of the fields are empty
				if (anyPlanetAdderFieldsAreEmpty()) return;
				
				//Collect data from each of the planet adder text fields
				float mass = Float.parseFloat(massTextField.getText());
				float px = Float.parseFloat(pxTextField.getText());
				float py = Float.parseFloat(pyTextField.getText());
				float pz = Float.parseFloat(pzTextField.getText());
				float vx = Float.parseFloat(vxTextField.getText());
				float vy = Float.parseFloat(vyTextField.getText());
				float vz = Float.parseFloat(vzTextField.getText());
				
				//Add Planet to applet
				applet().addPlanet(mass, px, py, pz, vx, vy, vz);
			}
		});
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 25, 0);
		controlPanel.add(addPlanetButton, c);
		
		//Init User Constants Label
		JLabel userConstantsLabel = new JLabel("           User Constants:         ");
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 0);
		controlPanel.add(userConstantsLabel, c);
		
		//Init G Label
		JLabel gLabel = new JLabel("G: ");
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(gLabel, c);
		
		//Init G TextField
		gTextField = new JTextField();
		gTextField.setText("16");
		gTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gTextField.getText().equals("")) return;
				applet().setG(Float.parseFloat(gTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(gTextField, c);
		
		//Init Zoom Label
		JLabel zoomLabel = new JLabel("Zoom: ");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 25, 5);
		controlPanel.add(zoomLabel, c);
		
		//Init Zoom TextField
		zoomTextField = new JTextField();
		zoomTextField.setText("3");
		zoomTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (zoomTextField.getText().equals("")) return;
				applet().setZoom(Float.parseFloat(zoomTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 12;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 25, 0);
		controlPanel.add(zoomTextField, c);
		
		//Init UI Options Label
		JLabel uiOptionsLabel = new JLabel("             UI Options:           ");
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 0);
		controlPanel.add(uiOptionsLabel, c);
		
		//Init Moon Mass Label
		JLabel moonMassLabel = new JLabel("Moon Mass: ");
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(moonMassLabel, c);
		
		//Init Moon Mass TextField
		moonMassTextField = new JTextField();
		moonMassTextField.setText("5");
		moonMassTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().setMoonMass(Float.parseFloat(moonMassTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 14;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(moonMassTextField, c);
		
		//Init Planet Mass Label
		JLabel planetMassLabel = new JLabel("Planet Mass: ");
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(planetMassLabel, c);
		
		//Init Planet Mass TextField
		planetMassTextField = new JTextField();
		planetMassTextField.setText("15");
		planetMassTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().setPlanetMass(Float.parseFloat(planetMassTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 15;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(planetMassTextField, c);
		
		//Init Gas Giant Mass Label
		JLabel gasGiantMassLabel = new JLabel("Gas Giant Mass: ");
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(gasGiantMassLabel, c);
		
		//Init Gas Giant Mass TextField
		gasGiantMassTextField = new JTextField();
		gasGiantMassTextField.setText("30");
		gasGiantMassTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().setGasGiantMass(Float.parseFloat(gasGiantMassTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 16;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(gasGiantMassTextField, c);
		
		//Init Sun Mass Label
		JLabel sunMassLabel = new JLabel("Sun Mass: ");
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(sunMassLabel, c);
		
		//Init Sun Mass TextField
		sunMassTextField = new JTextField();
		sunMassTextField.setText("50");
		sunMassTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().setSunMass(Float.parseFloat(sunMassTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 17;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(sunMassTextField, c);
		
		//Init Black Hole Mass Label
		JLabel blackHoleMassLabel = new JLabel("Black Hole Mass: ");
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		controlPanel.add(blackHoleMassLabel, c);
		
		//Init Black Hole Mass TextField
		blackHoleMassTextField = new JTextField();
		blackHoleMassTextField.setText("200");
		blackHoleMassTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().setBlackHoleMass(Float.parseFloat(blackHoleMassTextField.getText()));
			}
		});
		c.gridx = 1;
		c.gridy = 18;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(blackHoleMassTextField, c);
		
		//Init Rainbow Explosions CB
		rainbowExplosionsCB = new JCheckBox("Rainbow Explosions: ");
		rainbowExplosionsCB.setBackground(Color.LIGHT_GRAY);
		rainbowExplosionsCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applet().setRainbowExplosions(rainbowExplosionsCB.isSelected());
			}
		});
		c.gridx = 0;
		c.gridy = 19;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 25, 0);
		controlPanel.add(rainbowExplosionsCB, c);
		
		//Init Reset View Button
		resetViewButton = new JButton("Reset View");
		resetViewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().resetView();
			}
		});
		c.gridx = 0;
		c.gridy = 20;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 25, 0);
		controlPanel.add(resetViewButton, c);
		
		//Init Randomize Button
		randomizeButton = new JButton("Randomize!");
		randomizeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				applet().randomize();
			}
		});
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 0, 0);
		controlPanel.add(randomizeButton, c);
	}
	
	//Make Applet Panel
	private void initializeAppletPanel() {
		//Initialize the Panel
		appletPanel = new JPanel();
		appletPanel.setBounds(WIDTH/4, 0, WIDTH * 3 / 4, HEIGHT);
		
		//Initialize the Applet
		GravApplet myApplet = new GravApplet();
		myApplet.setInstance(myApplet);
		appletPanel.add(myApplet);
		myApplet.init();
	}
	
	//Checks if any of the planet adder fields are empty
	private boolean anyPlanetAdderFieldsAreEmpty() {
		return massTextField.getText().equals("") ||
		pxTextField.getText().equals("") ||
		pyTextField.getText().equals("") ||
		pzTextField.getText().equals("") ||
		vxTextField.getText().equals("") ||
		vyTextField.getText().equals("") ||
		vzTextField.getText().equals("");
	}
	
	private GravApplet applet() {
		return GravApplet.getInstance();
	}
}
