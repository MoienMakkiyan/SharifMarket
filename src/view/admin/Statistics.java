package view.admin;

import basecode.AdminMannager;
import basecode.Good;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Main_Admin;

import java.io.IOException;

public class Statistics {
    private TableView table;
    Main_Admin main_admin = new Main_Admin();
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label AllProfit;
    @FXML
    TextField GoodProfit;

    public void initialize(){

        AllProfit.setText("All Profit Until Now :"+AdminMannager.getInstance().AllProfit()+" IRR");

        for (Good good : AdminMannager.getInstance().getGoods())
            good.setProfit();

        table = new TableView();

        TableColumn<Good, String> column1 = new TableColumn<>("Name");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Good, String> column2 = new TableColumn<>("ID");
        column2.setMinWidth(60);
        column2.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Good, String> column3 = new TableColumn<>("Inventory");
        column3.setMinWidth(80);
        column3.setCellValueFactory(new PropertyValueFactory<>("current_inventory"));

        TableColumn<Good, String> column4 = new TableColumn<>("Buying Price");
        column4.setMinWidth(80);
        column4.setCellValueFactory(new PropertyValueFactory<>("buying_price"));

        TableColumn<Good, String> column5 = new TableColumn<>("Selling Price");
        column5.setMinWidth(80);
        column5.setCellValueFactory(new PropertyValueFactory<>("selling_price"));

        TableColumn<Good, String> column6 = new TableColumn<>("All Selling");
        column6.setMinWidth(80);
        column6.setCellValueFactory(new PropertyValueFactory<>("sellingUntilNow"));

        TableColumn<Good, String> column7 = new TableColumn<>("All Buying");
        column7.setMinWidth(80);
        column7.setCellValueFactory(new PropertyValueFactory<>("buyingUntilNow"));

        TableColumn<Good, String> column8 = new TableColumn<>("Profit");
        column8.setMinWidth(80);
        column8.setCellValueFactory(new PropertyValueFactory<>("profit"));


        table.setItems(getAllProducts());
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        table.setOpacity(1);
        table.setMaxHeight(325);
        table.setMinHeight(460);
        table.setTranslateX(132);
        table.setTranslateY(0);
        anchorPane.getChildren().add(table);

        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        ObservableList<Good> productsSelected = selectionModel.getSelectedItems();
        productsSelected.addListener(new ListChangeListener<Good>() {
            @Override
            public void onChanged(Change<? extends Good> c) {
                GoodProfit.setText(productsSelected.get(0).getName()+" has "+(productsSelected.get(0).getSellingUntilNow()-productsSelected.get(0).getBuyingUntilNow())+" Profit Until Now.");
            }
        });
    }

    private ObservableList<Good> getAllProducts() {
        ObservableList<Good> productsList = FXCollections.observableArrayList();
        for (Good product : AdminMannager.getInstance().getGoods())
            productsList.add(product);
        return productsList;
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

    public void All_Goods() throws IOException {
        main_admin.changeScene("admin/All_Goods.fxml");
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
