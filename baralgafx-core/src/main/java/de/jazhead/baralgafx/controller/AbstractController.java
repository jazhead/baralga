package de.jazhead.baralgafx.controller;

import de.jazhead.baralgafx.facade.PresentationFacade;
import org.remast.baralga.gui.model.PresentationModel;

public abstract class AbstractController {

    protected final PresentationModel model;
    protected final MainController mainController;

    protected AbstractController() {
        this.model = PresentationFacade.getInstance().getModel();
        this.mainController = MainController.getInstance();
    }

    public abstract void update();
}
