package de.jazhead.baralgafx.controller.control;

import de.jazhead.baralgafx.controller.AbstractController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.remast.baralga.model.Project;

public class ProjectSelector extends AbstractController {

    @FXML
    public ComboBox<Project> projectSelector;

    @FXML
    void initialize() {

        this.update();
        this.initProjectSelector();
    }

    private void initProjectSelector() {
        projectSelector.getSelectionModel().select(this.model.getSelectedProject());

        projectSelector.setOnAction(event -> {
            model.changeProject(projectSelector.getSelectionModel().getSelectedItem());
            mainController.descriptionTextArea.setText("");
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
