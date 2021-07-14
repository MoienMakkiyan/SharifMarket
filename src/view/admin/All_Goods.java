package view.admin;

import basecode.AdminMannager;
import basecode.Good;
import basecode.Order;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Main_Admin;

import java.io.IOException;

public class All_Goods {
    private TableView table;
    Main_Admin main_admin = new Main_Admin();
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label response;
    @FXML
    TextField GoodName;
    @FXML
    TextField GoodInventory;
    @FXML
    TextField BuyingPrice;
    @FXML
    TextField SellingPrice;

    public void initialize(){

        table = new TableView();

        TableColumn<Good, String> column1 = new TableColumn<>("Name");
        column1.setMinWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Good, String> column2 = new TableColumn<>("ID");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Good, String> column3 = new TableColumn<>("Inventory");
        column3.setMinWidth(100);
        column3.setCellValueFactory(new PropertyValueFactory<>("current_inventory"));

        TableColumn<Good, String> column4 = new TableColumn<>("Buying Price");
        column4.setMinWidth(120);
        column4.setCellValueFactory(new PropertyValueFactory<>("buying_price"));

        TableColumn<Good, String> column5 = new TableColumn<>("Selling Price");
        column5.setMinWidth(120);
        column5.setCellValueFactory(new PropertyValueFactory<>("selling_price"));

        TableColumn<Good, String> column6 = new TableColumn<>("Unit");
        column6.setMinWidth(80);
        column6.setCellValueFactory(new PropertyValueFactory<>("MDFR"));

        table.setItems(getAvailableProducts());
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
        table.setOpacity(1);
        table.setMaxHeight(325);
        table.setMinHeight(500);
        table.setTranslateX(131);
        table.setTranslateY(0);
        anchorPane.getChildren().add(table);

        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        ObservableList<Good> productsSelected = selectionModel.getSelectedItems();
        productsSelected.addListener(new ListChangeListener<Good>() {
            @Override
            public void onChanged(Change<? extends Good> c) {
                GoodName.setText(productsSelected.get(0).getName());
                GoodInventory.setText(String.valueOf(productsSelected.get(0).getCurrent_inventory()));
                BuyingPrice.setText(String.valueOf(productsSelected.get(0).getBuying_price()));
                SellingPrice.setText(String.valueOf(productsSelected.get(0).getSelling_price()));
                response.setText("");
            }
        });
    }

    private ObservableList<Good> getAvailableProducts() {
        ObservableList<Good> productsList = FXCollections.observableArrayList();
        for (Good product : AdminMannager.getInstance().getGoods())
            productsList.add(product);
        return productsList;
    }

    public void Edit(){
        Good selectedGood = (Good) table.getSelectionModel().getSelectedItem();
        if (GoodName.getText().isEmpty()) response.setText("Please Choose a Good");
        else {
            AdminMannager.getInstance().EditNameGood(selectedGood.getId(),GoodName.getText());
            AdminMannager.getInstance().EditNameCountGood(selectedGood.getId(),GoodName.getText(),Integer.parseInt(GoodInventory.getText()),selectedGood.getMDFR());
            AdminMannager.getInstance().EditSellPriceCountGood(selectedGood.getId(),Integer.parseInt(SellingPrice.getText()),Integer.parseInt(BuyingPrice.getText()),Integer.parseInt(GoodInventory.getText()),selectedGood.getMDFR());
            table.refresh();
            AdminMannager.getInstance().Save();
            response.setText("The Selected Item Edited Successfully!");
        }
    }

    public void Delete(){
        Good selectedGood = (Good) table.getSelectionModel().getSelectedItem();
        if (GoodName.getText().isEmpty()) response.setText("Please Choose a Good");
        else {
            AdminMannager.getInstance().RemoveGood(selectedGood.getId());
            response.setText("The Selected Item Deleted Successfully!");
            AdminMannager.getInstance().Save();
            table.refresh();
            table.setItems(getAvailableProducts());
        }
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
