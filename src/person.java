import java.io.Serializable;

public class person implements Serializable {

    private String name;
    private String cash;


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
