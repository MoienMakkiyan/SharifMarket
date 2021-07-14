package view.costumer;

import basecode.CostumerMannager;
import basecode.Good;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Main_Costumer;

import java.io.IOException;

public class availabe_goods {
    private TableView table;
    Main_Costumer main_costumer = new Main_Costumer();
    @FXML
    private Label IDshow;
    @FXML
    private Label Moneyshow;
    @FXML
    Label response;
    @FXML
    TextField txtInventory;
    @FXML
    AnchorPane anchorPane;

    public void initialize(){
        IDshow.setText(String.valueOf(CostumerMannager.getInstance().getID()));
        Moneyshow.setText(String.valueOf(CostumerMannager.getInstance().getUsers().get(CostumerMannager.getInstance().back_index_of_user(CostumerMannager.getInstance().getID())).getMoney()));

        table = new TableView();

        TableColumn<Good, String> column1 = new TableColumn<>("Name");
        column1.setMinWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Good, String> column2 = new TableColumn<>("ID");
        column2.setMinWidth(150);
        column2.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn<Good, String> column3 = new TableColumn<>("Inventory");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("current_inventory"));

        TableColumn<Good, String> column4 = new TableColumn<>("Price(IRR)");
        column4.setMinWidth(150);
        column4.setCellValueFactory(new PropertyValueFactory<>("selling_price"));

        TableColumn<Good, String> column5 = new TableColumn<>("Unit");
        column5.setMinWidth(45);
        column5.setCellValueFactory(new PropertyValueFactory<>("MDFR"));

        table.setItems(getAvailableProducts());
        table.getColumns().addAll(column1, column2, column3, column4, column5);
        table.setOpacity(1);
        table.setMaxHeight(325);
        table.setMinHeight(600);
        table.setTranslateX(155);
        table.setTranslateY(0);
        anchorPane.getChildren().add(table);

        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        ObservableList<Good> productsSelected = selectionModel.getSelectedItems();
        productsSelected.addListener(new ListChangeListener<Good>() {
            @Override
            public void onChanged(Change<? extends Good> c) {
                response.setText(productsSelected.get(0).getName());
            }
        });
    }

    public void Refresh(){
        IDshow.setText(String.valueOf(CostumerMannager.getInstance().getID()));
        Moneyshow.setText(String.valueOf(CostumerMannager.getInstance().getUsers().get(CostumerMannager.getInstance().back_index_of_user(CostumerMannager.getInstance().getID())).getMoney()));
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
        CostumerMannager.getInstance().Save();
        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }
    public void unavailable_goods() throws IOException {
        main_costumer.changeScene("costumer/unavailabe_goods.fxml");
    }
    public void all_goods() throws IOException {
        main_costumer.changeScene("costumer/all_goods.fxml");
    }

    public void buy(ActionEvent actionEvent) {
        Good productSelected = (Good) table.getSelectionModel().getSelectedItem();
        if (productSelected == null)
            response.setText("Select sth");
        else {
            response.setText(productSelected.getName());

            if (txtInventory.getText().isEmpty() || Double.parseDouble(txtInventory.getText()) <= 0)
                response.setText("Enter Q.");
            else {
                if(CostumerMannager.getInstance().AddOrder(CostumerMannager.getInstance().getID(),productSelected.getId(),Integer.parseInt(txtInventory.getText()))){
                    response.setText("Registered");
                    CostumerMannager.getInstance().Save();
                    //table.setItems(getAvailableProducts());
                    table.refresh();
                }

                else
                    response.setText("NEQ");
            }
        }
        txtInventory.setText("");
        Moneyshow.setText(String.valueOf(CostumerMannager.getInstance().getUsers().get(CostumerMannager.getInstance().back_index_of_user(CostumerMannager.getInstance().getID())).getMoney()));
    }

    private ObservableList<Good> getAvailableProducts() {
        ObservableList<Good> productsList = FXCollections.observableArrayList();
        for (Good product : CostumerMannager.getInstance().getGoods())
            if(product.getCurrent_inventory()>0) productsList.add(product);
        return productsList;
    }
}
