package com.example.assignmentredo;

import Models.Container;
import Models.ContainerShips;
import Models.Pallet;
import Models.Port;
import utils.ScannerInput;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Driver {
    private static com.example.assignmentredo.LinkLists LinkLists;

    public static LinkyList<Port> ports=new LinkyList<>();

    public static void main(String[] args) {
        new Driver();

    }
    public Driver(){
        runMenu();
    }


    private int mainMenu() {
        return ScannerInput.readNextInt("""
                |----------------------------------------|
                |               Port Menu                |
                |----------------------------------------|
                |              Port Options              |
                |----------------------------------------|
                |             1) Add a Port              |
                |            2) List all ports           |
                |----------------------------------------|
                |              Ship Option               |
                |----------------------------------------|
                |             3) Add a Ship              |
                |            4)list all ships            |
                |             5)Launch ship              |
                |              6)Dock ship               |
                |            7)Ship locations            |
                |----------------------------------------|
                |           Container Options            |
                |----------------------------------------|
                |           8)Add Container              |
                |          9)List all Container          |
                |----------------------------------------|
                |           Pallet Options               |
                |----------------------------------------|
                |           10)Add a pallet              |
                |           11)List pallets              |
                |        12)Value of all goods           |
                |          )View all goods               |
                |          14)Remove pallet              |
                |----------------------------------------|
                |            15)reset system             |
                |                0)Exit                  |
                |----------------------------------------|
               ==>>
               """);
    }

    private void runMenu() {
        int option=mainMenu();
        while (option!=0){
            switch (option){
                case 1->addPort(ports);
                case 2->listPorts();

                case 3->addShip();
                case 4->listShips();
                case 5->launchOrDockShip();
                case 6->launchOrDockShip();
                case 7->viewShipLocations();

                case 8->addContainer();
                case 9->listAllContainers();

                case 10->addPallet();
                case 11->listAllPallets();
                case 12->listAllPalletValue();
//                case 13->
                case 14->removePallet();
                case 15-> reset();


                default -> System.out.println("Invalid option entered:" + option);

            }
            ScannerInput.readNextLine("\n Press enter key to continue...");

            option = mainMenu();
        }
        System.out.println("Exiting....bye");
        System.exit(0);



    }


    //ADD methods

    private void addPort(LinkyList<Port> ports) {

        String portName = ScannerInput.readNextLine("Enter the name of the port: ");
        String portCode = ScannerInput.readNextLine("Enter the Unique Code for the port: ");
        String portCounrty = ScannerInput.readNextLine("Enter the country the port is in: ");

        Port port=new Port(portName,portCode,portCounrty);
        ports.add(port);

        System.out.println("Port added: " + port.getName() + " - " + port.getPortCode());
    }

    private void addShip() {

        ports.listAllPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port you wish to add a ship to: ");
        Port port = getPortByName(findPortName);

        if (port != null) {
            String shipName = ScannerInput.readNextLine("Enter ship name: ");
            String shipID = ScannerInput.readNextLine("Enter the ship ID: ");
            String shipCountry = ScannerInput.readNextLine("Enter the what counrty the ship belongs to: ");
            String shipPhoto = ScannerInput.readNextLine("Enter a URL for a photograph of the ship: ");
            ContainerShips newShip = new ContainerShips(shipName, shipID, shipCountry, shipPhoto);

            port.ships.add(newShip);

            System.out.println("Ship added to " + findPortName + " Successfully.");
        }else {
            System.out.println("Port not found");
            }
        }

    private void addContainer() {

        ports.listAllPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port name: ");
        Port port = getPortByName(findPortName);

        if (port != null) {
            listShips();
            String findShipName = ScannerInput.readNextLine("Enter the ship name: ");
            ContainerShips ship = findShipInPort(port, findShipName);


            if (ship != null) {
                String containerID = ScannerInput.readNextLine("Enter the Container ID: ");
                int containerSize = ScannerInput.readNextInt("what size is the container in feet:  ");

                Container newContainer = new Container(containerID, containerSize);


                ship.container.add(newContainer);

                System.out.println("Container added to " + findPortName + "successfully");
            } else {
                System.out.println("Ship not found");
            }
        }
    }



    private void addPallet() {
        ports.listAllPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port name: ");
        Port port = getPortByName(findPortName);

        if (port != null) {
            listShips();
            String findShipName = ScannerInput.readNextLine("Enter the ship name: ");
            ContainerShips ships = findShipInPort(port, findShipName);

            if (ships != null) {
                listAllContainers();
                String containerID = ScannerInput.readNextLine("Enter the Container ID you wish to add your pallet to: ");
                Container container = findContainerInShip(ships, containerID);

                if (container != null) {
                    String description = ScannerInput.readNextLine("Enter the Pallet description: ");
                    int quantity = ScannerInput.readNextInt("Enter the quantity the pallet can hold: ");
                    double unitValue = ScannerInput.readNextDouble("Enter the total value of the pallet: ");
                    double totalWeight = ScannerInput.readNextDouble("Enter the total weight of the pallet: ");
                    double totalSize = ScannerInput.readNextDouble("Enter the total size of the pallet: ");
                    Pallet newPallet = new Pallet(description, quantity, unitValue, totalWeight, totalSize);

                    container.pallet.add(newPallet);

                    System.out.println("Pallet added to container " + containerID + " Successfully");
                } else {
                    System.out.println("Container not found");
                }
            } else {
                System.out.println("Ship not found in the port");
            }
        } else {
            System.out.println("Port not found");
        }
    }

    public void removePallet(){
        ports.listAllPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port name");
        Port port = getPortByName(findPortName);

        if (port != null){
            listShips();
            String findShipName = ScannerInput.readNextLine("Enter the ship name: ");
            ContainerShips ships = findShipInPort(port,findShipName);

            if (ships != null){

                String containerID = ScannerInput.readNextLine("Enter the Container ID: ");
                Container container = findContainerInShip(ships, containerID);

                if (container !=null){


                    String palletDes = ScannerInput.readNextLine("Enter the the descrpiton of the pallet: ");

                    boolean removed = container.removePallet(palletDes);

                    if (removed){
                        System.out.println("Pallet removed frrom container " + containerID + " Successfully");
                    }else {
                        System.out.println("Pallet  with description " + palletDes + " not found in the container");
                    }
                }else {
                    System.out.println("Container not found in the ship");
                }
            }else {
                System.out.println("Ship not found in port");
            }
        }else {
            System.out.println("Port not found");
        }
    }

    private void  viewShipLocations(){
    ports.listAllShipLocations();
    }







    private void launchOrDockShip() {
        ports.listAllPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port where the ship is docked:");
        Port port = getPortByName(findPortName);

        if (port != null) {
            listShips(); // Assuming you have a method to list ships at a specific port
            String findShipName = ScannerInput.readNextLine("Enter the ship you want to launch/dock: ");
            ContainerShips ship = findShipInPort(port, findShipName);

            if (ship != null) {
                if (ship.isAtSea()) {
                    System.out.println("Ship is already at sea. Docking...");
                    ship.dock(ship);
                    System.out.println("Ship " + findShipName + " has been docked at " + port.getName() + " port.");
                } else {
                    System.out.println("Ship is currently docked. Launching...");
                    ship.launch();
                    System.out.println("Ship " + findShipName + " has been launched.");
                }
            } else {
                System.out.println("Ship not found in the port.");
            }
        } else {
            System.out.println("Port not found");
        }
    }


    //FIND METHODS
    private ContainerShips findShipInPort (Port port, String shipName){
        for (ContainerShips ship : port.ships) {
            if (ship.getName().equalsIgnoreCase(shipName)) {
                return ship;
            }
        }
        System.out.println("Ship with name " + shipName + " was not found in the port" );
        return null;
        }

        private Container findContainerInShip(ContainerShips ships,String containerID) {
            for (Container container : ships.container) {
                if (container.getContainerID().equalsIgnoreCase(containerID)) {
                    return container;
                }
            }
            System.out.println("Container with ID " + containerID + " not found in the ship");
            return null;
        }


        //LIST METHODS
        private void listPorts(){
        if (ports != null){
            for (Port port1 : ports){
                System.out.println("Port Name: " + port1.getName());
                System.out.println("Port ID : " + port1.getPortCode());
                System.out.println("Port Country : " + port1.getCountry());
                System.out.println("-------------------------------------");
            }
        }else {
            System.out.println("No ports in system");
        }
    }

        private void listShips(){
        listPorts();
        String findPortName = ScannerInput.readNextLine("Enter the port you wish to find:  ");
        Port port = getPortByName(findPortName);
        if (port != null){
            System.out.println("Ships in port: " + port.getName());
            ports.listAllShips(port);
        }else {
            System.out.println("Port not found");
        }
    }

    private void listAllContainers(){
        String findPortName = ScannerInput.readNextLine("Enter the port you wish to find: ");
        Port port = getPortByName(findPortName);

        if (port != null){
            System.out.println("Containes at port: " + port.getName());
            ports.listAllContainersAtPort(port);
        }else {
            System.out.println("Port not found");
        }
    }


    private void listAllPallets(){
        String findPortName = ScannerInput.readNextLine("Enter the port you wish to find: ");
        Port port = getPortByName(findPortName);

        if (port != null){
            System.out.println("Pallets at this port: " + port.getName());
            ports.listAllPalletsAtPort(port);
        }else {
            System.out.println("Port not found");
        }
    }

    private void listAllPalletValue(){
        listAllPalletValue();
    }



    private Port getPortByName(String portName) {
        for (Port port : ports){
            if (port.getName().equalsIgnoreCase(portName)){
                return port;
            }
        }
        return null;
    }

    public void reset(){
        String answer = ScannerInput.readNextLine("Are you sure you want to reset ALL data? (yes/no)");
        if (answer.equalsIgnoreCase("yes")){
        ports = new LinkyList<Port>();

        System.out.println("All Data has been reset");
    }else {
        System.out.println("Reset Canceled");
        }
    }


    public static LinkLists getLinkLists(){
        return LinkLists;
    }



}