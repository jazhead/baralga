package de.jazhead.baralgafx.controller.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.log4j.Logger;
import org.remast.baralga.gui.model.ProjectActivityStateException;

public class TimerButton extends AbstractControl {

    private static final Logger LOG = Logger.getLogger(TimerButton.class);

    @FXML
    public Button startButton;

    @FXML
    public Button stopButton;

    @FXML
    void initialize() {

        initTimerButton();
    }

    private void initTimerButton() {

        startButton.toFront();

        startButton.setOnAction(event -> updateButton());
        stopButton.setOnAction(event -> updateButton());
    }

    private void updateButton() {

        if (model.getSelectedProject() != null) {
            try {
                updateTimer();
            } catch (final ProjectActivityStateException ex) {
                ex.printStackTrace();
            }
        } else {
            LOG.warn("No project is selected");
        }
    }

    private void updateTimer() throws ProjectActivityStateException {

        if (this.model.isActive()) {
            this.mainController.projectSelectorController.projectSelector.setDisable(false);
            this.mainController.descriptionTextArea.setText("");
            model.stop();
            startButton.toFront();
        } else {
            this.mainController.projectSelectorController.projectSelector.setDisable(true);
            model.start();
            stopButton.toFront();
        }
    }

    @Override
    public void update() {

    }
}
