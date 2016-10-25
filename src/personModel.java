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
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

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
        }
        return return_Object;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                Main.persons.get(rowIndex).setName((String) aValue);
            case 1 :
                Main.persons.get(rowIndex).setCash((String) aValue);
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
