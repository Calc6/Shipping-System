package Models;

import com.example.assignmentredo.LinkyList;

public class ContainerShips {
    public LinkyList<Container> container;

    private Port port;
    private String name;
    private String shipID;
    private String country;
    private String photograph;//URL
    private boolean atSea;

    public ContainerShips(String name, String shipID, String country, String photograph) {
        this.name = name;
        this.shipID = shipID;
        this.country = country;
        this.photograph = photograph;

        container = new LinkyList<>();
    }

    public void launch(){
        if (!atSea){
            atSea = true;
        }else {
            System.out.println("Ship is already at sea");
        }
    }

    public void dock(ContainerShips ship){
        if (atSea){
            atSea = false;
        }else {
            System.out.println("Ship is already docked");
        }
    }










    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public Port getPort() {
        return port; // Assuming that 'port' is an instance variable in the ContainerShips class
    }


    public boolean isAtSea(){
        return atSea;
    }

    public void setAtSea(boolean atSea){
        this.atSea = atSea;
    }

    @Override
    public String toString() {
        return "Container Ship: " + name + " (" + shipID + "), Country: " + country + "\n" +
                "Photograph URL: " + photograph + "\n" +
                "Status: " + atSea;
    }
}
