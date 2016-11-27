package de.jazhead.baralgafx.facade;

import org.remast.baralga.gui.model.PresentationModel;
import org.remast.baralga.model.BaralgaDAO;

import java.sql.SQLException;

public class PresentationFacade {

    private static PresentationFacade instance;

    private PresentationModel model;

    private PresentationFacade() {

        try {
            this.model = initModel();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public static PresentationFacade getInstance() {

        if (instance == null) {

            instance = new PresentationFacade();
        }

        return instance;
    }

    private static PresentationModel initModel() throws SQLException {

        final PresentationModel model = new PresentationModel();

        final BaralgaDAO dao = new BaralgaDAO();
        dao.init();

        model.setDAO(dao);
        model.initialize();

        return model;
    }

    public PresentationModel getModel() {
        return model;
    }
}
