package view.costumer;

import basecode.CostumerMannager;
import basecode.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.Main_Costumer;

import java.io.IOException;

public class customer_signin {

    Main_Costumer main_costumer = new Main_Costumer();

    @FXML
    private Label SignUp_User_Error;
    @FXML
    private Label SignUp_Pass_Error;
    @FXML
    private TextField Signup_username;
    @FXML
    private PasswordField Signup_password;

    private boolean accept_user = false;

    public void check_user_exist(){
        SignUp_User_Error.setText("");
        SignUp_Pass_Error.setText("");

        System.out.println(Signup_username.getText());
        if (Signup_username.getText().isEmpty()){
            SignUp_User_Error.setText("Please Enter Your ID.");
        }
        else {
            if (!Search(Signup_username.getText())){
                SignUp_User_Error.setText("Accepted.");
                accept_user = true;
            }
            else {
                SignUp_User_Error.setText("This Username already Exists.");
                accept_user = false;
            }
        }
    }

    private boolean Search(String n){
        boolean f = false;
        for (User user : CostumerMannager.getInstance().getUsers()){
            if (user.getId().equals(n)) f = true;
        }
        return f;
    }

    public void Register() throws IOException {
        if (accept_user){
            CostumerMannager.getInstance().LoadPass();
            CostumerMannager.getInstance().SignUp(Signup_username.getText(),Signup_password.getText());
            SignUp_Pass_Error.setText("Your Account Created.");
            CostumerMannager.getInstance().SaveUser();
            main_costumer.changeScene("costumer/costumer_Login.fxml");
        }
        else SignUp_Pass_Error.setText("Please Check Username.");
    }

    public void LogOut() throws IOException {
        main_costumer.changeScene("costumer/costumer_Login.fxml");
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
