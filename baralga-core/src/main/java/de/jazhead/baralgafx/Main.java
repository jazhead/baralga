package de.jazhead.baralgafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start class for BaralgaFX
 */
public class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/RootLayout.fxml"));
        final Scene scene = new Scene(root);
        stage.setTitle("BaralgaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
