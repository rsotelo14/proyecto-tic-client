package uy.um.edu.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uy.um.edu.client.ClientApplication;

import java.security.Principal;

public class JavaFXApplication extends Application  {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        JavaFXApplication.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("usuario/LoginUser.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("usuario/style/appStyle.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("usuario/style/loginStyle.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("usuario/style/adminStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        Font.loadFont(getClass().getResourceAsStream("usuario/fonts/OpenSans-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("usuario/fonts/OpenSans-Regular.ttf"), 30);
        Font.loadFont(getClass().getResourceAsStream("usuario/fonts/OpenSans-Semibold.ttf"), 14);
    }

    @Override
    public void stop() {
        ClientApplication.getContext().close();
    }

}
