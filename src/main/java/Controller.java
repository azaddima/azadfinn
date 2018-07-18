import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Controller {
	
	
	private Rectangle rect = new Rectangle(200,300,300,300);


	private PaintArea paintArea;
	private MyFormTemplate activeForm; //model

	private GuiForm view = new GuiForm();
	//private Gui view = new Gui();

	private FileIo fileIo = new FileIo();

	    
    public Controller(){
    	
    	
    	
    	//get Canvas
    	paintArea = view.getPaintArea();
    	
    	//add rects
    	paintArea.addRect(100, 100);
    	paintArea.addRect(250, 100);
    	
    	//connect observer and observable at startup
    	connectObservers();
    	
    	
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
		view.getTextField().addKeyListener(new KeyAdapter() {
			
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				 
				 int value = Integer.parseInt(view.getTextField().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Eine gültige Zahl eingeben: 0 -255", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderRed().setValue(value);
			     }
			
			}
		});
		
		view.getTextField_1().addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				 
				 int value = Integer.parseInt(view.getTextField_1().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Eine gültige Zahl eingeben: 0 -255", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderGreen().setValue(value);
			     }
			
			}
		});
		
		view.getTextField_2().addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkChange();
				}
			}
			
			public void checkChange() {
				
				 int value = Integer.parseInt(view.getTextField_2().getText());
			     if ( value < 0 || value > 255){
			       JOptionPane.showMessageDialog(null,
			          "Error: Eine gültige Zahl eingeben: 0 -255", "Error Massage",
			          JOptionPane.ERROR_MESSAGE);
			     } else {
			    	 view.getColorSliderBlue().setValue(value);
			     }
			}
		});


		// TODO: 18.07.2018 FIX ERROR: when no current layer is selected
		
		view.getxSpinner().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				//
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
    	
    	view.getStopPntBtn().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				//Disable PaintStopButton
				view.getStopPntBtn().setEnabled(false);
				
				//PaintState reset to 0. 
				System.out.println("PaintState resetet: " + paintArea.getPaintState());
				paintArea.resetPaintState();
			}
		});
    	
    	
    	// MOUSELISTENER
    	paintArea.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {

			}
			public void mousePressed(MouseEvent e) {

				//---
				//PAINTSTATE 0
				// ---
				//sets active layer if object is clicked
				paintArea.objectsInPos(e.getX(), e.getY());

				//sets active FORM - for editing
				activeForm = paintArea.getActiveRect();

                // Updates "Work Space" when active activeForm is changed
				view.updateWorkSpace(activeForm);

				//---
				//PAINT STATE 1
				//---
				//ADD RECTANGLE
				int dimensions = 25; // starting size for rectangles
				System.out.println("PaintState: " + paintArea.getPaintState());
				if(paintArea.getPaintState() == 1) {

					paintArea.addRect(e.getX() - dimensions, e.getY() - dimensions);
					paintArea.repaint();

					// Connect created Object with Observer
					addLastObserver();
				}
			}


			public void mouseExited(MouseEvent e) {


			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseClicked(MouseEvent e) {
				//Form follows MouseEvent x/y
				//followMouse(e);

			}

			public void followMouse(MouseEvent e){

				// TODO: 18.07.2018 LAYERING !!!!!!
				activeForm.setX(e.getX() - 25); // Jspinner value need to be casted to an Integer
				activeForm.setY(e.getY() - 25);
				paintArea.repaint();
			}
		});



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
    	
//    	view.getPaintArea().repaint();

       
        
//        Timer timer = new Timer(10, new ActionListener() {
//        	
//        	public void actionPerformed(ActionEvent e) {
//
//        		
//                rect.x += 5; // alle 10 ms
//
//                rect.y = (int) ((view.getBounds().height / 4) * Math.sin(rect.x * 0.005f)) + (view.getBounds().height / 2) -150;
//
//                if(rect.x > view.getBounds().width){
//                    rect.x = -230;
//                }
//
//                //change color ------------------
//                Color c = view.getPaintArea().getC();
//                int r = c.getRed();
//                int g = c.getGreen();
//                int b = c.getBlue();
//
//                r++;
//                g+= 10;
//                b+= 5;
//
//                r %= 255;
//                g %= 255;
//                b %= 255;
//
//                c = new Color(r,g,b);
//                view.getPaintArea().setC(c);
//
//
//                view.getPaintArea().repaint();
//
////                for (int i = 0; i < 10; i++) {
////
////                }
//                
//            }
//        });

//        timer.start();
        
    }

	public void addLastObserver() {
		paintArea.returnLast().getObserver().addObserver(view);
	}
    
    public void connectObservers() {
    	 	
    	if(paintArea.getForms().size() != 0) {
    		
    		ArrayList<MyRectangle> rects = paintArea.getForms();
    		
        	for(MyRectangle rect: rects) {
        		rect.getObserver().addObserver(view);
        	}
    	}
    	
    }
    
}


