package de.jazhead.baralgafx.controller.control;

import de.jazhead.baralgafx.controller.MainController;
import de.jazhead.baralgafx.facade.PresentationFacade;
import org.remast.baralga.gui.model.PresentationModel;

abstract class AbstractControl {

    protected final PresentationModel model;
    protected final MainController mainController;

    AbstractControl() {
        this.model = PresentationFacade.getInstance().getModel();
        this.mainController = MainController.getInstance();
    }

    public abstract void update();
}
