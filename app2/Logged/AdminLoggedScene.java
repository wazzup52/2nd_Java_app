package app2.Logged;


import app2.Database;
import app2.Main_JavaFX;
import app2.Utilities;
import app2.Helper.QForm;
import app2.Helper.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminLoggedScene {
	/*-----Declarations----*/
	private static int width = 800, height = 400;
	private static GridPane grid, qUForm;
	private static Button abackBtn, showAllQFormsBtn, showStudentBtn1, showStudentsBtn2, banUserBtn, exportBtn;
	private static ChoiceBox<String> cb1, cb2, cb3, cb4, cb5;
	private static Label ptext, label1, label2;

	/*-----Admin logged on Scene------*/
	public static Scene start() {
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 0));

		/*-----Controls----*/
		showAllQFormsBtn = new Button("Afisati toate chestionarele");
		abackBtn = new Button("Logout");
		showStudentBtn1 = new Button("Afisati ");
		showStudentsBtn2 = new Button("Afisati % : ");
		ptext = new Label("Castraveti");
		cb1 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("studies_situation")));
		cb2 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("college")));
		banUserBtn = new Button("Ban user");
		cb3 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getAllUsers()));
		cb4 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("studies_situation")));
		cb5 = new ChoiceBox<String>(FXCollections.observableArrayList(Database.getChoices("work_location")));
		label1 = new Label();
		label2 = new Label();
		/*----Location----*/
		grid.add(showAllQFormsBtn, 0, 0);
		grid.add(label1, 0, 1);
		grid.add(cb1, 0, 2);
		grid.add(cb2, 0, 3);
		grid.add(showStudentBtn1, 0, 4);
		grid.add(label2, 0, 5);
		grid.add(cb4, 0, 6);
		grid.add(cb5, 0, 7);
		grid.add(showStudentsBtn2, 0, 8);
		grid.add(ptext, 0, 9);
		grid.add(banUserBtn, 3, 0);
		grid.add(cb3, 4, 0);
		grid.add(abackBtn, 4, 10);

		/*-----Events-----*/
		showAllQFormsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(showAllQFormsScene(1));
			}
		});
		abackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(Login_Scene.start());
			}
		});
		showStudentBtn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!(cb1.getSelectionModel().isEmpty() || cb2.getSelectionModel().isEmpty()))
					Main_JavaFX.stage.setScene(showStudentScene(cb1.getSelectionModel().getSelectedIndex() + 1,
							cb2.getSelectionModel().getSelectedIndex() + 1));
			}
		});
		banUserBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!(cb3.getSelectionModel().isEmpty())) {
					String c = cb3.getSelectionModel().getSelectedItem().toString();
					if (Database.getType(c) != 1) {
						Database.deleteUser(c);
						cb3.setItems(FXCollections.observableArrayList(Database.getAllUsers()));
					}
				}
			}
		});
		showStudentsBtn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!(cb4.getSelectionModel().isEmpty() || cb5.getSelectionModel().isEmpty()))
					ptext.setText(Double.toString(Database.pStudent(cb4.getSelectionModel().getSelectedIndex(),
							cb5.getSelectionModel().getSelectedIndex()) * 100) + "%");
			}
		});

		return new Scene(grid, width, height);
	}

	/*-------show all qforms scene*/
	public static Scene showAllQFormsScene(int k) {
		int width = 600, height = 600;
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		int z = k;
		qUForm = new GridPane();
		qUForm.setAlignment(null);
		qUForm.setHgap(10);
		qUForm.setVgap(10);
		qUForm.setPadding(new Insets(25, 25, 25, 25));
		/*----Questions---*/
		Label q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, label;
		label = new Label("Chestionar ");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		q1 = new Label("1. Nume Student ");
		q1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q2 = new Label("2. Adresa de email ");
		q2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q3 = new Label("3. Numar de telefon  ");
		q3.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q4 = new Label("4. Facultatea la care este student sau pe care a absolvit-o  ");
		q4.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q5 = new Label("5. Specializarea  ");
		q5.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q6 = new Label("6. Situatia pe piata fortei de munca  ");
		q6.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q7 = new Label("7. Denumirea postului ocupat  ");
		q7.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q8 = new Label("8. Tipul postului ocupat  ");
		q8.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q9 = new Label("9. Numele companiei unde lucreaza  ");
		q9.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q10 = new Label("10. Orasul in care lucreaza  ");
		q10.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q11 = new Label("11. Situatia scolara actuala  ");
		q11.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		q12 = new Label("12. Unde ar dori sa lucreze  ");
		q12.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		qUForm.add(label, 0, 1);
		qUForm.add(q1, 0, 2);
		qUForm.add(q2, 0, 4);
		qUForm.add(q3, 0, 6);
		qUForm.add(q4, 0, 8);
		qUForm.add(q5, 0, 10);
		qUForm.add(q6, 0, 12);
		qUForm.add(q7, 0, 14);
		qUForm.add(q8, 0, 16);
		qUForm.add(q9, 0, 18);
		qUForm.add(q10, 0, 20);
		qUForm.add(q11, 0, 22);
		qUForm.add(q12, 0, 24);

		/*----Answers----*/
		Label iq1, iq2, iq3, iq7, iq9, iq10, iq4, iq5, iq6, iq8, iq11, iq12;
		QForm qf = Database.getQForms(z);
		iq1 = new Label(qf.getQ1());
		iq1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq2 = new Label(qf.getQ2());
		iq2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq3 = new Label(Integer.toString(qf.getQ5()));
		iq3.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq4 = new Label(Database.getSingleChoice("college", "c_id", qf.getQ4()));
		iq4.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq5 = new Label(Database.getSingleChoiceSpec(qf.getQ5()));
		iq5.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq6 = new Label(Database.getSingleChoice("work_situation", "ws_id", qf.getQ6()));
		iq6.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq7 = new Label(qf.getQ7());
		iq7.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq8 = new Label(Database.getSingleChoice("job_type", "j_id", qf.getQ8()));
		iq8.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq9 = new Label(qf.getQ9());
		iq9.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq10 = new Label(qf.getQ10());
		iq10.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq11 = new Label(Database.getSingleChoice("studies_situation", "s_id", qf.getQ11()));
		iq11.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		iq12 = new Label(Database.getSingleChoice("work_location", "wl_id", qf.getQ12()));
		iq12.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		qUForm.add(iq1, 0, 3);
		qUForm.add(iq2, 0, 5);
		qUForm.add(iq3, 0, 7);
		qUForm.add(iq4, 0, 9);
		qUForm.add(iq5, 0, 11);
		qUForm.add(iq6, 0, 13);
		qUForm.add(iq7, 0, 15);
		qUForm.add(iq8, 0, 17);
		qUForm.add(iq9, 0, 19);
		qUForm.add(iq10, 0, 21);
		qUForm.add(iq11, 0, 23);
		qUForm.add(iq12, 0, 25);

		/*----Controls-----*/
		Button nextBtn = new Button("Urmatorul Chestionar");
		Button previousBtn = new Button("Chestionarul anterior");
		Button ubackBtn = new Button("Inapoi");
		exportBtn = new Button("Export to PDF");
		qUForm.add(nextBtn, 1, 26);
		qUForm.add(previousBtn, 0, 26);
		qUForm.add(ubackBtn, 0, 28);
		qUForm.add(exportBtn, 0, 27);

		ScrollPane scrollPane = new ScrollPane(qUForm);
		scrollPane.setFitToHeight(true);
		BorderPane root = new BorderPane(scrollPane);

		/*---Events---*/
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(showAllQFormsScene(z + 1));
			}
		});
		previousBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (z - 1 <= 0)
					Main_JavaFX.stage.setScene(showAllQFormsScene(1));
				else
					Main_JavaFX.stage.setScene(showAllQFormsScene(z - 1));
			}
		});
		ubackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(start());
			}
		});
		exportBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					Utilities.toPDF("chestionar" + z + ".pdf", q1.getText(), iq1.getText(), q2.getText(), iq2.getText(),
							q3.getText(), iq3.getText(), q4.getText(), iq4.getText(), q5.getText(), iq5.getText(),
							q6.getText(), iq6.getText(), q7.getText(), iq7.getText(), q8.getText(), iq8.getText(),
							q9.getText(), iq9.getText(), q10.getText(), iq10.getText(), q11.getText(), iq11.getText(),
							q12.getText(), iq12.getText());
				} catch (Exception msg) {
					System.out.println("Eroare." + msg);
					System.exit(0);
				}
			}
		});
		return new Scene(root, width, height);
	}

	/*-------show students scene------*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Scene showStudentScene(int a, int b) {
		int width = 330;
		int height = 410;
		Utilities.centerStage(Main_JavaFX.stage, width, height);
		TableView<Student> table = new TableView<Student>();
		ObservableList<Student> data = Database.getStudents(a, b);

		table.setEditable(false);
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		TableColumn emailCol = new TableColumn("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		TableColumn pnCol = new TableColumn("Phone Number");
		pnCol.setCellValueFactory(new PropertyValueFactory<Student, String>("pn"));

		table.setItems(data);
		table.getColumns().addAll(nameCol, emailCol, pnCol);

		Button backBtn = new Button("Inapoi");
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table);
		vbox.getChildren().add(backBtn);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main_JavaFX.stage.setScene(start());
			}
		});

		return new Scene(vbox, width, height);
	}
}
