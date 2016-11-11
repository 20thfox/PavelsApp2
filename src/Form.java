import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class Form extends JFrame {

    private JLabel Label1;
    private JPanel Panel;
    private JTable table1;
    private JLabel CountLBL;
    private personModel model;



    public Form(String s){
        super(s);
        setDefaultCloseOperation(Form.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setContentPane(Panel);
        //Label1.setText("Name");
        Label1.setText(person.getEventName());
        CountLBL.setText("Собрано:");

        model = new personModel();
        table1.setModel(model);

        JMenuBar menu = new JMenuBar();
        menu.add(createFileMenu());
        menu.add(createAddMenu());
        setJMenuBar(menu);
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

    class addAction extends AbstractAction {
        addAction() {
            putValue(NAME, "Добавить участника");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            person person = new person();
            person.setName(JOptionPane.showInputDialog("Введи имя"));
            person.setCash(JOptionPane.showInputDialog("Сумма вклада"));
            Main.persons.add(person);
            table1.updateUI();
        }
    } //для удаления Main.person.remove(tabel get selected row) и tabel update UI

    class removeAction extends AbstractAction {
        removeAction() {putValue(NAME,"Удалить участника");}

        @Override
        public void actionPerformed(ActionEvent e) {
            if (table1.getSelectedRow() == -1 || Main.persons.size() < 0) {return;}
            Main.persons.remove(table1.getSelectedRow());
            table1.updateUI();
        }
    }

    class createAction extends AbstractAction {
        createAction() {putValue(NAME,"Новое событие");}
        @Override
        public void actionPerformed(ActionEvent e) {
            person.setEventName(JOptionPane.showInputDialog("Введи название"));
            Label1.setText(person.getEventName());
        }
    }

    class exitAction extends  AbstractAction {
        exitAction() {putValue(NAME,"Выход");}
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
