import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Controller {
	
	
	//private Rectangle rect = new Rectangle(200,300,300,300);


	private PaintArea paintArea;
	private MyFormTemplate activeForm; //model
	private GuiForm view = new GuiForm();
	private FileIo fileIo = new FileIo();
	private Server server;
	private Client client;
	//Pattern that is controlling whether the ip address is correct
	private static final Pattern PATTERN = Pattern.compile(
			"^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	Thread thread;
	//Timer for animation
	Timer timerRepaint;

	//Timer for Drag & Drop
	Timer followMouse;

	//bool for active Drag and Drop
	private boolean isDragged;
	private boolean allowDrag;



    public Controller(){

    	//Drag n Drop
    	isDragged = false;
    	allowDrag = false;

    	
    	//Disable workspace
		view.disableWorkSpace();
    	
    	//get Canvas
    	paintArea = view.getPaintArea();

    	// Timer for repaint canvas
		activateRepainter();

    	//add rects
    	paintArea.addRect(100, 100);
    	paintArea.addRect(250, 100);
    	paintArea.addCircle(300,300);

    	//Instance client and server after paintArea is completely loaded
		client = new Client(paintArea);
    	server = new Server(paintArea.getForms());

    	//connect observer and observable at startup
    	connectObservers();

    	//Add View as observer to Server
    	server.addObserver(view);

    	//set IP address
		server.getIpAddress();

		//start Server
		view.getServerStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getServerPortTextField().getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Error: Please insert a port between 1000 and 9999.", "Error Massage",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						int port = Integer.parseInt(view.getServerPortTextField().getText());
						if (port < 1000 || port > 9999) {
							JOptionPane.showMessageDialog(null,
									"Error: Please insert a port between 1000 and 9999.", "Error Massage",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							server.setThreadIsRunning(true);
							server.setPort(port);
							//server.startServer(Integer.parseInt(view.getServerPortTextField().getText()));
							thread = new Thread(server);
							thread.start();
							view.disableForServer();
						}
					}
					catch (Exception be) {

					}
				}
			}
		});

		//Stop Server
		view.getServerStopButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				server.setThreadIsRunning(false);
				view.enableForServer();
			}
		});

		//start Client
		view.getClientStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (view.getClientPortTextField().getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Error: Please insert a port between 1000 and 9999.", "Error Massage",
								JOptionPane.ERROR_MESSAGE);
					}
					int port = Integer.parseInt(view.getClientPortTextField().getText());
					if (port < 1000 || port > 9999) {
						JOptionPane.showMessageDialog(null,
								"Error: Please insert a port between 1000 and 9999.", "Error Massage",
								JOptionPane.ERROR_MESSAGE);
					}
					String host = view.getClientIpAddressTextField().getText();
					if (!PATTERN.matcher(host).matches()) {
						JOptionPane.showMessageDialog(null,
								"Error: Please insert a correct IP address (f.e.141.22.65.73).", "Error Massage",
								JOptionPane.ERROR_MESSAGE);
					} else {
						client.setPort(port);
						client.setHost(host);
						client.start();
						view.disableForClient();
						client.setClientIsRunning(true);
					}
				}
				catch (Exception ec) {

				}
			}
		});

		//stop Client
		view.getClientStopButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.stopConnection();
				client.setThreadIsRunning(false);
				view.enableForClient();
				client.setClientIsRunning(false);
			}
		});


    	view.getColorSliderRed().addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if(activeForm != null) {
					int redValue = view.getColorSliderRed().getValue();
					activeForm.setR(redValue);
					System.out.println("(Listener)Redvalue: " + activeForm.getR());
					
					//update internal color
					activeForm.setC();
					paintArea.repaint();
				}
			}
		});
    	
		view.getColorSliderGreen().addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if(activeForm != null) {
					int greenValue = view.getColorSliderGreen().getValue();
					activeForm.setG(greenValue);
					System.out.println("(Listener)Greenvalue: " + activeForm.getG());
					
					//update internal color
					activeForm.setC();
					paintArea.repaint();
				}
			}
		});
		
		view.getColorSliderBlue().addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if(activeForm != null) {
					int blueValue = view.getColorSliderBlue().getValue();
					activeForm.setB(blueValue);
					System.out.println("(Listener)Bluevalue:"+ activeForm.getB());
					
					//update internal color
					activeForm.setC();
					paintArea.repaint();
				}
			}
		});
			
		
		// COLOR TEXTAREA
		view.getRedValue().addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				 
				 int value = Integer.parseInt(view.getRedValue().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Please insert a correct number between 0 an 255.", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderRed().setValue(value);
			     }
			
			}
		});
		
		view.getGreenValue().addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				 
				 int value = Integer.parseInt(view.getGreenValue().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Please insert a correct number between 0 an 255.", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderGreen().setValue(value);
			     }
			
			}
		});
		
		view.getBlueValue().addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				
				 int value = Integer.parseInt(view.getBlueValue().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Please insert a correct number between 0 an 255.", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderBlue().setValue(value);
			     }
			}
		});

		
		view.getxSpinner().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				activeForm.setX((Integer)view.getxSpinner().getValue()); // Jspinner value need to be casted to an Integer
				paintArea.repaint();
			}
		});

		view.getySpinner().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				activeForm.setY((Integer)view.getySpinner().getValue());
				paintArea.repaint();
			}
		});


		//Dimensions ------
		// Rectangle

		view.getWidthSpinner().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				if (activeForm instanceof MyRectangle) {
					((MyRectangle) activeForm).setWidth((Integer) view.getWidthSpinner().getValue());
					System.out.println(((MyRectangle) activeForm).getWidth() + " ");
					paintArea.repaint();
				}

				// change circle width

				if(activeForm instanceof MyCircle){
					((MyCircle)activeForm).setWidth((Integer)view.getWidthSpinner().getValue());
					System.out.println(((MyCircle) activeForm).getWidth() + ": Height ");
					paintArea.repaint();
				}
			}
		});


		view.getHeightSpinner().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				//change height rectangle
				if (activeForm instanceof MyRectangle) {

					((MyRectangle) activeForm).setHeight((Integer) view.getHeightSpinner().getValue());
					System.out.println(((MyRectangle) activeForm).getHeight() + ": Height ");
					paintArea.repaint();
				}

				if(activeForm instanceof MyCircle){

					((MyCircle)activeForm).setHeight((Integer)view.getHeightSpinner().getValue());
					System.out.println(((MyCircle) activeForm).getHeight() + ": Height ");
					paintArea.repaint();
				}
			}
		});


		view.getSizeSlider().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				activeForm.setSize(view.getSizeSlider().getValue());
				paintArea.repaint();
			}
		});

		//ANIMATION CHECKBOX LISTENER

		view.getSinusCheckBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == ItemEvent.SELECTED){

					activeForm.setSinus(true);
					activeForm.setSinusMovement(5, view.getBounds().height, view.getBounds().width);

				} else {
					activeForm.setSinus(false);
					activeForm.stopSinusMovement();
				}

			}
		});

		view.getRandomColorCheckBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){

					activeForm.setRandomClr(true);
					activeForm.setRandomColorAnim(1,5,10);
				} else {
					activeForm.setRandomClr(false);
					activeForm.stopRanfomColorAnim();
				}
			}
		});

		// -------
		// BOTTOM TAB
		// -------
		
    	view.getAddRectBtn().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				view.getStopPntBtn().setEnabled(true);
				//Paintstate [1](Rectangle)
				System.out.println("PaintState changed to 1");
				paintArea.setPaintState(1);
			}
		});


    	view.getCircleBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getStopPntBtn().setEnabled(true);

				paintArea.setPaintState(2);
				System.out.println("PaintState changed to 2");
			}
		});



    	view.getStopPntBtn().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				//Disable PaintStopButton
				view.getStopPntBtn().setEnabled(false);


				//PaintState reset to 0. 
				System.out.println("PaintState resetet: " + paintArea.getPaintState());
				paintArea.resetPaintState();
			}
		});

    	view.getDeleteBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintArea.deleteActiveLayer();
				view.getDeleteBtn().setEnabled(false);
			}
		});

    	//Enable Drag and Drop
    	view.getMoveObjectsButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allowDrag = !allowDrag;

				if(allowDrag == true){
					view.getMoveObjectsButton().setText("Disable Moving");
				} else {
					view.getMoveObjectsButton().setText("Activate Moving");
				}

			}
		});

    	
    	// MOUSELISTENER
    	paintArea.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if(isDragged == true){
					followMouse.stop();
					isDragged = false;
					view.updateWorkSpace(activeForm);

				}
			}
			public void mousePressed(MouseEvent e) {

				//---
				//PAINTSTATE 0
				// ---
				//sets active layer if object is clicked
				paintArea.objectsInPos(e.getX(), e.getY());

				//sets active FORM - for editing
				activeForm = paintArea.getActiveForm();

                // Workspace update when active activeForm(Layer) is changed
                if(!client.IsClientStarted()) {
                    if (paintArea.getActiveLayer() == -1) {
                        view.disableWorkSpace();
                        view.getDeleteBtn().setEnabled(false);
                    } else {
                        view.enableWorkSpace();
                        view.getDeleteBtn().setEnabled(true);
                    }

                    view.updateWorkSpace(activeForm);
                }

                // Drag and Drop

				if(allowDrag){
					if(paintArea.getPaintState() == 0){
						if(paintArea.getActiveLayer() != -1){
							isDragged = true;
							followMouse.start();
						}
					}
				}


				//PAINT STATE 1
				//---
				//ADD RECTANGLE

				int dimensions = 25; // starting size for rectangles

				if(paintArea.getPaintState() == 1) {

					paintArea.addRect(e.getX() - dimensions, e.getY() - dimensions);
					paintArea.repaint();

					// Connect created Object with Observer
					addLastObserver();
				}

				//PAINT STATE 2
				//---
				//Add Circle

				if(paintArea.getPaintState() == 2){

					paintArea.addCircle(e.getX(), e.getY());
					paintArea.repaint();

					//Connect created Object with observer
					addLastObserver();
				}

			}


			public void mouseExited(MouseEvent e) {


			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseClicked(MouseEvent e) {

			}


		});


    	//Mouse Dragged TimerEvent

		followMouse = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(paintArea.getMousePosition() != null){

					//get mousePosition from paintArea component
					activeForm.setX((int)(paintArea.getMousePosition().getX())); // Jspinner value need to be casted to an Integer
					activeForm.setY((int)(paintArea.getMousePosition().getY()));
					paintArea.repaint();
				}
			}
		});


		//



    	//ActionListener for JMenuBar
    	//--------------------------------

    	//ActionListener for "New"-button, clears screen
    	view.getMntmNew().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				paintArea.clearForms();
				
			}
		});
    	
    	//ActionListener for "Save"-button, saves data in txt file
    	view.getMntmSave().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				fileIo.saveData(paintArea);
			}
		});

		//ActionListener for "Open"-button, takes data from txt file
		view.getMntmOpen().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileIo.openData();
				paintArea.repaint();
			}
		});

    }

	public void addLastObserver() {
		paintArea.returnLast().getObserver().addObserver(view);
	}
    
    public void connectObservers() {
    	 	
    	if(paintArea.getForms().size() != 0) {
    		
    		ArrayList<MyFormTemplate> forms = paintArea.getForms();

        	for(MyFormTemplate form: forms) {
        		form.getObserver().addObserver(view);
        	}
    	}
    	
    }

    public void activateRepainter(){
		//Timer for moving the Form in a sinus curve
		timerRepaint = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintArea.repaint();
			}
		});
		timerRepaint.start();
	}
}


