package app2.Helper;

public class QForm {
	private String q1, q2, q7, q9, q10;
	private int q3, q4, q5, q6, q8, q11, q12;

	public QForm() {
	};

	public QForm(String q1, String q2, int q3, int q4, int q5, int q6, String q7, int q8, String q9, String q10, int q11,
			int q12) {
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
		this.q5 = q5;
		this.q6 = q6;
		this.q7 = q7;
		this.q8 = q8;
		this.q9 = q9;
		this.q10 = q10;
		this.q11 = q11;
		this.q12 = q12;
	}

	public String getQ1() {
		return q1;
	}

	public String getQ2() {
		return q2;
	}

	public int getQ3() {
		return q3;
	}

	public int getQ4() {
		return q4;
	}

	public int getQ5() {
		return q5;
	}

	public int getQ6() {
		return q6;
	}

	public String getQ7() {
		return q7;
	}

	public int getQ8() {
		return q8;
	}

	public String getQ9() {
		return q9;
	}

	public String getQ10() {
		return q10;
	}

	public int getQ11() {
		return q11;
	}

	public int getQ12() {
		return q12;
	}

	public String toString() {
		return q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " " + q6 + " " + q7 + " " + q8 + " " + q9 + " " + q10
				+ " " + q11 + " " + q12;
	}

}
