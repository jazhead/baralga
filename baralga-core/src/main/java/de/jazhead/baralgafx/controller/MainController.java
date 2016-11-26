package de.jazhead.baralgafx.controller;

import de.jazhead.baralgafx.controller.control.ProjectSelector;
import de.jazhead.baralgafx.controller.control.TimerButton;
import de.jazhead.baralgafx.facade.PresentationFacade;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import org.remast.baralga.gui.dialogs.ManageProjectsDialog;
import org.remast.baralga.gui.model.PresentationModel;

import javax.swing.*;

public class MainController {
    protected final PresentationModel model;

    private static MainController instance;

    public JFrame frame;

    @FXML
    public TimerButton timerButtonController;

    @FXML
    public ProjectSelector projectSelectorController;

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public MenuItem editProjects;

    public MainController() {
        instance = this;
        this.model = PresentationFacade.getInstance().getModel();
    }

    @FXML
    void initialize() {

        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'RootLayout.fxml'.";

        frame = new JFrame();
        editProjects.setOnAction(event -> {
            frame.toFront();
            frame.setAlwaysOnTop(true);

            final ManageProjectsDialog manageProjectsDialog = new ManageProjectsDialog(frame, model);
            manageProjectsDialog.setVisible(true);

            // TODO: 26.11.2016 update projects
        });

    }

    public static MainController getInstance() {
        return instance;
    }
}
