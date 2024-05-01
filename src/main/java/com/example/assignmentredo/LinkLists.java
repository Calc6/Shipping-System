package com.example.assignmentredo;

import Models.Container;
import Models.ContainerShips;
import Models.Port;

public class LinkLists {
    private LinkyList<Port> port;
    private LinkyList<ContainerShips> ships;
    private LinkyList<Container> containers;

    public LinkLists(LinkyList<Port> port) {
        this.port = port;
    }


//getter and setters



    public LinkyList<Port> getPort() {
        return port;
    }

    public void setPort(LinkyList<Port> port) {
        this.port = port;
    }
}


