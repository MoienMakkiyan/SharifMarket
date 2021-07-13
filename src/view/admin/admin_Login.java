package view.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import view.Main_Admin;

import java.awt.*;
import java.io.IOException;

public class admin_Login {
    @FXML
    Label error_box;
    @FXML
    Label error1_box;
    @FXML
    PasswordField passwordField;

    Main_Admin main_admin = new Main_Admin();

    public void Forget_Pass(){
        error_box.setText("The Password is : I Love OOP");
    }

    public void Login() throws IOException {
        error_box.setText("");
        if (passwordField.getText().isEmpty()) error_box.setText("Please Enter the Password.");
        else {
            if (passwordField.getText().equalsIgnoreCase("I Love OOP")){
                main_admin.changeScene("admin/admin_Enter.fxml");
            }
            else error_box.setText("Invalid Password, Please try again...");
        }
    }

    public void force_Exit(){
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Exit");
        aLert.setHeaderText("You're about to exit the program");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }
}
