package app2;

import app2.Logged.Login_Scene;
import javafx.application.Application;
import javafx.stage.*;

public class Main_JavaFX extends Application {
	public static String loggedUser_u;
	public static String loggedUser_p;
	public static int loggedUser_t;
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// stage settings
		primaryStage.setTitle("Login Form");
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setScene(Login_Scene.start());
		Utilities.centerStage(primaryStage, 300, 250);
		stage = primaryStage;
		stage.show();
	}

	public static void main() {
		//System.setProperty("glass.accessible.force", "false");
		Application.launch();

	}

}
