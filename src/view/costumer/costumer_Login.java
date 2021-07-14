package view.costumer;

import basecode.CostumerMannager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.Main_Costumer;

import java.io.IOException;

public class costumer_Login {
    @FXML
    private Label Login_Eror;
    @FXML
    private TextField Login_username;
    @FXML
    private PasswordField Login_password;

    Main_Costumer main_costumer = new Main_Costumer();

    public void checkLogin() throws IOException {
        CostumerMannager.getInstance().LoadPass();
        if (Login_username.getText().isEmpty()&&Login_password.getText().isEmpty())
            Login_Eror.setText("Please Enter Your Data.");
        else {
            if (CostumerMannager.getInstance().SignIn(Login_username.getText(),Login_password.getText())){
                Login_Eror.setText("Success.");
                CostumerMannager.getInstance().setID(Integer.parseInt(Login_username.getText()));
                main_costumer.changeScene("costumer/Entered_user.fxml");
            }
            else {
                Login_Eror.setText("Wrong Username or Password.");
            }
        }
        CostumerMannager.getInstance().SaveUser();
    }

    public void force_Exit(){
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Exit");
        aLert.setHeaderText("You're about to exit the program");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        CostumerMannager.getInstance().Save();
        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }

    public void signup() throws IOException {
        main_costumer.changeScene("costumer/customer_signin.fxml");
    }
}
