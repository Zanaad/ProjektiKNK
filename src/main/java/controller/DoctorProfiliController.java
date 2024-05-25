package controller;

import app.Navigator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.dto.DoktorDto;
import repository.Doc.Doktor;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorProfiliController implements Initializable {

    @FXML
    private Label labelAdresa;

    @FXML
    private Label labelEmri;

    @FXML
    private Label labelID;

    @FXML
    private Label labelMbiemri;

    @FXML
    private Label labelNrTel;

    @FXML
    private Label labelSpecializimi;

    @FXML
    private TextArea txtAdresa;

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtNrTel;

    @FXML
    private TextField txtSpecializimi;

    @FXML
    void handleClickApp(MouseEvent event) {
        Navigator.navigate(event, Navigator.Doctor_App);
    }

    @FXML
    void handleMenaxho(MouseEvent event) {
        Navigator.navigate(event, Navigator.Doctor_Menaxho);
    }

    @FXML
    void handleRegjistro(MouseEvent event) {
        Navigator.navigate(event, Navigator.Doctor_Shto);
    }

    @FXML
    void handleUpdate(MouseEvent event) {
        Doktor doktorRepository = new Doktor();
        doktorRepository.updateDoctor(Integer.parseInt(txtID.getText()), txtEmri.getText(), txtMbiemri.getText(), txtAdresa.getText(), Integer.parseInt(txtNrTel.getText()), txtSpecializimi.getText());
    }

    public void initialize(URL url, ResourceBundle rb) {
        Doktor doktorRepository = new Doktor();
        DoktorDto doctor = doktorRepository.getDoctorData(1);

        if (doctor != null) {
            txtID.setText(String.valueOf(doctor.getID()));
            txtEmri.setText(doctor.getEmri());
            txtMbiemri.setText(doctor.getMbiemri());
            txtNrTel.setText(String.valueOf(doctor.getNrTel()));
            txtAdresa.setText(doctor.getAdresa());
            txtSpecializimi.setText(doctor.getSpecializimi());

            txtNrTel.textProperty().addListener((observable, oldValue, newValue) -> labelNrTel.setText(newValue));
            txtAdresa.textProperty().addListener((observable, oldValue, newValue) -> labelAdresa.setText(newValue));
            txtEmri.textProperty().addListener((observable, oldValue, newValue) -> labelEmri.setText(newValue));
            txtID.textProperty().addListener((observable, oldValue, newValue) -> labelID.setText(newValue));
            txtMbiemri.textProperty().addListener((observable, oldValue, newValue) -> labelMbiemri.setText(newValue));
            txtSpecializimi.textProperty().addListener((observable, oldValue, newValue) -> labelSpecializimi.setText(newValue));
        }
        updateLabels(doctor);
    }

    private void updateLabels(DoktorDto doctor) {
        if (doctor != null) {
            labelID.setText(String.valueOf(doctor.getID()));
            labelEmri.setText(doctor.getEmri());
            labelMbiemri.setText(doctor.getMbiemri());
            labelAdresa.setText(doctor.getAdresa());
            labelNrTel.setText(String.valueOf(doctor.getNrTel()));
            labelSpecializimi.setText(doctor.getSpecializimi());
        }
    }
}