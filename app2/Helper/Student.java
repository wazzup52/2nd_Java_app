package app2.Helper;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	private final SimpleStringProperty name;
	private final SimpleStringProperty email;
	private final SimpleStringProperty pn;

	public Student(String name, String email, String pn) {
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.pn = new SimpleStringProperty(pn);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String n) {
		name.set(n);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String fName) {
		email.set(fName);
	}

	public String getPN() {
		return pn.get();
	}

	public void setPN(String p) {
		pn.set(p);
	}

	public String toString() {
		return name + " " + email + " " + pn;
	}

}