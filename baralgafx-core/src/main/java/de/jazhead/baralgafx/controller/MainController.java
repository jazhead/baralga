package de.jazhead.baralgafx.controller;

import de.jazhead.baralgafx.controller.control.ProjectSelector;
import de.jazhead.baralgafx.controller.control.TimerButton;
import de.jazhead.baralgafx.dispatcher.CloseDispatcher;
import de.jazhead.baralgafx.event.CloseEvent;
import de.jazhead.baralgafx.facade.PresentationFacade;
import de.jazhead.baralgafx.listener.CloseListener;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;
import org.remast.baralga.gui.dialogs.ManageProjectsDialog;
import org.remast.baralga.gui.model.PresentationModel;
import org.remast.baralga.gui.model.ProjectActivityStateException;
import org.remast.baralga.gui.panels.ReportPanel;

import javax.swing.*;

public class MainController implements CloseListener {

    private static final Logger LOG = Logger.getLogger(MainController.class);

    private static MainController instance;

    protected final PresentationModel model;

    public JFrame frame;

    @FXML
    public TimerButton timerButtonController;

    @FXML
    public ProjectSelector projectSelectorController;

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public MenuItem editProjects;

    @FXML
    public SwingNode activityTableNode;

    public MainController() {
        instance = this;
        this.model = PresentationFacade.getInstance().getModel();
        CloseDispatcher.addListener(this);
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
            projectSelectorController.update();
            // TODO: 26.11.2016 update projects
        });

        activityTableNode.setContent(new ReportPanel(this.model));

    }

    public static MainController getInstance() {
        return instance;
    }

    @Override
    public void onClose(final CloseEvent event) {

        try {
            if (model.isActive()) {
                model.stop();
            }
        } catch (final ProjectActivityStateException e) {
            LOG.warn("Failure while close application", e);
        }
        frame.dispose();
    }
}
