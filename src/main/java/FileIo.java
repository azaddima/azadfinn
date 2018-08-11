import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

//This class takes care of the file input and output, exporting and importing data
public class FileIo {

	private PaintArea paintArea;

	//Method that exports data
	public void saveData(PaintArea paintArea) {
		this.paintArea = paintArea;

		//The user is able to save its data where he wants by which name he wants
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt", "text");
		fileChooser.setFileFilter(filter);
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
			File file = fileChooser.getSelectedFile();
			//process of saving
			try {
				FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				if (paintArea.getForms().size() != 0) {
					System.out.println("Wird gesepeichert");
					oos.writeObject(paintArea.getForms());
					oos.flush();
				}

				fos.close();
				oos.close();
				System.out.println("Es wurde irgendwo was gespeichert.");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//Method of opening data
	public void openData() {

		//The user can choose to open data from where he wants
		JFileChooser fc = new JFileChooser();
		if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
			File file = fc.getSelectedFile();


			try {

				//create ObjectOutputStream for the file we created
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

				//read array in data.txt
				ArrayList<MyFormTemplate> formsFile = (ArrayList<MyFormTemplate>) ois.readObject();

				//set Forms Array in paintArea
				paintArea.setForms(formsFile);
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}