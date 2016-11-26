package de.jazhead.baralgafx.controller;

import de.jazhead.baralgafx.facade.PresentationFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.remast.baralga.gui.model.PresentationModel;
import org.remast.baralga.model.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button timerButton;

    @FXML
    private ComboBox<Project> projectSelector;

    @FXML
    private TextArea descriptionTextArea;

    /**
     * The model.
     */
    private PresentationModel model;

    @FXML
    void initialize() {
        this.model = PresentationFacade.getInstance().getModel();

        assert timerButton != null : "fx:id=\"timerButton\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert projectSelector != null : "fx:id=\"taskSelector\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'RootLayout.fxml'.";

        initProjectSelector();
    }

    private void initProjectSelector() {
        final ObservableList<Project> projects = FXCollections.observableArrayList(this.model.getProjectList());
        projectSelector.setItems(projects);

        // TODO: 26.11.2016 tooltip text

        projectSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(String.format("Choose: %s", observable.getValue()));
            MainController.this.model.changeProject(observable.getValue());

            if (descriptionTextArea != null) {
                descriptionTextArea.setText("");
            }
        });
    }
}
