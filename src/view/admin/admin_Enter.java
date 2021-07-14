package view.admin;

import basecode.AdminMannager;
import basecode.CostumerMannager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.Main_Admin;

import java.io.IOException;

public class admin_Enter {

    Main_Admin main_admin = new Main_Admin();

    public void initialize(){
        AdminMannager.getInstance().Load();
    }

    public void All_Goods() throws IOException {
        main_admin.changeScene("admin/All_Goods.fxml");
    }

    public void Available_Goods() throws IOException {
        main_admin.changeScene("admin/Available_Goods.fxml");
    }

    public void Unavailable_Goods() throws IOException {
        main_admin.changeScene("admin/Unavailable_Goods.fxml");
    }

    public void Order_History() throws IOException {
        main_admin.changeScene("admin/Order_History.fxml");
    }

    public void Statistics() throws IOException {
        main_admin.changeScene("admin/Statistics.fxml");
    }

    public void LogOut() throws IOException {
        main_admin.changeScene("admin/admin_Login.fxml");
    }

    public void force_Exit(){
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Exit");
        aLert.setHeaderText("You're about to exit the program");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        AdminMannager.getInstance().Save();
        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }
}
