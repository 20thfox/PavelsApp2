import java.io.Serializable;

public class person implements Serializable {

    private String name;
    private String cash;
    private String drink;
    private String anotation;


    public static String getEventName() {
        return Main.eventName;
    }

    public static void setEventName(String eventName) {
        Main.eventName = eventName;
    }



    public String getDrink() {return drink;}

    public void setDrink(String drink) {this.drink = drink;}

    public String getAnotation() {return anotation;}

    public void setAnotation(String anotation) {this.anotation = anotation;}

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
