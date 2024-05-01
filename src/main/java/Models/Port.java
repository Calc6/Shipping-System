package Models;

import com.example.assignmentredo.LinkyList;

public class Port {
    public LinkyList<ContainerShips> ships;
    private String name;
    private String portCode;
    private String country;





    public Port(String name, String portCode, String country) {
        this.name = name;
        this.portCode = portCode;
        this.country = country;
        this.ships = new LinkyList<ContainerShips>();
    }


    @Override
    public String toString() {
        return "Port: " + name + " (" + portCode + "), Country: " + country;
    }







    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
