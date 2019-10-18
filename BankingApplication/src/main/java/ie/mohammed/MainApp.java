package ie.mohammed;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.model.Employee;
import ie.mohammed.model.InputValidation;
import ie.mohammed.model.Customer;
//import ie.mohammed.domain.Power;
import ie.mohammed.model.Account;
import ie.mohammed.service.impl.AccountServiceImpl;
import ie.mohammed.service.impl.CustomerServiceImpl;
import ie.mohammed.service.impl.EmployeeServiceImpl;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		CustomerServiceImpl customerService = (CustomerServiceImpl) context.getBean("customerServiceImpl");
		EmployeeServiceImpl employeeService = (EmployeeServiceImpl) context.getBean("employeeServiceImpl");
		AccountServiceImpl accountService = (AccountServiceImpl) context.getBean("accountServiceImpl");

		InputValidation inputValidation = new InputValidation();
		/*
		 * System.out.println("There are " +
		 * customerService.CountTotalCustomer()+" total number of customers");
		 * System.out.println("Customer Details\n"); List<Customer> customers =
		 * customerService.findAllCustomer(); for(Customer customer: customers)
		 * System.out.println(customer);
		 * 
		 * System.out.println("There are " + employeeService.totalEmployeesCount() +
		 * " total number of employees."); System.out.println("Employee Details\n");
		 * List<Employee> employee = employeeService.totalEmployees(); for(Employee emp:
		 * employee) System.out.println(emp);
		 * 
		 * System.out.println("There are " + accountService.totalNumberOfAccount() +
		 * "total number of accounts"); System.out.println("Customer Details\n");
		 * List<Account> account = accountService.findAllAccount(); for(Account acc:
		 * account) System.out.println(acc);
		 * 
		 * System.out.println("Account Details are- \n");
		 * accountService.displayAccountDetails();
		 * 
		 * mainMenu(); adminMenu(); customerMenu();
		 */

		Scanner scanner = new Scanner(System.in);
		//boolean user = false;
		boolean accountRedo = false;
		int choice;
		String employeeLastName;
		int employeeId;
		
		int user;
		int loginOption;
		String pwd;
		
		mainMenu();
		do 
		{
			loginOption = 0;
			user = 0;
			pwd = null;
			
			while (loginOption < 1 || loginOption > 3)
			{
				loginOption = inputValidation.getIntegerInput("Option: ");	
				
			}
			
			switch(loginOption) 
			{
			case 1: // Admin choice
				System.out.println();
				while(user < 1)
				{
					user = inputValidation.getIntegerInput("Employee ID: ");
				}
				while(pwd == null)
				{
					pwd = InputValidation.getPasswordInput("Last Name: ");
				}
				System.out.println("");
				
				Employee emp = employeeService.findById(user);
				if (emp==null)
					System.out.println("ERROR - ??????????????????/");
				if (emp.getLastName().equals(pwd)) 
				{
					System.out.println("Valid Login");
					System.out.println();
					System.out.println("Hello, " + emp.getFirstName() + " " + emp.getLastName());
					
					adminMenu();
				}
			}
				
		}while(loginOption !=4);
		
		

		while (true) {
			System.out.println("\n-------------------");
			System.out.println("BANK    OF     SPRING");
			System.out.println("-------------------\n");
			//System.out.println("1. Register account.");
			System.out.println("1. Login.");
			//System.out.println("3. Update accounts.");
			System.out.println("2. Exit.");
			System.out.print("\nEnter your choice : ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) 
			{
			case 1:
				
				System.out.println("Enter employee id : ");
				employeeId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter last name : ");
				employeeLastName = scanner.nextLine();
				Employee emp = employeeService.findById(employeeId);
				if (emp==null)
					System.out.println("ERROR - ??????????????????/");
				else if (emp.getLastName().equals(employeeLastName)) 
				{
					while (true) 
					{
						System.out.println("\n-------------------");
						System.out.println("W  E  L  C  O  M  E");
						System.out.println("-------------------\n");
						System.out.println("1. Register and Create and account.");
						System.out.println("2. Deposit.");
						System.out.println("3. Transfer.");
						System.out.println("4. Last 5 transactions.");
						System.out.println("5. User information.");
						System.out.println("6. Log out.");
						System.out.print("\nEnter your choice : ");
						choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) 
						{
						case 1:
							System.out.println("Enter first name:");
							String firstName = scanner.nextLine();
							System.out.println("Enter last name:");
							String lastName = scanner.nextLine();
							System.out.println("Enter address :");
							String address = scanner.nextLine();
							System.out.println("Enter city name:");
							String city = scanner.nextLine();
							System.out.println("Enter contact number :");
							String contactNumber = scanner.nextLine();
							System.out.println("Enter email :");
							String email = scanner.nextLine();

							customerService.saveACustomer(firstName, lastName, address, city, contactNumber, email);

							break;

						default:
							System.out.println("Wrong choice!");
						}
					}
				} else 
				{
					System.out.println("Wrong choice!");
				}
			case 2:
				/*
				 * System.out.println("\nThank you for choosing Bank Of Java."); System.exit(1);
				 * break; default: System.out.println("Wrong choice !");
				 */
				String response = "";
		    	while(!response.toLowerCase().equals("n") && !response.toLowerCase().equals("y")){
		    		response = InputValidation.getPasswordInput("Are you sure you want to exit? (Y/N)");
		    	}
		    	
            	if(response.toLowerCase().equals("n")){
            		System.out.println("Thanks for staying :)");
            	}	
		    	if(response.toLowerCase().equals("y")){
            		System.exit(0);
            	}			    		
		    	break;
		    default:
		    	System.err.println("Invalid selection");
		    	break; 
			}
		}

		/*
		 * do { System.out.println("Enter Employee ID:"); employeeId =
		 * scanner.nextInt(); for(Employee emp : employeeService.totalEmployees()) {
		 * if(employeeId == emp.getEmployeeId()) {
		 * System.out.println("Enter the password: "); password = scanner.nextLine();
		 * for(Employee em : employeeService.totalEmployees()) {
		 * if(password.equals(em.getPassword())) { user = true; } break; } } break; }
		 * }while(!user);
		 */

		/*
		 * System.out.println("Enter Password: "); password = scanner.nextLine(); for
		 * (Employee emp : employeeService.totalEmployees()) { if (employeeId ==
		 * emp.getEmployeeId() && password.equals(emp.getPassword())) { user = true; } }
		 * 
		 * } while (!user);
		 */

		/*
		 * 
		 * 
		 * System.out.println("Enter employeeID : "); employeeId = scanner.nextInt();
		 * scanner.nextLine(); System.out.println("Enter password : "); password =
		 * scanner.next(); scanner.nextLine(); for (Employee employee :
		 * employeeService.totalEmployees()) { if (employee.getEmployeeId() ==
		 * employeeId && employee.getPassword().equals(password)) { user = true; } }
		 * while (true) { long start = System.currentTimeMillis(); long end = start + 60
		 * * 1000; // 60 seconds * 1000 ms/sec int operation = 0; int userChoice;
		 * 
		 * boolean quit = false; do { System.out.println("Spring Bank System:");
		 * System.out.println("------------------------");
		 * System.out.println("1. Create Customer & Saving Account");
		 * System.out.println("2. Deposit Money");
		 * System.out.println("3. Withdraw Money");
		 * System.out.println("4. View Balance"); System.out.println("5. Quit");
		 * System.out.print("Operation count: " + operation + "(Shut down at 5)");
		 * userChoice = scanner.nextInt(); switch (userChoice) { case 1:
		 * 
		 * // create customer, then saving account regard to existing customer( maximum
		 * 2 // SA per Customer) System.out.println("Create Customer :"); String
		 * firstName, lastName, address, city, contactNumber, email;
		 * 
		 * String answer; int accountNumber; double balance; int accountId = 0;
		 * 
		 * Scanner sc = new Scanner(System.in); Customer c = new Customer(); Account
		 * account = new Account(); int saID = 0;
		 * 
		 * System.out.println("Enter First Name:"); firstName = sc.nextLine();
		 * c.setFirstName(firstName); System.out.println("Enter Last Name:"); lastName =
		 * sc.nextLine(); c.setLastName(lastName); System.out.println("Enter Address:");
		 * address = sc.nextLine(); c.setAddress(address);
		 * System.out.println("Enter City: "); city = sc.nextLine(); c.setCity(city);
		 * System.out.println("Enter Email:"); email = sc.nextLine(); c.setEmail(email);
		 * 
		 * do {
		 * 
		 * System.out.println("Crating an account, Enter account number: ");
		 * accountNumber = sc.nextInt(); account.setAccountNumber(accountNumber);
		 * System.out.println("Enter the Balance: "); balance = sc.nextDouble();
		 * account.setBalance(balance); accountService.saveAnAccount(accountNumber,
		 * balance); accountId++; System.out.println("Create another account? Y?N");
		 * answer = sc.nextLine(); if (answer.equalsIgnoreCase("n")) { accountRedo =
		 * true; } if (accountId == 2) { System.out.println("Maximum account reached!");
		 * } } while (!accountRedo && accountId != 2);
		 * 
		 * customerService.createACustomer(c); operation++; break; case 2:
		 * 
		 * // deposit //Scanner scanner = new Scanner(System.in);
		 * 
		 * Account acc = new Account(); int accNumber; double amount;
		 * System.out.println("Enter the Account Number you want to deposit.");
		 * accNumber = scanner.nextInt(); acc.setAccountNumber(accNumber); operation++;
		 * break;
		 * 
		 * 
		 * case 3: String acNums; int amts; Scanner sc2 = new Scanner(System.in);
		 * System.out.println("Enter Saving Account number u wish to do withdraw.");
		 * acNums= sc2.nextLine(); System.out.println("Enter amount :"); amts =
		 * sc2.nextInt();
		 * 
		 * sS.withdraw(acNums, amts); operation++; break;
		 * 
		 * 
		 * case 4: // view System.out.println("Saving Account Balance:");
		 * System.out.println(sS.getAllSA()); operation++; break;
		 * 
		 * 
		 * case 5: quit = true; break; default: System.out.println("Wrong choice.");
		 * break; } System.out.println(); if (operation == 5) {
		 * System.out.println("5 operation reached, System shutdown."); } if
		 * (System.currentTimeMillis() > end) { System.out.println("Session Expired.");
		 * } } while (!quit && operation != 5 && System.currentTimeMillis() < end);
		 * System.out.println("Bye!");
		 * 
		 * }
		 */

		/*
		 * long start = System.currentTimeMillis(); long end = start + 60 * 1000; // 60
		 * seconds * 1000 ms/sec int operation = 0; int userChoice;
		 * 
		 * boolean quit = false;
		 */

		/*
		 * do { System.out.println("Spring Bank System:");
		 * System.out.println("------------------------");
		 * System.out.println("1. Create Customer & Saving Account");
		 * System.out.println("2. Deposit Money");
		 * System.out.println("3. Withdraw Money");
		 * System.out.println("4. View Balance"); System.out.println("5. Quit");
		 * System.out.print("Operation count: " + operation + "(Shut down at 5)");
		 * userChoice = in.nextInt(); switch (userChoice) { case 1:
		 * 
		 * // create customer, then saving account regard to existing customer( maximum
		 * 2 // SA per Customer) System.out.println("Create Customer :"); String
		 * firstName, lastName, address, city, contactNumber, email;
		 * 
		 * String answer; int accountNumber; double balance; int accountId = 0;
		 * 
		 * Scanner sc = new Scanner(System.in); Customer c = new Customer(); Account
		 * account = new Account(); int saID = 0;
		 * 
		 * System.out.println("Enter First Name:"); firstName = sc.nextLine();
		 * c.setFirstName(firstName); System.out.println("Enter Last Name:"); lastName =
		 * sc.nextLine(); c.setLastName(lastName); System.out.println("Enter Address:");
		 * address = sc.nextLine(); c.setAddress(address);
		 * System.out.println("Enter City: "); city = sc.nextLine(); c.setCity(city);
		 * System.out.println("Enter Email:"); email = sc.nextLine(); c.setEmail(email);
		 * 
		 * do {
		 * 
		 * System.out.println("Crating an account, Enter account number: ");
		 * accountNumber = sc.nextInt(); account.setAccountNumber(accountNumber);
		 * System.out.println("Enter the Balance: "); balance = sc.nextDouble();
		 * account.setBalance(balance); accountService.saveAnAccount(accountNumber,
		 * balance); accountId++; System.out.println("Create another account? Y?N");
		 * answer = sc.nextLine(); if (answer.equalsIgnoreCase("n")) { accountRedo =
		 * true; } if (accountId == 2) { System.out.println("Maximum account reached!");
		 * } } while (!accountRedo && accountId != 2);
		 * 
		 * customerService.createACustomer(c); operation++; break; case 2:
		 * 
		 * // deposit Scanner scanner = new Scanner(System.in);
		 * 
		 * Account acc = new Account(); int accNumber; double amount;
		 * System.out.println("Enter the Account Number you want to deposit.");
		 * accNumber = scanner.nextInt(); acc.setAccountNumber(accNumber); operation++;
		 * break;
		 * 
		 * 
		 * case 3: String acNums; int amts; Scanner sc2 = new Scanner(System.in);
		 * System.out.println("Enter Saving Account number u wish to do withdraw.");
		 * acNums= sc2.nextLine(); System.out.println("Enter amount :"); amts =
		 * sc2.nextInt();
		 * 
		 * sS.withdraw(acNums, amts); operation++; break;
		 * 
		 * 
		 * case 4: // view System.out.println("Saving Account Balance:");
		 * System.out.println(sS.getAllSA()); operation++; break;
		 * 
		 * 
		 * case 5: quit = true; break; default: System.out.println("Wrong choice.");
		 * break; } System.out.println(); if (operation == 5) {
		 * System.out.println("5 operation reached, System shutdown."); } if
		 * (System.currentTimeMillis() > end) { System.out.println("Session Expired.");
		 * } } while (!quit && operation != 5 && System.currentTimeMillis() < end);
		 * System.out.println("Bye!");
		 */

	}

	public static void mainMenu() {
		System.out.println("");
		System.out.println("======================================");
		System.out.println("|     Please select and option:      |");
		System.out.println("======================================");
		System.out.println("| Options:                           |");
		System.out.println("|        [1] Employee                   |");
		System.out.println("|        [2] Account Holder          |");
		System.out.println("|        [3] Exit                    |");
		System.out.println("======================================");
	}

	public static void adminMenu() {
		System.out.println("");
		System.out.println("===============================================");
		System.out.println("|     Please select and option:               |");
		System.out.println("===============================================");
		System.out.println("| Options:                                    |");
		System.out.println("|        [1] Create new Employee Account         |");
		System.out.println("|        [2] Create new Customer              |");
		System.out.println("|        [3] Set up an Account for a customer |");
		System.out.println("|        [4] Update Customer Details          |");
		System.out.println("|        [5] Update Customer Account details  |");
		System.out.println("|        [6] Check Customer balance           |");
		System.out.println("|        [7] Print transaction details        |");
		System.out.println("|        [8] Add interest                     |");
		System.out.println("|        [9] Show menu                        |");
		System.out.println("|        [10] Logout                          |");
		System.out.println("===============================================");
	}

	public static void customerMenu() {
		System.out.println("========================================");
		System.out.println("|     Please select and option:        |");
		System.out.println("========================================");
		System.out.println("| Options:                             |");
		System.out.println("|        [1] Check balence             |");
		System.out.println("|        [2] Deposit                   |");
		System.out.println("|        [3] Withdraw                  |");
		System.out.println("|        [4] Print transaction History |");
		System.out.println("|        [5] Change password           |");
		System.out.println("|        [6] Change account            |");
		System.out.println("|        [7] Show menu                 |");
		System.out.println("|        [8] Logout                    |");
		System.out.println("========================================");
	}

}
