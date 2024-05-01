package Models;

import Models.Pallet;
import com.example.assignmentredo.LinkyList;

public class Container {
    public LinkyList<Pallet> pallet;

    private String ContainerID;
    private int containerSize;//in feet


    public Container(String containerID, int containerSize ) {
        ContainerID = containerID;
        this.containerSize = containerSize;


        pallet = new LinkyList<>();
    }

    public boolean removePallet(String description){
        Pallet palletToRemove = null;
        for (Pallet pallet : pallet){
            if (pallet.getDescription().equalsIgnoreCase(description)){
                palletToRemove = pallet;
            }
        }
        if (palletToRemove != null){
            pallet.remove(palletToRemove);
            return true;
        }
        return false;
    }


    //getter and setters

    public String getContainerID() {
        return ContainerID;
    }

    public void setContainerID(String containerID) {
        ContainerID = containerID;
    }

    public int getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }


}
