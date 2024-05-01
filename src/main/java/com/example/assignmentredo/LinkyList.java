package com.example.assignmentredo;
import Models.Container;
import Models.ContainerShips;
import Models.Pallet;
import Models.Port;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkyList<F> implements Iterable<F> {

    private Node<F> head; //the head (start) of the linked list
    private int size; // Keep track of the number of elements in the list

    @Override
    public Iterator<F> iterator() {
        return new LinkyListIterator(); // Return an iterator for this LinkyList
    }

    // Private inner class for the iterator
    private class LinkyListIterator implements Iterator<F> {
        private Node<F> current = head; // Current node

        @Override
        public boolean hasNext() {
            return current != null; // Check if there are more elements in the list
        }

        public F next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator.");
            }
            F data = current.data; // Get the data from the current node
            current = current.next; // Move to the next node
            return data;
        }
    }

    // represents nodes in the linked list
    private static class Node<F> {
        private F data; // Data stored in the node
        private Node<F> next; // Reference to the next node in the list

        Node(F data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    // Method to add an element to the linked list
    public void add(F data) {
        Node<F> newNode = new Node<>(data); // Create a new node with the given data
        if (head == null) {
            head = newNode; // If the list is empty, set the new node as the head
        } else {
            Node<F> current = head;
            while (current.next != null) {
                current = current.next; //find the last node
            }
            current.next = newNode; // Add the new node to the end of the list
        }
        size++; // 1+ size of the list
    }

    public void remove(F data){
        if (head.data.equals(data)){
            head = head.next;
            size--;
            return;
        }
        Node<F> current = head;
        while (current.next != null){
            if (current.next.data.equals(data)){
                current.next = current.next.next;
                size--;
                return;
            }
            current=current.next;
        }
    }

    public boolean isEmpty(){
        return head==null;
    }


    public void listAllPorts() {
        if (head == null) {
            System.out.println("No ports found.");
        } else {
            Node<F> current = head;
            while (current != null) {
                F port = current.data;
                System.out.println("Port: " + port.toString());
                System.out.println("-----------------------------");
                current = current.next;
            }
        }
    }

    public void listAllShips(Port port) {
        if (port == null) {
            System.out.println("Invalid port");
            return;
        } Iterator<ContainerShips> iterator = port.ships.iterator();
        if (!iterator.hasNext()){
            System.out.println("No ships found at " + port.getName() + " port");
        } else {
            System.out.println("Ships at " + port.getName() + " port:");
            while (iterator.hasNext()){
                ContainerShips ships = iterator.next();
                System.out.println("Ship name: " + ships.getName());
                System.out.println("Ship ID: " + ships.getShipID());
                System.out.println("Ship country: " + ships.getCountry());
                System.out.println("Ship photo: " + ships.getPhotograph());
                System.out.println("---------------------------------------");
            }
        }
    }

    public void listAllContainersAtPort(Port port) {
        if (port == null) {
            System.out.println("Invalid port");
            return;
        }

        Iterator<ContainerShips> shipIterator = port.ships.iterator();

        if (!shipIterator.hasNext()) {
            System.out.println("No ships found at " + port.getName() + " port");
        } else {
            System.out.println("Containers at " + port.getName() + " port:");
            while (shipIterator.hasNext()) {
                ContainerShips ship = shipIterator.next();
                System.out.println("Ship name: " + ship.getName());

                Iterator<Container> containerIterator = ship.container.iterator();
                if (!containerIterator.hasNext()) {
                    System.out.println("No containers on this ship");
                } else {
                    while (containerIterator.hasNext()) {
                        Container container = containerIterator.next();
                        System.out.println("Container ID: " + container.getContainerID());
                        System.out.println("Container Size: " + container.getContainerSize());
                        System.out.println("---------------------------------------");
                    }
                }
            }
        }
    }

    public void listAllPalletsAtPort(Port port) {
        if (port == null) {
            System.out.println("Invalid port");
            return;
        }

        Iterator<ContainerShips> shipIterator = port.ships.iterator();

        if (!shipIterator.hasNext()) {
            System.out.println("No ships found at " + port.getName() + " port");
        } else {
            System.out.println("Pallets at " + port.getName() + " port:");
            while (shipIterator.hasNext()) {
                ContainerShips ship = shipIterator.next();
                System.out.println("Ship name: " + ship.getName());

                Iterator<Container> containerIterator = ship.container.iterator();
                if (!containerIterator.hasNext()) {
                    System.out.println("No containers on this ship");
                } else {
                    while (containerIterator.hasNext()) {
                        Container container = containerIterator.next();
                        System.out.println("Container ID: " + container.getContainerID());
                        System.out.println("Pallets in container:");

                        Iterator<Pallet> palletIterator = container.pallet.iterator();
                        if (!palletIterator.hasNext()) {
                            System.out.println("No pallets in this container");
                        } else {
                            while (palletIterator.hasNext()) {
                                Pallet pallet = palletIterator.next();
                                System.out.println(" - Pallet Description: " + pallet.getDescription());
                                System.out.println("   Quantity: " + pallet.getQuantity());
                                System.out.println("   Unit Value: " + pallet.getUnitValue());
                                // Add other pallet fields as needed
                                System.out.println("------------------------------");
                            }
                        }
                    }
                }
            }
        }
    }

    public void listAllShipLocations() {
        if (head == null) {
            System.out.println("No ships found.");
        } else {
            Node<F> current = head;
            while (current != null) {
                if (current.data instanceof ContainerShips) {
                    ContainerShips ship = (ContainerShips) current.data;
                    System.out.println("Ship: " + ship.getName() + " (Ship ID: " + ship.getShipID() + ")");
                    if (ship.isAtSea()) {
                        System.out.println("Location: At Sea");
                    } else {
                        Port port = ship.getPort();
                        if (port != null) {
                            System.out.println("Location: Docked at Port " + port.getName() + " (Port Code: " + port.getPortCode() + ")");
                        } else {
                            System.out.println("Location: Docked, but port information is missing.");
                        }
                    }
                    System.out.println("------------------------------");
                }
                current = current.next;
            }
        }
    }

    public  void listPalletValue(){
        if (head==null){
            System.out.println("no pallets found.");
        }else {
            Node<F> current = head;
            while (current != null){
                if (current.data instanceof Pallet){
                    Pallet pallet = (Pallet) current.data;
                    System.out.println("Pallet ID:" + pallet.getDescription());
                    System.out.println("Pallet Value: " + pallet.getUnitValue());
                    System.out.println("---------------------------------------");
                }
                current = current.next;
            }
        }
    }








}
