package app2.Logged;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app2.Database;
import app2.Main_JavaFX;
import app2.Utilities;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Login_Scene {
	/*---- Variable Declarations----*/
	@SuppressWarnings("rawtypes")
	private static ChoiceBox jobBox;
	private static GridPane lgrid, rgrid;
	private static Label userLabel, passLabel, rpassLabel, ruserLabel, rrpassLabel;
	private static TextField userField, ruserField;
	private static PasswordField passField, rpassField, rrpassField;
	private static Button loginBtn, registerBtn, rFormBtn, backBtn;
	private static int width = 300;
	private static int height = 250;

	/*-----Login Scene-----*/
	public static Scene start() {
		// grid settings
		lgrid = new GridPane();
		lgrid.setAlignment(Pos.CENTER);
		lgrid.setHgap(10);
		lgrid.setVgap(10);
		lgrid.setPadding(new Insets(25, 25, 25, 25));
		// grid components
		userLabel = new Label("Username");
		passLabel = new Label("Password");
		userField = new TextField();
		passField = new PasswordField();
		loginBtn = new Button("Login");
		registerBtn = new Button("Register");

		lgrid.add(userLabel, 0, 1);
		lgrid.add(userField, 1, 1);
		lgrid.add(passLabel, 0, 2);
		lgrid.add(passField, 1, 2);
		lgrid.add(loginBtn, 1, 3);
		lgrid.add(registerBtn, 1, 4);

		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String user = userField.getText();
				String pass = passField.getText();
				if (!Utilities.checkLogin(user, pass)){
					JOptionPane optionPane = new JOptionPane("Password or Username are wrong");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else {
					Main_JavaFX.loggedUser_u = user;
					Main_JavaFX.loggedUser_p = pass;
					Main_JavaFX.loggedUser_t = Database.getType(user);

					userField.setText("");
					passField.setText("");
					if (Main_JavaFX.loggedUser_t == 0)
						Main_JavaFX.stage.setScene(UserLoggedScene.start());
					else
						Main_JavaFX.stage.setScene(AdminLoggedScene.start());
				}
			}
		});
		registerBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Main_JavaFX.centerStage(Main_JavaFX.stage,width,height);
				Main_JavaFX.stage.setScene(startRegister());
			}
		});
		return new Scene(lgrid, width, height);

	}

	/*-----Register Scene -------*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Scene startRegister() {
		Utilities.centerStage(Main_JavaFX.stage, width, height);

		// grid settings
		rgrid = new GridPane();
		rgrid.setAlignment(Pos.CENTER);
		rgrid.setHgap(10);
		rgrid.setVgap(10);
		rgrid.setPadding(new Insets(25, 25, 25, 25));

		// grid components
		ruserLabel = new Label("Username");
		rpassLabel = new Label("Password");
		rrpassLabel = new Label("Retype password");
		ruserField = new TextField();
		rpassField = new PasswordField();
		rrpassField = new PasswordField();
		rFormBtn = new Button("Register");
		backBtn = new Button("Back");

		jobBox = new ChoiceBox(FXCollections.observableArrayList("User", "Administrator"));

		rgrid.add(ruserLabel, 0, 1);
		rgrid.add(ruserField, 1, 1);
		rgrid.add(rpassLabel, 0, 2);
		rgrid.add(rpassField, 1, 2);
		rgrid.add(rrpassLabel, 0, 3);
		rgrid.add(rrpassField, 1, 3);
		rgrid.add(jobBox, 1, 4);
		rgrid.add(rFormBtn, 1, 5);
		rgrid.add(backBtn, 1, 6);

		rFormBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String u = ruserField.getText();
				String p = rpassField.getText();
				String rp = rrpassField.getText();
				int type = jobBox.getSelectionModel().getSelectedIndex();
				if (u.equals("") || p.equals("") || rp.equals("") || type < 0 || type > 1)
					JOptionPane.showMessageDialog(null, "Completati tot.");
				else if (!Utilities.checkPass(p)){
					JOptionPane optionPane = new JOptionPane("Parola trebuie sa fie de minim 5 caractere , litere si cifre.");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else if (!rp.equals(p)) {
					JOptionPane optionPane = new JOptionPane("Parolele nu coicid");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					
					rpassField.setText("");
					rrpassField.setText("");

				} else {
					if (Database.checkIfExist(u)){
						JOptionPane optionPane = new JOptionPane("Acest user exista deja.");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					else {
						Database.insertUser(u, p, Integer.toString(type));

						JOptionPane optionPane = new JOptionPane("Inregistrare efectuata cu succes.");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
						
						Main_JavaFX.stage.setScene(start());
					}
				}
			}
		});
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(start());
			}
		});

		return new Scene(rgrid, width, height);
	}

}
