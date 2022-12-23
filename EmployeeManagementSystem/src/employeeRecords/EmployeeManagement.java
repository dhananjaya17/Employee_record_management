package employeeRecords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class EmployeeManagement {
	List<Employee> empList = null;
	List<Department> deptList = null;
	Employee employee = null;
	Department department = null;
	FileReader fileReader =null;
	String employeeDetails = null;
	String[] deptData =null;
	String[] empData = null;
	int[] data1 = null;
	float[] data2 =null;
	List<Employee> newEmpList = null;
	public static final String employeeFilePath ="D://EmloyeeManagement//employee.csv" ;
	public static final String departmentFilePath ="D://EmloyeeManagement//department.csv" ;

	public List<Department> getAllDepartments() throws IOException{
		deptList = new ArrayList<>();
		String departmentDetails = null;
		int[] data = null ;
		File file = new File(departmentFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		for(int i =1 ;(departmentDetails = bufferedReader.readLine()) != null;i++ ){
			while((departmentDetails = bufferedReader.readLine()) != null) {
				deptData = departmentDetails.split("\\,");
				data = new int[deptData.length];
				data[0]	= Integer.parseInt(deptData[0]);
				department = new Department(data[0],deptData[1],deptData[2]);
				deptList.add(department);
			}
		}

		fileReader.close();
		return deptList;
	}

	public List<Employee> getAllEmployees() throws IOException {
		empList = new ArrayList<>();
		FileReader fileReader1 =null;
		File file = new File(employeeFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		fileReader1 = new FileReader(file );
		BufferedReader bufferedReader1= new BufferedReader(fileReader1);
		for(int j =1 ;(employeeDetails = bufferedReader1.readLine()) != null;j++ ) {
			while((employeeDetails = bufferedReader1.readLine()) != null) {
				empData = employeeDetails.split("\\,");
				data1 = new int[empData.length];
				data1[0]= Integer.parseInt(empData[0]);
				data1[1]= Integer.parseInt(empData[6]);
				data2 = new float[empData.length];
				data2[1] = Float.parseFloat(empData[4]);
				deptList = getAllDepartments();
				for(int dept=0 ; dept<deptList.size(); dept++) {
					department = deptList.get(dept);
					int deptno1 = department.getDeptno();
					String deptno2 = String.valueOf(deptno1);
					if(empData[5].equals(deptno2)) {
						employee = new Employee(data1[0],empData[1],empData[2],empData[3],data2[1],department,data1[1]);
						empList.add(employee);
					}
				}
			}
		}


		fileReader1.close();
		return empList;
	}

	public void addAnEmployee(String filterOption ) throws IOException {
		File file = new File(employeeFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file,true);
		fileWriter.write(filterOption);
		fileWriter.close();
	}
	public void addADepartment(String filterOption ) throws IOException {
		File file = new File(departmentFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file,true);
		fileWriter.write(filterOption);
		fileWriter.close();
	}
	public List<Employee> displayEmployeeByDeptName(String filterOption) throws IOException {
		newEmpList = new ArrayList<>(); 
		if(empList==null && filterOption!=null) {
			empList = getAllEmployees() ;
			for(int i =0;i<empList.size();i++) {
				employee = empList.get(i);
				Department employeedata = employee.getDept();
				String tempdata = employeedata.getDeptname();
				if(filterOption.toLowerCase().equals(tempdata.toLowerCase())) {
					newEmpList.add(employee);
				}
			}
		}
		return  newEmpList;	
	}
	public float displayHighestSalary() throws IOException {
		Employee tempEmployee = null;
		float salary =0;
		float maxSalary = 0;
		if(empList==null) {
			empList = getAllEmployees() ;
			for(int i =0;i<empList.size();i++) {
				employee = empList.get(i);
				salary = employee.getSal();
				if(maxSalary <= salary ) {
					maxSalary = salary;
					tempEmployee =employee;
				}
			}
		}
		System.out.println(tempEmployee);
		return maxSalary  ;	
	}
	public float updataEmpSalaryByIncrement(float hikePercentage) throws IOException {
		newEmpList = new ArrayList<>();
		float currentSalary = 0;
		float hikeSalary = 0;
		if(hikePercentage == 0) {
			System.out.println("Re-enter the percentage!");
		}else if(hikePercentage != 0){
			if(empList==null) {
				empList = getAllEmployees();
				for(int i =0;i<empList.size();i++) {
					employee = empList.get(i);
					currentSalary = employee.getSal();
					hikeSalary = (currentSalary*hikePercentage/100);
					currentSalary =(currentSalary + hikeSalary);
				}
			}
		}
		return currentSalary;	
	}

	public void updateEmployee(String oldData ,String newReplaceData ) throws IOException{
		if(oldData !=null && newReplaceData !=null) {
			File file = new File(employeeFilePath);
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((employeeDetails = bufferedReader.readLine()) != null) {
				empData = employeeDetails.split("\\,");
				if(empData[1].equals(oldData)) {
					empData[1].replace(oldData, newReplaceData);
				}
			} 
		}fileReader.close();	
	}
	public List<Employee> getEmployeeUnderManager(int mgr) throws IOException {
		newEmpList = new ArrayList<>();
		if(empList==null) {
			empList = getAllEmployees() ;
			for(int i =0;i<empList.size();i++) {
				employee = empList.get(i);
				int empData = employee.getMgr();
				if(mgr == empData  ) {
					newEmpList.add(employee);
				}
			}
		}
		return newEmpList;

	}
	public static void main(String[] args) throws IOException {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		List<Employee> empList = null;
		List<Department> deptList = null;
		Scanner intScanner = new Scanner(System.in);
		Scanner stringScanner = new Scanner(System.in);
		try (Scanner floatScanner = new Scanner(System.in)) {
			int userChoice = -1;
			do {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Select which operation should be perfomed :  ");
				System.out.println("--------------------------------------------------------------------------");

				System.out.println("1: Display All Employee");
				System.out.println("2: Add Employee");
				System.out.println("3: Add Department");
				System.out.println("4: Display EmployeeByDeptName");
				System.out.println("5: Maximum Paid Employee ");
				System.out.println("6: updataEmpSalaryByIncrement");
				System.out.println("7: Employee By Manager");
				System.out.println("8: Display Departments");
				System.out.println("9: Updata Employee Details");

				System.out.println("0: Exit ");


				System.out.println("--------------------------------------------------------------------------");
				System.out.println("UserOption: ");
				System.out.println("--------------------------------------------------------------------------");
				userChoice = intScanner.nextInt();
				int userFilterChoice;
				switch(userChoice){
				case 1: 
					System.out.println("-------------------------Employees---------------------------------------------");
					System.out.println("Employee Details:  ");
					empList = employeeManagement.getAllEmployees();
					for(int employee=0 ;employee<empList.size();employee++) {
						System.out.println(empList.get(employee).toString());
					}
					break;
				case 2:
					System.out.println("--------------Add Employee------------------------------------------------");
					System.out.println("Enter how many Employees you want: ");
					int numberOfEmployees = intScanner.nextInt();
					System.out.println("--------------Insert Employee Details---------------------------------");
					for(int i=0; i<numberOfEmployees ;i++) {
						System.out.println("enter the  EmployeeNo: ");
						int empno = intScanner.nextInt();
						System.out.println("enter the  FirstName: ");
						String firstname = stringScanner.nextLine();
						System.out.println("enter the LastName: ");
						String lastname =stringScanner.nextLine();
						System.out.println("enter the Email: ");
						String email = stringScanner.nextLine();
						System.out.println("enter the Salary: ");
						float sal = floatScanner.nextFloat();
						System.out.println("enter the Deptno: ");
						int deptno =intScanner.nextInt();
						System.out.println("enter the  Manager: ");
						int mgr =intScanner.nextInt();
						String newEmployee = "\r\n"+empno+","+firstname+","+lastname+","+email+","+sal+","+deptno+","+mgr;
						employeeManagement.addAnEmployee(newEmployee);
					}
					System.out.println("--------------New Employee have been updated !------------------------");
					break;
				case 3:
					System.out.println("-------------------------Add Department-----------------------------------");
					System.out.println("Enter how many Departments you want: ");
					int numberOfDepartments = intScanner.nextInt();
					System.out.println("--------------Insert Employee Details---------------------------------");
					for(int j=0; j<numberOfDepartments ;j++) {
						System.out.println("enter the Deptno: ");
						int deptno =intScanner.nextInt();
						System.out.println("enter the DeptName : ");
						String deptName = stringScanner.nextLine();
						System.out.println("enter the Location: ");
						String location =stringScanner.nextLine();
						String newDepartment = "\r\n"+deptno+","+deptName+","+location;
						employeeManagement.addADepartment(newDepartment);
					}
					System.out.println("--------------New Department have been updated !----------------------");
					break;
				case 4:
					System.out.println("---------Employee Details By DepartmentName---------------------------");
					System.out.println("Departments:");
					System.out.println("          1: sales       ");
					System.out.println("          2: finance     ");
					System.out.println("          3: engineering ");

					System.out.println("----------------------------------------------------------------------");
					System.out.println("UserFilterOption: ");
					userFilterChoice = intScanner.nextInt();
					String filterOption = null;
					switch(userFilterChoice) {
					case 1:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Sales Department Employees: ");
						filterOption = "sales";
						break;
					case 2:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Finance Department Employees: ");
						filterOption = "finance";
						break;
					case 3:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Engineering Department Employees: ");
						filterOption = "engineering";
						break;
					}
					empList = employeeManagement.displayEmployeeByDeptName(filterOption);
					for(int employee=0 ;employee<empList.size();employee++) {
						System.out.println(empList.get(employee).toString());
					}

					break;
				case 5:
					float salary = employeeManagement.displayHighestSalary();
					System.out.println("The hightest Paid Salary is: "+salary);
					break;
				case 6:
					System.out.println("Enter the Hike Percentage: ");
					float hikePercentage = floatScanner.nextFloat();
					float hikeSalary = employeeManagement.updataEmpSalaryByIncrement(hikePercentage);
					System.out.println("Salary after the hike:"+hikeSalary);
					break;
				case 7:
					System.out.println("---------Employee Details By Manager-----------------------------------");
					System.out.println("Manager:");
					System.out.println("          1: 103  ");
					System.out.println("          2: 105  ");
					System.out.println("          3: no manager ");

					System.out.println("----------------------------------------------------------------------");
					System.out.println("UserFilterOption: ");
					userFilterChoice = intScanner.nextInt();
					int manager = 0;
					switch(userFilterChoice) {
					case 1:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Employees under manager 103: ");
						manager = 103;
						break;
					case 2:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Employees under manager 105: ");
						manager = 105;
						break;
					case 3:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("Employees without manager: ");
						manager = 0;
						break;	
					}
					empList = employeeManagement.getEmployeeUnderManager(manager);
					for(int employee=0 ;employee<empList.size();employee++) {
						System.out.println(empList.get(employee).toString());
					}
					break;
				case 8:
					System.out.println("-------------------Departments---------------------------------------------");
					System.out.println("Department Details:  ");
					deptList = employeeManagement.getAllDepartments();
					for(int department=0 ;department<deptList.size();department++) {
						System.out.println(deptList.get(department).toString());
					}
					break;
				case 9:
					System.out.println("--------------Edit Employee Details-----------------------------------");
					System.out.println("Enter EmployeeDetail to edited: ");
					String oldData = stringScanner.nextLine();
					System.out.println("Enter ReplaceDetail of employee: ");
					String replaceData = stringScanner.nextLine();
					employeeManagement.updateEmployee(oldData,replaceData );
					System.out.println("-------------------------- updated !----------------------------------");
					break;
				}

			}while(userChoice != 0);
		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}
}

