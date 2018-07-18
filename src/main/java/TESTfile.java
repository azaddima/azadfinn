import java.io.*;
import java.util.ArrayList;

public class TESTfile {



    public void saveData(){

        //Change in Method
        MyRectangle form = new MyRectangle(10,10,10,10);
        ArrayList<MyRectangle> rectArray = new ArrayList<>();
        rectArray.add(form);
        rectArray.add(form);
        rectArray.add(form);


        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("test.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rectArray);
            oos.flush();

            fos.close();
            oos.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openData(){

        try {

            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));

            ArrayList<MyRectangle> loadArray = (ArrayList<MyRectangle>) ois.readObject();
            for(MyRectangle rectangle : loadArray){
                System.out.println(rectangle.getX() + "");
            }

            ois.close();

            /*
            MyRectangle formLoad = (MyRectangle) ois.readObject();
            System.out.println(formLoad.getX() + "");
            */


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        TESTfile file = new TESTfile();

        file.saveData();
        file.openData();

    }

}
