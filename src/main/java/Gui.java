import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class Gui extends JFrame implements Observer {

	//Zeichenfläche
	private PaintArea paintArea = new PaintArea();
	private JButton addRectBtn;
	private JButton stopPntBtn;
	private JButton network; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JSlider colorSliderRed;
	private JSlider colorSliderGreen;
	private JSlider colorSliderBlue;
	private JMenuItem mntmNew;
	private JMenuItem mntmSave;
	private JMenuItem mntmOpen;
	private JTextField textField_3;
	private JPanel colorConfigPanel;
	private JPanel serverPanel;
	private JPanel clientPanel;
	private JPanel networkPanel;
	private JPanel changings;
	private JTextField textField_4;
	private JTextField ipAddressClientTextField;
	
	public Gui() {
	
		//configPanel
		JPanel configPanel = new JPanel();
		configPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(configPanel, BorderLayout.EAST);
		configPanel.setLayout(new BorderLayout(0, 0));
		
		changings = new JPanel();
		configPanel.add(changings, BorderLayout.NORTH);
		changings.setLayout(new BorderLayout(0, 0));
		
		networkPanel = new JPanel();
		//changings.add(networkPanel, BorderLayout.NORTH);
		networkPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		serverPanel = new JPanel();
		networkPanel.add(serverPanel);
		serverPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel serverLabel = new JLabel("Server");
		serverLabel.setHorizontalAlignment(SwingConstants.LEFT);
		serverLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		serverLabel.setAlignmentY(1.0f);
		serverPanel.add(serverLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		serverPanel.add(panel_1);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPort);
		
		textField_3 = new JTextField();
		textField_3.setColumns(5);
		panel_1.add(textField_3);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		serverPanel.add(panel_2);
		
		JLabel lblIpAddress = new JLabel("IP   ");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(lblIpAddress);
		
		JLabel label = new JLabel("New label");
		panel_2.add(label);
		
		clientPanel = new JPanel();
		networkPanel.add(clientPanel);
		clientPanel.setLayout(new GridLayout(3,1));
		//clientPanel.setVisible(false);
		
		JLabel clientLabel = new JLabel("Client");
		clientPanel.add(clientLabel);
		
		JPanel clientIpPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) clientIpPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		clientPanel.add(clientIpPanel);
		
		JLabel ipAddressClientLabel = new JLabel("IP");
		clientIpPanel.add(ipAddressClientLabel);
		
		ipAddressClientTextField = new JTextField();
		clientIpPanel.add(ipAddressClientTextField);
		ipAddressClientTextField.setColumns(10);
		
		JPanel clientPortPanel = new JPanel();
		FlowLayout fl_clientPortPanel = (FlowLayout) clientPortPanel.getLayout();
		fl_clientPortPanel.setAlignment(FlowLayout.LEFT);
		clientPanel.add(clientPortPanel);
		
		JLabel portClientPanel = new JLabel("Port");
		clientPortPanel.add(portClientPanel);
		
		textField_4 = new JTextField();
		clientPortPanel.add(textField_4);
		textField_4.setColumns(10);
		
		colorConfigPanel = new JPanel();
		changings.add(colorConfigPanel, BorderLayout.NORTH);
		colorConfigPanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblColor = new JLabel("Color");
		colorConfigPanel.add(lblColor);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel colorRedRow = new JPanel();
		colorConfigPanel.add(colorRedRow);
		
		JLabel lblRed = new JLabel("Red  ");
		lblRed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		colorRedRow.add(lblRed);
		
		textField = new JTextField();
		colorRedRow.add(textField);
		textField.setColumns(5);
		
		colorSliderRed = new JSlider();
		colorSliderRed.setValue(0);
		colorSliderRed.setMinorTickSpacing(1);
		colorSliderRed.setMajorTickSpacing(1);
		colorRedRow.add(colorSliderRed);
		colorSliderRed.setMaximum(255);
		
		JPanel colorGreenRow = new JPanel();
		colorConfigPanel.add(colorGreenRow);
		
		JLabel lblGreen = new JLabel("Green\r\n");
		lblGreen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		colorGreenRow.add(lblGreen);
		
		textField_1 = new JTextField();
		colorGreenRow.add(textField_1);
		textField_1.setColumns(5);
		
		colorSliderGreen = new JSlider();
		colorSliderGreen.setValue(0);
		colorSliderGreen.setMinorTickSpacing(1);
		colorSliderGreen.setMajorTickSpacing(1);
		colorGreenRow.add(colorSliderGreen);
		colorSliderGreen.setMaximum(255);
		
		JPanel colorBluePanel = new JPanel();
		colorConfigPanel.add(colorBluePanel);
		
		JLabel lblBlue = new JLabel("Blue");
		colorBluePanel.add(lblBlue);
		lblBlue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_2 = new JTextField();
		colorBluePanel.add(textField_2);
		textField_2.setColumns(5);
		
		colorSliderBlue = new JSlider();
		colorSliderBlue.setValue(0);
		colorBluePanel.add(colorSliderBlue);
		colorSliderBlue.setMajorTickSpacing(1);
		colorSliderBlue.setMinorTickSpacing(1);
		colorSliderBlue.setMaximum(255);
		
		JPanel tabs = new JPanel();
		configPanel.add(tabs, BorderLayout.SOUTH);
		tabs.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel tabPanel = new JPanel();
		tabs.add(tabPanel);
		tabPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.X_AXIS));
		
		network = new JButton("Network Settings");
		tabPanel.add(network);
		
		
		
		//button panel
		JPanel southPanel = new JPanel();
		southPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		stopPntBtn = new JButton("Stop Paint");
		stopPntBtn.setVisible(false);
		addRectBtn = new JButton("Rectangle");
		southPanel.add(addRectBtn);
		southPanel.add(stopPntBtn);
		
		
		//CANVAS (PAINT AREA)
		getContentPane().add(paintArea, BorderLayout.CENTER);

		
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout fl_menuPanel = (FlowLayout) menuPanel.getLayout();
		fl_menuPanel.setAlignment(FlowLayout.LEFT);
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuPanel.add(menuBar);
		
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);
		
		
		setVisible(true);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JButton getAddRectBtn() {
		return addRectBtn;
	}
	
	public JButton getStopPntBtn() {
		return stopPntBtn;
	}
	
	public PaintArea getPaintArea() {
		return paintArea;
	}

	public JSlider getColorSliderRed() {
		return colorSliderRed;
	}

	public void setColorSliderRed(JSlider colorSliderRed) {
		this.colorSliderRed = colorSliderRed;
	}

	public JSlider getColorSliderGreen() {
		return colorSliderGreen;
	}

	public void setColorSliderGreen(JSlider colorSliderGreen) {
		this.colorSliderGreen = colorSliderGreen;
	}

	public JSlider getColorSliderBlue() {
		return colorSliderBlue;
	}

	public void setColorSliderBlue(JSlider colorSliderBlue) {
		this.colorSliderBlue = colorSliderBlue;
	}

	public JMenuItem getMntmNew() {
		return mntmNew;
	}

	public void setMntmNew(JMenuItem mntmNew) {
		this.mntmNew = mntmNew;
	}

	public JMenuItem getMntmSave() {
		return mntmSave;
	}

	public void setMntmSave(JMenuItem mntmSave) {
		this.mntmSave = mntmSave;
	}

	public JMenuItem getMntmOpen() {
		return mntmOpen;
	}

	public void setMntmOpen(JMenuItem mntmOpen) {
		this.mntmOpen = mntmOpen;
	}

	public JButton getNetwork() {
		return network;
	}

	public void setNetwork(JButton network) {
		this.network = network;
	}

	public JPanel getServerPanel() {
		return serverPanel;
	}

	public void setServerPanel(JPanel serverPanel) {
		this.serverPanel = serverPanel;
	}

	public JPanel getColorConfigPanel() {
		return colorConfigPanel;
	}

	public void setColorConfigPanel(JPanel colorConfigPanel) {
		this.colorConfigPanel = colorConfigPanel;
	}

	public JPanel getClientPanel() {
		return clientPanel;
	}

	public void setClientPanel(JPanel clientPanel) {
		this.clientPanel = clientPanel;
	}

	public JPanel getNetworkPanel() {
		return networkPanel;
	}

	public void setNetworkPanel(JPanel networkPanel) {
		this.networkPanel = networkPanel;
	}

	public JPanel getChangings() {
		return changings;
	}

	public void setChangings(JPanel changings) {
		this.changings = changings;
	}
	
	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof Color) {
			
			// Color text Fields
			textField.setText(( (Color) arg).getRed() + "");
			textField_1.setText(((Color) arg).getGreen() + "");
			textField_2.setText(((Color) arg).getBlue() + "");
			
		}
		
	}
	

}

