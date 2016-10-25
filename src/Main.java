import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<person> persons = new ArrayList<>();

    public static void main(String[] args) {
        persons = (ArrayList<person>) deserData("persons");
        Form f = new Form("Form");
        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                serData("persons",persons);
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        f.setVisible(true);
    }

    private static Object deserData(String file_name) {
        Object retObj = null;
        try {
            FileInputStream fileIn = new FileInputStream(file_name + ".ser");  //считывает файл в массив байт
            ObjectInputStream in = new ObjectInputStream(fileIn);  //преобразует массив байт в объект
            retObj = in.readObject();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода");
            System.exit(2);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
            System.exit(3);
        }
        return retObj;
    }

    public static void serData(String file_name, Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file_name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            fileOut.close();
            out.close();
        }catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода");
            System.exit(2);
        }
    }
}
