package de.jazhead.baralgafx.controller.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.remast.baralga.gui.model.ProjectActivityStateException;

public class TimerButton extends AbstractControl {

    private final Image ICON_START_ENABLED = new Image(getClass().getResourceAsStream("/icons/Play-Hot-icon.png"));

    private final Image ICON_START_DISABLED = new Image(getClass().getResourceAsStream("/icons/Play-Disabled-icon.png"));

    private final Image ICON_STOP_ENABLED = new Image(getClass().getResourceAsStream("/icons/Stop-Normal-Blue-icon.png"));

    private final Image ICON_STOP_DISABLED = new Image(getClass().getResourceAsStream("/icons/Stop-Disabled-icon.png"));

    @FXML
    public Button timerButton;

    @FXML
    void initialize() {

        assert timerButton != null : "fx:id=\"timerButton\" was not injected: check your FXML file 'RootLayout.fxml'.";
        initTimerButton();
    }

    private void initTimerButton() {

        if (this.model.isActive()) {
            timerButton.setOnAction(event -> {
                try {
                    model.stop();
                    initTimerButton();
                } catch (final ProjectActivityStateException e) {
                    e.printStackTrace();
                }
            });
        } else {
            timerButton.setOnAction(event -> {
                try {
                    model.start();
                    initTimerButton();
                } catch (final ProjectActivityStateException e) {
                    e.printStackTrace();
                }
            });
        }
        timerButton.setText("");
        timerButton.setGraphic(new ImageView(ICON_START_DISABLED));
    }

    @Override
    public void update() {

    }
}
