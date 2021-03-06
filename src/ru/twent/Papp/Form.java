package ru.twent.Papp;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;

public class Form extends JFrame {

    private JLabel Label1;
    private JPanel Panel;
    private JTable table1;
    private JLabel CountLBL;
    private JScrollPane ScrollPane;
    private JButton btnadd;
    private JButton btndel;
    private JButton btnrefresh;
    private personModel model;


    public Form(String s){
        super(s);
        setDefaultCloseOperation(Form.EXIT_ON_CLOSE);
        setSize(500,400);
        setLocationRelativeTo(null);
        setContentPane(Panel);
        //Label1.setText("Name");

        Label1.setText(person.getEventName());
        if (Main.eventName == null){
            person.setEventName(JOptionPane.showInputDialog("Введи название события"));
            Label1.setText(person.getEventName());
        }

        model = new personModel();
        table1.setModel(model);
        table1.setFillsViewportHeight(true);

        btnadd.setText("Удалить");
        btndel.setText("Добавить");
        btnrefresh.setText("Обновить");
        btnadd.addActionListener(new removeAction());
        btndel.addActionListener(new addAction());
        btnrefresh.addActionListener(new refreshAction());

        TableColumn drinkColumn = table1.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Пиво");
        comboBox.addItem("Водка");
        comboBox.addItem("Виски");
        comboBox.addItem("Ром");
        comboBox.addItem("Шампанское");
        comboBox.addItem("Текила");
        comboBox.addItem("None");
        drinkColumn.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn anotColumn = table1.getColumnModel().getColumn(3);
        anotColumn.setPreferredWidth(150);

        JMenuBar menu = new JMenuBar();
        menu.add(createFileMenu());
        menu.add(createAddMenu());
        setJMenuBar(menu);
        summary();
    }



    private JMenu createFileMenu () {
        JMenu file = new JMenu("Файл");
        JMenuItem create = new JMenuItem(new createAction());
        JMenuItem exit = new JMenuItem(new exitAction());
        file.add(create);
        file.add(exit);
        return file;
    }
    private JMenu createAddMenu() {
        JMenu option = new JMenu("Опции");
        JMenuItem body = new JMenuItem(new addAction());
        JMenuItem remove_body = new JMenuItem(new removeAction());
        option.add(body);
        option.add(remove_body);
        return option;
    }





    private class addAction extends AbstractAction {
        addAction() {
            putValue(NAME, "Добавить");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            person person = new person();
            person.setName(JOptionPane.showInputDialog("Введи имя"));
            try {
                person.setCash(Integer.parseInt(JOptionPane.showInputDialog("Сумма вклада")));
            }catch (Exception r){
                JOptionPane.showMessageDialog(null,"Вы ввели не число");
            }
            Main.persons.add(person);
            summary();
            table1.updateUI();
        }
    } //для удаления Main.person.remove(tabel get selected row) и tabel update UI

    private class removeAction extends AbstractAction {
        removeAction() {putValue(NAME,"Удалить участника");}

        @Override
        public void actionPerformed(ActionEvent e) {
            if (table1.getSelectedRow() == -1 || Main.persons.size() < 0) {return;}
            Main.persons.remove(table1.getSelectedRow());
            summary();
            table1.updateUI();
        }
    }

    private class createAction extends AbstractAction {
        createAction() {putValue(NAME,"Новое событие");}
        @Override
        public void actionPerformed(ActionEvent e) {
            person.setEventName(JOptionPane.showInputDialog("Введи название"));
            Label1.setText(person.getEventName());
            Main.persons.clear();
            table1.updateUI();
            CountLBL.setText("Собрано:" + 0 + " руб");
        }
    }

    private class exitAction extends  AbstractAction {
        exitAction() {putValue(NAME,"Выход");}
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.serData("persons",Main.persons);
            Main.serData("EventName",Main.eventName);
            System.exit(0);
        }
    }

    private class refreshAction extends AbstractAction {
        refreshAction () {putValue(NAME,"Обновить");}


        @Override
        public void actionPerformed(ActionEvent e) {
            summary();
        }
    }

    private void  summary () {
        int Totalcount = 0;
        for (int i = 0; i < table1.getRowCount(); i++) {
            Totalcount += ((int)table1.getValueAt(i, 1));
        }
        CountLBL.setText("Собрано:" + Totalcount + " руб");
    }
}
