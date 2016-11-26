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
        this.update();
    }

    private void initProjectSelector() {

        // TODO: 26.11.2016 tooltip text

        final ObservableList<Project> projects = getProjectList();
        projectSelector.setItems(projects);

        projectSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.model.changeProject(observable.getValue());
            this.mainController.descriptionTextArea.setText("");
        });
    }

    @Override
    public void update() {
        projectSelector.setItems(getProjectList());
    }

    private ObservableList<Project> getProjectList() {
        return FXCollections.observableArrayList(this.model.getProjectList());
    }
}
