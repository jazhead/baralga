package de.jazhead.baralgafx.controller;

import de.jazhead.baralgafx.controller.control.ProjectSelector;
import de.jazhead.baralgafx.controller.control.TimerButton;
import de.jazhead.baralgafx.dispatcher.CloseDispatcher;
import de.jazhead.baralgafx.event.CloseEvent;
import de.jazhead.baralgafx.facade.PresentationFacade;
import de.jazhead.baralgafx.listener.CloseListener;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;
import org.remast.baralga.gui.model.PresentationModel;
import org.remast.baralga.gui.model.ProjectActivityStateException;
import org.remast.baralga.gui.panels.ReportPanel;

import javax.swing.*;

public class MainController implements CloseListener {

    private static final Logger LOG = Logger.getLogger(MainController.class);

    private static MainController instance;

    private final PresentationModel model;

    public JFrame frame;

    @FXML
    public TimerButton timerButtonController;

    @FXML
    public ProjectSelector projectSelectorController;

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public SwingNode activityTableNode;

    public MainController() {
        instance = this;
        this.model = PresentationFacade.getInstance().getModel();
        CloseDispatcher.addListener(this);
    }

    @FXML
    void initialize() {

        frame = new JFrame();
        frame.toFront();
        frame.setAlwaysOnTop(true);

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
