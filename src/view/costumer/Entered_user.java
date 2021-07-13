package view.costumer;

import basecode.CostumerMannager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import view.Main_Costumer;

import java.io.IOException;

public class Entered_user {

    @FXML
    private Label IDshow;
    @FXML
    private Label Moneyshow;

    Main_Costumer main_costumer = new Main_Costumer();

    public void initialize(){
        CostumerMannager.getInstance().Load();
        IDshow.setText(String.valueOf(CostumerMannager.getInstance().getID()));
        Moneyshow.setText(String.valueOf(CostumerMannager.getInstance().getUsers().get(CostumerMannager.getInstance().back_index_of_user(CostumerMannager.getInstance().getID())).getMoney()));
    }

    public void Refresh(){
        IDshow.setText(String.valueOf(CostumerMannager.getInstance().getID()));
        Moneyshow.setText(String.valueOf(CostumerMannager.getInstance().getUsers().get(CostumerMannager.getInstance().back_index_of_user(CostumerMannager.getInstance().getID())).getMoney()));
    }

    public void LogOut() throws IOException {
        main_costumer.changeScene("costumer/costumer_Login.fxml");
    }

    public void Availabe_Goods() throws IOException {
        main_costumer.changeScene("costumer/availabe_goods.fxml");
    }

    public void Unavailabe_Goods() throws IOException {
        main_costumer.changeScene("costumer/unavailabe_goods.fxml");
    }

    public void All_Goods() throws IOException {
        main_costumer.changeScene("costumer/all_goods.fxml");
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
