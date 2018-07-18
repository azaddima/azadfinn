import java.io.*;
import java.util.ArrayList;

public class FileIo {


	private PaintArea paintArea;


	public void saveData(PaintArea paintArea) {
		this.paintArea = paintArea;
		try {
			File file = new File("Data.txt");
			FileOutputStream fos = new FileOutputStream(file, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			//If Bedingung kann man vorher setzen
			if(paintArea.getForms().size() != 0) {
				System.out.println("Wird gesepeichert");
				oos.writeObject(paintArea.getForms());
				oos.flush();
			}

			fos.close();
			oos.close();
			System.out.println("Es wurde irgendwo was gespeichert.");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void openData() {

		try {

			//create ObjectOutputStream for the file we created
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data.txt"));

			//read array in data.txt
			ArrayList<MyRectangle> formsFile = (ArrayList<MyRectangle>) ois.readObject();

			//set Forms Array in paintArea
			paintArea.setForms(formsFile);

			ois.close();


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}