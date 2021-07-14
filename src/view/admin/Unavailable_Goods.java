package view.admin;

import basecode.AdminMannager;
import basecode.Good;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Main_Admin;

import java.io.IOException;

public class Unavailable_Goods {
    private TableView table;
    Main_Admin main_admin = new Main_Admin();
    @FXML
    AnchorPane anchorPane;

    public void initialize(){

        table = new TableView();

        TableColumn<Good, String> column1 = new TableColumn<>("Name");
        column1.setMinWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Good, String> column2 = new TableColumn<>("ID");
        column2.setMinWidth(130);
        column2.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Good, String> column3 = new TableColumn<>("Inventory");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("current_inventory"));

        TableColumn<Good, String> column4 = new TableColumn<>("Price(IRR)");
        column4.setMinWidth(150);
        column4.setCellValueFactory(new PropertyValueFactory<>("selling_price"));

        TableColumn<Good, String> column5 = new TableColumn<>("Unit");
        column5.setMinWidth(50);
        column5.setCellValueFactory(new PropertyValueFactory<>("MDFR"));

        table.setItems(getUnavailableProducts());
        table.getColumns().addAll(column1, column2, column3, column4, column5);
        table.setOpacity(1);
        table.setMaxHeight(325);
        table.setMinHeight(550);
        table.setTranslateX(132);
        table.setTranslateY(0);
        anchorPane.getChildren().add(table);

        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        ObservableList<Good> productsSelected = selectionModel.getSelectedItems();
        productsSelected.addListener(new ListChangeListener<Good>() {
            @Override
            public void onChanged(Change<? extends Good> c) {
                //response.setText(productsSelected.get(0).getName());
            }
        });
    }

    private ObservableList<Good> getUnavailableProducts() {
        ObservableList<Good> productsList = FXCollections.observableArrayList();
        for (Good product : AdminMannager.getInstance().getGoods())
            if(product.getCurrent_inventory()==0) productsList.add(product);
        return productsList;
    }

    public void All_Goods() throws IOException {
        main_admin.changeScene("admin/All_Goods.fxml");
    }

    public void Available_Goods() throws IOException {
        main_admin.changeScene("admin/Available_Goods.fxml");
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
