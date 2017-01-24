package app2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import app2.Helper.QForm;
import app2.Helper.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

	/*-----------------------------*/

	public static void insertQForm(String a1, String a2, int a3, int a4, int a5, int a6, String a7, int a8, String a9,
			String a10, int a11, int a12) {
		Connection c = null;
		Statement stmt = null;
		String id = a1 + a3;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO public.\"qform\" VALUES('" + a1 + "','" + a2 + "'," + a3 + "," + a4 + "," + a5
					+ "," + a6 + ",'" + a7 + "'," + a8 + ",'" + a9 + "','" + a10 + "'," + a11 + "," + a12 + ",'" + id
					+ "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Records created successfully");
	}

	public static QForm getQForms(int n) {
		Connection c = null;
		Statement stmt = null;
		QForm qf = new QForm();

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT * FROM \"qform\";";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			/*----exceptions----*/
			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			if (n > rowcount)
				n = n % rowcount;
			if (n == 0)
				n = 1;
			/*----------*/
			while (rs.next()) {
				if (i == n - 1) {
					qf = new QForm(rs.getString("name"), rs.getString("email"), rs.getInt("phone_number"),
							rs.getInt("college"), rs.getInt("speciality"), rs.getInt("work_situation"),
							rs.getString("job_title"), rs.getInt("job_type"), rs.getString("company_name"),
							rs.getString("city_name"), rs.getInt("studies_situation"), rs.getInt("work_location"));

				}
				i++;
			}

			rs.close();
			stmt.close();
			c.close();

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Query successfull.");
		return qf;
	}

	public static Vector<String> getChoices(String table) {
		Connection c = null;
		Statement stmt = null;
		Vector<String> choices = new Vector<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + table + "\";");
			while (rs.next()) {
				if (rs.getInt(1) != 0)
					choices.addElement(rs.getString("name"));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return choices;
	}

	public static Vector<String> getChoicesSpec(String table, int column) {
		Connection c = null;
		Statement stmt = null;
		Vector<String> choices = new Vector<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + table + "\";");
			while (rs.next()) {
				if (column == -1) {
					if (rs.getString("fc-1") != null)
						choices.addElement(rs.getString("fc-1"));
				} else if (rs.getString("fc-" + (column + 1)) != null)
					choices.addElement(rs.getString("fc-" + (column + 1)));

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return choices;
	}

	public static String getSingleChoice(String table, String id, int n) {
		Connection c = null;
		Statement stmt = null;
		String choice = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + table + "\" WHERE " + id + "=" + n + ";");
			rs.next();
			if (rs.getInt(1) != 0)
				choice = rs.getString("name");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return choice;
	}

	public static String getSingleChoiceSpec(int id) {
		Connection c = null;
		Statement stmt = null;
		String choice = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"Specialization\" WHERE id=" + id + ";");
			rs.next();
			for (int i = 1; i <= 18; i++) {

				choice = rs.getString("fc-" + i);
				System.out.println(choice);
				if (!rs.wasNull())
					return choice;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return null;
	}

	public static void changePassword(String u, String p) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE public.\"User\" SET password ='" + p + "' WHERE username='" + u + "';";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
	}

	public static String getPassword(String a) {
		Connection c = null;
		Statement stmt = null;
		String s = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT * FROM public.\"User\" WHERE username='" + a + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next())
				return null;
			s = rs.getString("password");
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Query successfull.");
		return s;
	}

	public static void insertUser(String user, String pass, String type) {

		Connection c = null;
		Statement stmt = null;
		String id = user + pass + type;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO public.\"User\" VALUES('" + user + "','" + pass + "'," + type + ",'" + id + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Records created successfully");
	}

	public static void deleteUser(String username) {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM public.\"User\" WHERE username='" + username + "';";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
	}

	public static boolean checkIfExist(String user) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"User\" WHERE username=('" + user + "');");
			if (!rs.next()) {
				rs.close();
				stmt.close();
				c.close();
				return false;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return true;
		}
		// System.out.println("Operation done successfully");
		return true;

	}

	public static Vector<String> getAllUsers() {
		Connection c = null;
		Statement stmt = null;
		Vector<String> choices = new Vector<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"User\";");
			while (rs.next()) {
				choices.addElement(rs.getString("username"));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return choices;
	}

	public static int getType(String u) {
		Connection c = null;
		Statement stmt = null;
		int s = -1;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT * FROM public.\"User\" WHERE username='" + u + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next())
				return -1;
			s = rs.getInt("user_type");
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Query successfull.");
		return s;
	}

	public static Double pStudent(int a, int b) {
		Connection c = null;
		Statement stmt = null;
		Double d1 = 0.0, d2 = 0.0;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT COUNT(*) AS count FROM public.\"qform\" WHERE studies_situation=" + (a + 1)
					+ " AND work_location=" + (b + 1) + ";";
			ResultSet rs1 = stmt.executeQuery(sql);
			rs1.next();
			d1 = rs1.getDouble("count");
			sql = "SELECT COUNT(*) AS count FROM public.\"qform\" WHERE studies_situation=" + (a + 1) + ";";
			ResultSet rs2 = stmt.executeQuery(sql);
			rs2.next();
			d2 = rs2.getDouble("count");
			rs1.close();
			rs2.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Query successfull.");
		if (Utilities.isNaN(d1 / d2))
			return 0.0;
		else
			return d1 / d2;
	}

	public static ObservableList<Student> getStudents(int ss, int cl) {
		Connection c = null;
		Statement stmt = null;
		ObservableList<Student> data = FXCollections.observableArrayList();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT name,email,phone_number FROM public.\"qform\" WHERE studies_situation=" + ss
					+ " AND college=" + cl + ";";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				data.add(new Student(rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Query successfull.");
		return data;
	}

	public static int correctSpec(String s) {
		Connection c = null;
		Statement stmt = null;
		int value = 0;
		System.out.println(s);
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app_POO", "postgres", "1q2w3e");
			c.setAutoCommit(false);

			stmt = c.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			for (int i = 1; i <= 18; i++) {
				String sql = "SELECT * FROM public.\"Specialization\" WHERE \"fc-" + i + "\"='" + s + "';";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next())
					value = rs.getInt("id");
				rs.close();
			}
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return value;

	}
}