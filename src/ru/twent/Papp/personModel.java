package ru.twent.Papp;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class personModel implements TableModel {
    private ArrayList<TableModelListener> listeners;

    public personModel() {
        listeners = new ArrayList<>();
    }


    @Override
    public int getRowCount() {
        return Main.persons.size();
    } //количество строк, которое берется из длинны коллекции

    @Override
    public int getColumnCount() {
        return 4;
    }   //количество столбцов

    @Override
    public String getColumnName(int columnIndex) {
        String return_string = "";
        switch (columnIndex) {
            case 0:
                return_string = "Имя";
                break;
            case 1:
                return_string = "Вклад";
                break;
            case 2:
                return_string = "Напиток";
                break;
            case 3:
                return_string = "Примечание";
                break;
        }
        return return_string;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object return_Object = null;
        switch (columnIndex) {
            case 0 :
                return_Object = Main.persons.get(rowIndex).getName();
                break;
            case 1 :
                return_Object = Main.persons.get(rowIndex).getCash();
                break;
            case 2:
                return_Object = Main.persons.get(rowIndex).getDrink();
                break;
            case 3:
                return_Object = Main.persons.get(rowIndex).getAnotation();
                break;
        }
        return return_Object;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                Main.persons.get(rowIndex).setName((String) aValue);
                break;
            case 1 :
                try{
                Main.persons.get(rowIndex).setCash(Integer.parseInt((String) aValue) );} catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Вы ввели не число");}
                break;
            case 2 :
                Main.persons.get(rowIndex).setDrink((String) aValue);
                break;
            case 3 :
                Main.persons.get(rowIndex).setAnotation((String) aValue);
                break;
        }

    }




    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);


    }
}
