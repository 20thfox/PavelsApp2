package ru.twent.Papp;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<person> persons = new ArrayList<>();
    public static String eventName;

    public static void main(String[] args) {
        persons = (ArrayList<person>) deserData("persons");
        eventName = (String) deserData("EventName");
        Form f = new Form("Form");
        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                serData("persons",persons);
                serData("EventName",eventName);
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
            FileInputStream fileIn = new FileInputStream(file_name + ".mdk");  //считывает файл в массив байт
            ObjectInputStream in = new ObjectInputStream(fileIn);  //преобразует массив байт в объект
            retObj = in.readObject();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            JOptionPane.showMessageDialog(null,"Файлы списков не найдены, мы создали их для вас");
            serData("persons",persons);
            serData("EventName",eventName);
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
            FileOutputStream fileOut = new FileOutputStream(file_name + ".mdk");
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
