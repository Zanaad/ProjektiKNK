package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.dto.LoginUserDto;
import service.Alerts;
import service.Staff.AdminService;
import service.Staff.DoctorService;
import service.Staff.NurseService;
import service.Staff.ReceptionistService;

public class LoginController {

    @FXML
    private AnchorPane login_admin;

    @FXML
    private TextField login_adminEmail;

    @FXML
    private PasswordField login_adminPwd;

    @FXML
    private TextField login_docEmail;

    @FXML
    private PasswordField login_docPassword;

    @FXML
    private AnchorPane login_doctor;

    @FXML
    private AnchorPane login_nurse;

    @FXML
    private TextField login_nurseEmail;

    @FXML
    private PasswordField login_nursePwd;

    @FXML
    private TextField login_recEmail;

    @FXML
    private PasswordField login_recPwd;

    @FXML
    private AnchorPane login_receptionist;
    @FXML
    private ComboBox<?> login_user;

    @FXML
    private ComboBox<?> login_user1;

    @FXML
    private ComboBox<?> login_user2;

    @FXML
    private ComboBox<?> login_user3;

    @FXML
    void loginAdmin(ActionEvent event) {
        LoginUserDto loginData = new LoginUserDto(this.login_adminEmail.getText(), this.login_adminPwd.getText());
        boolean isLogin = AdminService.login(loginData);
        if (isLogin) {
            Navigator.navigate(event, Navigator.AdminMainForm);
        }

    }

    @FXML
    void loginDoctor(ActionEvent event) {
        LoginUserDto loginData = new LoginUserDto(this.login_docEmail.getText(), this.login_docPassword.getText());
        boolean isLogin = DoctorService.login(loginData);
        if (isLogin) {
            Navigator.navigate(event, Navigator.Doctor_App);
        }
    }

    @FXML
    void loginNurse(ActionEvent event) {
        LoginUserDto loginData = new LoginUserDto(this.login_nurseEmail.getText(), this.login_nursePwd.getText());
        boolean isLogin = NurseService.login(loginData);
        if (isLogin) {
            Navigator.navigate(event, Navigator.NursePage);
        } else {
            Alerts.errorMessage("error");
        }
    }

    @FXML
    void loginRec(ActionEvent event) {
        LoginUserDto loginData = new LoginUserDto(this.login_recEmail.getText(), this.login_recPwd.getText());
        boolean isLogin = ReceptionistService.login(loginData);
        if (isLogin) {
            Navigator.navigate(event, Navigator.ReceptionistPage);
        }
    }

    @FXML
    void loginShowPassword(ActionEvent event) {

    }

    @FXML
    void switchAdminLogin(ActionEvent event) {
        switchComboBoxSelection(login_user);
    }

    @FXML
    void switchDocLogin(ActionEvent event) {
        switchComboBoxSelection(login_user1);
    }

    @FXML
    void switchNurseLogin(ActionEvent event) {
        switchComboBoxSelection(login_user2);
    }

    @FXML
    void switchRecLogin(ActionEvent event) {
        switchComboBoxSelection(login_user3);
    }

    private void showForm(AnchorPane form) {
        login_admin.setVisible(form == login_admin);
        login_doctor.setVisible(form == login_doctor);
        login_nurse.setVisible(form == login_nurse);
        login_receptionist.setVisible(form == login_receptionist);
    }

    private void switchComboBoxSelection(ComboBox<?> combo) {
        Object selectedItem = combo.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("Admin")) {
            showForm(login_admin);
        } else if (selectedItem.equals("Doctor")) {
            showForm(login_doctor);
        } else if (selectedItem.equals("Nurse")) {
            showForm(login_nurse);
        } else if (selectedItem.equals("Receptionist")) {
            showForm(login_receptionist);
        }
    }
}
