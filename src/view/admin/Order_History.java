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

public class Order_History {
    private TableView table;
    Main_Admin main_admin = new Main_Admin();
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Label response;

    public void initialize(){

        table = new TableView();

        TableColumn<Order, String> column1 = new TableColumn<>("CostumerID");
        column1.setMinWidth(110);
        column1.setCellValueFactory(new PropertyValueFactory<>("costumerID"));

        TableColumn<Order, String> column2 = new TableColumn<>("OrderID");
        column2.setMinWidth(110);
        column2.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<Order, String> column3 = new TableColumn<>("Date");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("Date"));

        TableColumn<Order, String> column4 = new TableColumn<>("Good ID");
        column4.setMinWidth(110);
        column4.setCellValueFactory(new PropertyValueFactory<>("goodID"));

        TableColumn<Order, String> column5 = new TableColumn<>("Inventory");
        column5.setMinWidth(90);
        column5.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        TableColumn<Order, String> column6 = new TableColumn<>("Checked Out");
        column6.setMinWidth(90);
        column6.setCellValueFactory(new PropertyValueFactory<>("done"));

        table.setItems(getOrders());
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
        table.setOpacity(1);
        table.setMaxHeight(325);
        table.setMinHeight(550);
        table.setTranslateX(132);
        table.setTranslateY(0);
        anchorPane.getChildren().add(table);

        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        ObservableList<Order> OrderSelected = selectionModel.getSelectedItems();
        OrderSelected.addListener(new ListChangeListener<Order>() {
            @Override
            public void onChanged(Change<? extends Order> c) {
                response.setText("Selected OrderID : "+ String.valueOf(OrderSelected.get(0).getOrderID()));
            }
        });
    }

    private ObservableList<Order> getOrders() {
        ObservableList<Order> productsList = FXCollections.observableArrayList();
        for (Order order : AdminMannager.getInstance().getOrders())
            productsList.add(order);
        return productsList;
    }

    public void checkOut(){
        Order selectedOrder = (Order) table.getSelectionModel().getSelectedItem();
        if (selectedOrder == null){
            response.setText("Please Select An Order to CheckOut");
        }
        else {
            if (selectedOrder.isDone()){
                response.setText("This Order has been CheckOut !");
            }
            else {
                AdminMannager.getInstance().CheckOut(selectedOrder.getOrderID());
                response.setText("The Order "+selectedOrder.getOrderID()+" Checked Out.");
                table.refresh();
                AdminMannager.getInstance().Save();
            }
        }
    }

    public void All_Goods() throws IOException {
        main_admin.changeScene("admin/All_Goods.fxml");
    }

    public void Unavailable_Goods() throws IOException {
        main_admin.changeScene("admin/Unavailable_Goods.fxml");
    }

    public void Available_Goods() throws IOException {
        main_admin.changeScene("admin/Available_Goods.fxml");
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
