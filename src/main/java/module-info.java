module com.example.assignmentredo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignmentredo to javafx.fxml;
    exports com.example.assignmentredo;
    exports Models;
    opens Models to javafx.fxml;
}