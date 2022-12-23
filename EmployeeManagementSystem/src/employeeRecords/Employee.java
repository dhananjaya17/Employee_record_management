package employeeRecords;

public class Employee {
	
	
	int empno;
	String firstname;
	String lastname;
	String email;
	float sal;
	int mgr;
	Department dept;
	
	
	public Employee(int empno, String firstname, String lastname, String email, float sal, Department dept, int mgr) {
		super();
		this.empno = empno;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.sal = sal;
		this.dept=dept;
		this.mgr = mgr;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", firstname=" + firstname + ", lastname=" +  lastname + ", email=" +  email
				+ ", sal=" +  sal + "," + dept + ", mgr=" + mgr + "]";
	}
	
}
