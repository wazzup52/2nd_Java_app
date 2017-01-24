package app2.Logged;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app2.Database;
import app2.Main_JavaFX;
import app2.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class UserLoggedScene {
	/*-----Variable Declarations-----*/
	private static Label cpassLabel1, cpassLabel2, cpassLabel3;
	private static TextField cpassField1, cpassField2, cpassField3;
	private static Button logoutBtn, cbackBtn, cpassBtn, cpassFormBtn, qFormBtn, submitBtn, ubackBtn;;
	private static GridPane cpassgrid, normalUgrid, qUForm;
	private static int width = 300, height = 250;

	/*-----User logged on Scene------*/
	public static Scene start() {
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		normalUgrid = new GridPane();
		/*----------Normal User Controls---------*/
		{
			normalUgrid.setAlignment(null);
			normalUgrid.setHgap(10);
			normalUgrid.setVgap(10);
			normalUgrid.setPadding(new Insets(25, 25, 25, 25));
			cpassBtn = new Button("Schimbare parola");
			logoutBtn = new Button("Logout");
			normalUgrid.add(cpassBtn, 1, 2);
			normalUgrid.add(logoutBtn, 1, 12);
			qFormBtn = new Button("Completati chestionar");
			normalUgrid.add(qFormBtn, 1, 4);

		}
		/*--------Buttons functions---------*/

		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.centerOnScreen();
				Main_JavaFX.stage.setScene(Login_Scene.start());
			}
		});
		cpassBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(changePassScene());
			}
		});
		qFormBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(qFormScene());
			}
		});
		return new Scene(normalUgrid, width, height);
	}

	/*-----qForm Scene-------*/
	public static Scene qFormScene() {
		int width = 800, height = 600;
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		qUForm = new GridPane();
		qUForm.setAlignment(null);
		qUForm.setHgap(10);
		qUForm.setVgap(10);
		qUForm.setPadding(new Insets(25, 25, 25, 25));
		/*----Questions---*/
		Label q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12;
		q1 = new Label("1. Nume Student : ");
		q2 = new Label("2. Adresa de email : ");
		q3 = new Label("3. Numar de telefon : ");
		q4 = new Label("4. Facultatea la care este student sau pe care a absolvit-o : ");
		q5 = new Label("5. Specializarea : ");
		q6 = new Label("6. Situatia pe piata fortei de munca : ");
		q7 = new Label("7. Denumirea postului ocupat : ");
		q8 = new Label("8. Tipul postului ocupat : ");
		q9 = new Label("9. Numele companiei unde lucreaza : ");
		q10 = new Label("10. Orasul in care lucreaza : ");
		q11 = new Label("11. Situatia scolara actuala : ");
		q12 = new Label("12. Unde ar dori sa lucreze : ");
		qUForm.add(q1, 0, 1);
		qUForm.add(q2, 0, 3);
		qUForm.add(q3, 0, 5);
		qUForm.add(q4, 0, 7);
		qUForm.add(q5, 0, 9);
		qUForm.add(q6, 0, 11);
		qUForm.add(q7, 0, 13);
		qUForm.add(q8, 0, 15);
		qUForm.add(q9, 0, 17);
		qUForm.add(q10, 0, 19);
		qUForm.add(q11, 0, 21);
		qUForm.add(q12, 0, 23);

		/*----Answers----*/
		TextField iq1, iq2, iq3, iq7, iq9, iq10;
		ChoiceBox<String> iq4, iq5, iq6, iq8, iq11, iq12;

		iq1 = new TextField();
		iq2 = new TextField();
		iq3 = new TextField();
		iq7 = new TextField();
		iq9 = new TextField();
		iq10 = new TextField();
		iq4 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("college")));
		iq5 = new ChoiceBox<String>(FXCollections.observableArrayList(
				Database.getChoicesSpec("Specialization", iq4.getSelectionModel().getSelectedIndex())));//
		iq6 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("work_situation")));
		iq8 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("job_type")));
		iq11 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("studies_situation")));
		iq12 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("work_location")));

		qUForm.add(iq1, 0, 2);
		qUForm.add(iq2, 0, 4);
		qUForm.add(iq3, 0, 6);
		qUForm.add(iq4, 0, 8);
		qUForm.add(iq5, 0, 10);
		qUForm.add(iq6, 0, 12);
		qUForm.add(iq7, 0, 14);
		qUForm.add(iq8, 0, 16);
		qUForm.add(iq9, 0, 18);
		qUForm.add(iq10, 0, 20);
		qUForm.add(iq11, 0, 22);
		qUForm.add(iq12, 0, 24);

		submitBtn = new Button("Submit");
		ubackBtn = new Button("Back");
		qUForm.add(submitBtn, 0, 25);
		qUForm.add(ubackBtn, 0, 27);

		ScrollPane scrollPane = new ScrollPane(qUForm);
		scrollPane.setFitToHeight(true);
		BorderPane root = new BorderPane(scrollPane);
		/*----Events-----*/

		iq6.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number number, Number new_number) {
				if (iq6.getSelectionModel().getSelectedIndex() == 2) {
					iq7.setDisable(true);
					iq8.setDisable(true);
					iq9.setDisable(true);
					iq10.setDisable(true);

				} else {
					iq7.setDisable(false);
					iq8.setDisable(false);
					iq9.setDisable(false);
					iq10.setDisable(false);
				}
			}
		});
		iq4.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number number, Number new_number) {
				iq5.setItems(FXCollections.observableArrayList(
						Database.getChoicesSpec("Specialization", iq4.getSelectionModel().getSelectedIndex())));
				;
			}
		});

		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (iq1.getText().isEmpty() || iq2.getText().isEmpty() || iq3.getText().isEmpty()
						|| iq4.getSelectionModel().isEmpty() || iq5.getSelectionModel().isEmpty()
						|| iq6.getSelectionModel().isEmpty() || iq11.getSelectionModel().isEmpty()
						|| iq12.getSelectionModel().isEmpty()) {
					JOptionPane optionPane = new JOptionPane("Completati toate campurile corect");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}

				else if (iq6.getSelectionModel().getSelectedIndex() != 2) {
					if (iq7.getText().isEmpty() || iq8.getSelectionModel().isEmpty() || iq9.getText().isEmpty()
							|| iq10.getText().isEmpty()) {
						JOptionPane optionPane = new JOptionPane("Completati toate campurile corect");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					} else if (!Utilities.isValidEmailAddress(iq2.getText())) {
						JOptionPane optionPane = new JOptionPane("Email-ul nu are forma corecta");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					} else if (!iq3.getText().matches("\\d*")) {
						JOptionPane optionPane = new JOptionPane("Numarul de telefon nu are forma corecta");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					} else {
						Database.insertQForm(iq1.getText(), iq2.getText(), Integer.parseInt(iq3.getText()),
								iq4.getSelectionModel().getSelectedIndex() + 1,
								Database.correctSpec(iq5.getSelectionModel().getSelectedItem()),
								iq6.getSelectionModel().getSelectedIndex() + 1, iq7.getText(),
								iq8.getSelectionModel().getSelectedIndex() + 1, iq9.getText(), iq10.getText(),
								iq11.getSelectionModel().getSelectedIndex() + 1,
								iq12.getSelectionModel().getSelectedIndex() + 1);
						{
							JOptionPane optionPane = new JOptionPane("Chestionar completat");
							JDialog dialog = optionPane.createDialog("Warning");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
						}
						Main_JavaFX.stage.setScene(start());

					}
				} else if (!Utilities.isValidEmailAddress(iq2.getText())) {
					JOptionPane optionPane = new JOptionPane("E-mailul nu are forma corecta");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else if (!iq3.getText().matches("\\d*")) {
					JOptionPane optionPane = new JOptionPane("Numarul de telefon nu are forma corecta");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					Database.insertQForm(iq1.getText(), iq2.getText(), Integer.parseInt(iq3.getText()),
							iq4.getSelectionModel().getSelectedIndex() + 1,
							Database.correctSpec(iq5.getSelectionModel().getSelectedItem()),
							iq6.getSelectionModel().getSelectedIndex() + 1, iq7.getText(),
							iq8.getSelectionModel().getSelectedIndex() + 1, iq9.getText(), iq10.getText(),
							iq11.getSelectionModel().getSelectedIndex() + 1,
							iq12.getSelectionModel().getSelectedIndex() + 1);
					JOptionPane optionPane = new JOptionPane("Chestionar completat");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					Main_JavaFX.stage.setScene(start());

				}

			}
		});
		ubackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(start());
			}
		});

		return new Scene(root, width, height);

	}

	/*----Change password Scene*/
	public static Scene changePassScene() {
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		cpassgrid = new GridPane();
		/*-------Controls------*/
		cpassgrid.setAlignment(Pos.CENTER);
		cpassgrid.setHgap(10);
		cpassgrid.setVgap(10);
		cpassgrid.setPadding(new Insets(25, 25, 25, 25));

		cpassLabel1 = new Label("Parola veche");
		cpassLabel2 = new Label("Parola noua");
		cpassLabel3 = new Label("Rescrieti parola");
		cpassFormBtn = new Button("Schimba parola");
		cbackBtn = new Button("Inapoi");
		cpassField1 = new TextField();
		cpassField2 = new TextField();
		cpassField3 = new TextField();

		cpassgrid.add(cpassLabel1, 0, 1);
		cpassgrid.add(cpassLabel2, 0, 2);
		cpassgrid.add(cpassLabel3, 0, 3);
		cpassgrid.add(cpassField1, 1, 1);
		cpassgrid.add(cpassField2, 1, 2);
		cpassgrid.add(cpassField3, 1, 3);
		cpassgrid.add(cpassFormBtn, 1, 4);
		cpassgrid.add(cbackBtn, 1, 5);

		/*------Events-----*/
		cbackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(start());
			}
		});
		cpassFormBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String pv = cpassField1.getText();
				String pn = cpassField2.getText();
				String rpn = cpassField3.getText();
				if (pv.equals(Main_JavaFX.loggedUser_p)) {
					if (pn.equals(rpn)) {
						if (Utilities.checkPass(pn)) {
							Database.changePassword(Main_JavaFX.loggedUser_u, pn);
							Main_JavaFX.stage.setScene(start());
						} else {
							JOptionPane optionPane = new JOptionPane(
									"Parola trebuie sa fie de minim 5 caractere,litere si cifre.");
							JDialog dialog = optionPane.createDialog("Warning");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
						}

					} else {
						JOptionPane optionPane = new JOptionPane("Parolele nu corespund.");
						JDialog dialog = optionPane.createDialog("Warning");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}

				} else {
					JOptionPane optionPane = new JOptionPane("Parola veche nu corespunde.");
					JDialog dialog = optionPane.createDialog("Warning");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}

			}
		});

		return new Scene(cpassgrid, width, height);
	}

}