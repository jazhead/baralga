package de.jazhead.baralgafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.remast.baralga.gui.model.PresentationModel;
import org.remast.baralga.model.BaralgaDAO;

import java.sql.SQLException;

/**
 * Start class for BaralgaFX
 */
public class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final PresentationModel model = initModel();
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/RootLayout.fxml"));
        final Scene scene = new Scene(root);
        stage.setTitle("BaralgaFX");
        stage.setScene(scene);
        stage.show();
    }

    private static PresentationModel initModel() throws SQLException {
//        log.debug("Initializing model..."); //$NON-NLS-1$

        // Initialize with new site
        final PresentationModel model = new PresentationModel();

        final BaralgaDAO dao = new BaralgaDAO();
        dao.init();

        model.setDAO(dao);
        model.initialize();

        return model;
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
