package view;

import basecode.AdminMannager;
import basecode.CostumerMannager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Admin extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        Image icon = new Image(getClass().getResourceAsStream("/images/admin_icon.png"));
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("admin/admin_Login.fxml"));
        primaryStage.setTitle("Sharif Market - Admin");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = null;
        pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void exit(Stage stage) throws IOException {
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
