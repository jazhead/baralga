package de.jazhead.baralgafx.controller.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.remast.baralga.model.Project;

public class ProjectSelector extends AbstractControl {

    @FXML
    public ComboBox<Project> projectSelector;

    @FXML
    void initialize() {

        assert projectSelector != null : "fx:id=\"taskSelector\" was not injected: check your FXML file 'RootLayout.fxml'.";
        initProjectSelector();
    }

    private void initProjectSelector() {
        final ObservableList<Project> projects = FXCollections.observableArrayList(this.model.getProjectList());
        projectSelector.setItems(projects);

        // TODO: 26.11.2016 tooltip text

        projectSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(String.format("Choose: %s", observable.getValue()));
            this.model.changeProject(observable.getValue());

            this.mainController.descriptionTextArea.setText("");

//            if (descriptionTextArea != null) {
//                descriptionTextArea.setText("");
//            }
        });
    }
}
