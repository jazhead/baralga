package de.jazhead.baralgafx.controller.menu;

import de.jazhead.baralgafx.controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import org.remast.baralga.gui.actions.*;
import org.remast.baralga.gui.dialogs.ManageProjectsDialog;

public class MenuController extends AbstractController {

    public MenuItem exportExcel;
    public MenuItem exportICal;
    public MenuItem exportCsv;
    public MenuItem exportDataBackup;
    public MenuItem importDataBackup;
    public MenuItem editProjects;

    @FXML
    void initialize() {

        exportExcel.setOnAction(event -> {
            final ExportExcelAction exportExcelAction = new ExportExcelAction(this.mainController.frame, this.model);
            exportExcelAction.actionPerformed(null);
        });

        exportICal.setOnAction(event -> {
            final ICalExportAction iCalExportAction = new ICalExportAction(this.mainController.frame, this.model);
            iCalExportAction.actionPerformed(null);
        });

        exportCsv.setOnAction(event -> {
            final ExportCsvAction exportCsvAction = new ExportCsvAction(this.mainController.frame, this.model);
            exportCsvAction.actionPerformed(null);
        });

        exportDataBackup.setOnAction(event -> {
            final ExportXmlAction exportXmlAction = new ExportXmlAction(this.mainController.frame, this.model);
            exportXmlAction.actionPerformed(null);
        });

        importDataBackup.setOnAction(event -> {
            final ImportXmlAction importXmlAction = new ImportXmlAction(this.mainController.frame, this.model);
            importXmlAction.actionPerformed(null);
        });

        editProjects.setOnAction(event -> {

            final ManageProjectsDialog manageProjectsDialog = new ManageProjectsDialog(this.mainController.frame, model);
            manageProjectsDialog.setVisible(true);
            this.mainController.projectSelectorController.update();
        });
    }

    @Override
    public void update() {

    }
}
