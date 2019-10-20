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
		
		Scanner scanner = new Scanner(System.in);
		//boolean user = false;
		boolean accountRedo = false;
		int choice;
		String employeeLastName;
		int employeeId;
		
		int user;
		int loginOption;
		String userLastName;
		
		mainMenu();
		do 
		{
			loginOption = 0;
			user = 0;
			userLastName = null;
			
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
					user = inputValidation.getIntegerInput("Bank Employee/Admin ID: ");
				}
				while(userLastName == null)
				{
					userLastName = InputValidation.getPasswordInput("Last Name: ");
				}
				System.out.println("");
				
				Employee emp = employeeService.findById(user);
				if (emp==null)
					System.out.println("ERROR - ??????????????????/");
				if (emp.getLastName().equals(userLastName)) 
				{
					System.out.println("Valid Login");
					
					System.out.println();
					System.out.println("Hello, " + emp.getFirstName() + " " + emp.getLastName());
					
					adminMenu();
					
					boolean ext = true;
					do 
					{
						int userOption = 0;
						while (userOption < 1 || userOption > 9)
						{
							userOption = inputValidation.getIntegerInput("Option: ");
						}
						
						switch(userOption)
						{
						case 1: //Create Bank Employee account
							String firstName = null;
		        	 		String lastName = null;
		        	 		String password = null;
		        	 		
	        	 			while(firstName == null){
	        	 				firstName = InputValidation.getStringInput("Please enter the first name: ");
	        	 			}
	        	 			while(lastName == null){
	        	 				lastName = InputValidation.getStringInput("Please enter the last name: ");
	        	 			}
	        	 			while(password == null){
	        	 				password = InputValidation.getPasswordInput("Please enter a password: ");
	        	 			}
	        	 			
	        	 			employeeService.saveANewEmployee(firstName, lastName, password);
	        	 			break;
	        	 			
						case 2: //Create Customer account/register with the bank
		        	 		String title = null;
		        	 		int titleNum = 0; //Used to select the user title
		        	 		String firstNameC = null;
		        	 		String lastNameC = null;
		        	 		String address = null;
		        	 		String city = null;
		        	 		String contactNumber = null;
		        	 		String email = null;

	        	 			System.out.println("[1] Mr");
		        	 		System.out.println("[2] Mrs");
		        	 		System.out.println("[3] Ms");
		        	 		System.out.println("[4] Miss");				        	 		
		        	 		
		        	 		while (titleNum < 1 || titleNum > 4){
		        	 			titleNum = inputValidation.getIntegerInput("Title: ");
				 	        }
		        	 		
		        	 		if(titleNum == 1){
		        	 			title = "Mr";
		        	 		}
		        	 		if(titleNum == 2){
		        	 			title = "Mrs";
		        	 		}
		        	 		if(titleNum == 3){
		        	 			title = "Ms";
		        	 		}
		        	 		if(titleNum == 4){
		        	 			title = "Miss";
		        	 		}
		        	 		
		        	 		while(firstNameC == null){
		        	 			firstNameC = InputValidation.getStringInput("Please enter the customers first name: ");
	        	 			}
		        	 		while(lastNameC == null){
		        	 			lastNameC = InputValidation.getStringInput("Please enter the customers last name: ");
	        	 			}
		        	 		while(address == null){
		        	 			address = InputValidation.getPasswordInput("Please enter a Address: ");
	        	 			}
		        	 		while(city == null){
		        	 			city = InputValidation.getPasswordInput("Please enter a City: ");
	        	 			}
		        	 		while(contactNumber == null){
		        	 			contactNumber = InputValidation.getPasswordInput("Please enter a Contact Number: ");
	        	 			}
		        	 		while(email == null){
		        	 			email = InputValidation.getPasswordInput("Please enter a Email: ");
	        	 			}
		        	 		customerService.saveACustomer(title, firstNameC, lastNameC, address, city, contactNumber, email);
		        	 		         	 		
		        	 		break;
		        	 	
						case 3: // setting up a customer account
							//int customerID =0;
							int accountNumber = 0;
							double overDraft = -1;
							double amount = -1;
							
							while (accountNumber < 1){
								accountNumber = inputValidation.getIntegerInput("Account Number: ");
				 	        }
							while (amount < 0){
								amount = inputValidation.getDoubleInput("Please set the default balance on the account: ");
				 	        }
							while (overDraft < 0){
								overDraft = inputValidation.getDoubleInput("How much of an overdraft is this account going to have? ");
				 	        }
							
							accountService.saveAnAccount(accountNumber, amount, overDraft);
							break;
							
						case 4: //to see list of bank employee
							//employeeService.
							System.out.println("Employee Details\n");
							List<Employee> employee = employeeService.totalEmployees();
							for (Employee empl : employee)
								System.out.println(empl);
							break;
						case 5: //to see list of customer
							//customerService
							System.out.println("Customer Details\n");
							List<Customer> customer = customerService.findAllCustomer();
							for (Customer cum : customer)
								System.out.println(cum);
							break;
						case 6: //to see all customer account details
							//accountService.displayAccountDetails();
							System.out.println("Customer Details\n");
							List<Account> account = accountService.findAllAccount();
							for (Account acc : account)
								System.out.println(acc);
							break;
						case 7://check customer balance
							int accountNumberCheckBalance = 0;
							System.out.println("Please enter the customer account number: ");
							while (accountNumberCheckBalance < 1){
		        	 			accountNumberCheckBalance = inputValidation.getIntegerInput("Account number: ");
				 	        } 
							accountService.displayAccountDetails(accountNumberCheckBalance);
						
							break;
						case 8: //show menu
							adminMenu();
							break;
							
						case 9: //login out of the admin account
							mainMenu();//print the main menu
							
							ext = false;
							break;
						default:
							System.out.println("Invalid Selection");
							break;
						}
					}while(ext);
				}//end of if 
				else {
					System.out.println("Invalid");
				}
				
			break;
			case 2://Account holder
				
				System.out.println("");
				while (user < 1){
	        		user = inputValidation.getIntegerInput("Customer  ID:  ");
	 	        }
				while(userLastName == null){
					userLastName = InputValidation.getPasswordInput("Last Name: ");
	 			}
				
				System.out.println("");
				
				Customer cus = customerService.findById(user);
				if (cus==null)
					System.out.println("ERROR - ??????????????????/");
				if (cus.getLastName().equals(userLastName)) 
				{
					System.out.println("Valid Login");
					
					System.out.println();
					System.out.println("Hello, " + cus.getFirstName() + " " + cus.getLastName());
						
						customerMenu();//custoemr menu
						
						boolean ext =  true;
						
						
						
						do
						{
							int userOption = 0;
				        	 while (userOption < 1 || userOption > 7)
				        	 {
				        		 userOption = inputValidation.getIntegerInput("Option: ");
					 	     }//end of while
				        	 
				        	 switch (userOption)
				        	 {
				        	 case 1: //check balance
				        		
				        		 System.out.println("Enter the account ID : ");
				        		 Account account = accountService.findByAccountID(user);
				        		 while(user < 0) {
				        			 user = inputValidation.getIntegerInput("Account ID : ");				        			
				        		 }
				        		 if(user == account.getAccountId()) {
				        			 accountService.displayAccountDetailsById(user);
				        		 }
				        		
				        		 break;
				        		 
				        	 case 2 : //deposit
				        		 double amount = 0;
				        		 System.out.println("Enter the account ID : ");
				        		 Account accountId = accountService.findByAccountID(user);
				        		 while(user < 0) {
				        			 user = inputValidation.getIntegerInput("Account ID : ");				        			
				        		 }
				        		 if(user == accountId.getAccountId()) {
				        			 //accountService.displayAccountDetailsById(user);
				        			 System.out.println("How much would like to Depost: ");
										while (amount < 0) {
											amount = inputValidation.getDoubleInput("Amount : ");
										}
										accountService.depositMoneyById(user, amount);
				        		 }
							
							/*
							 * int accountNumber = 0; double amount = 0;
							 * 
							 * System.out.println("Enter the account number : "); Account accountNum =
							 * accountService.findByAccountNumber(accountNumber); while (accountNumber < 0)
							 * { accountNumber = inputValidation.getIntegerInput("Account Number : "); } if
							 * (accountNumber == accountNum.getAccountNumber()) {
							 * System.out.println("How much would like to Depost: "); while (amount < 0) {
							 * amount = inputValidation.getDoubleInput("Amount : "); }
							 * accountService.depositMoney(accountNumber, amount);
							 * accountService.displayAccountDetails(accountNumber); }
							 */
							 
				        		 
				        		 break;
				        		 
				        	 case 3: //withdraw
				        		
							/*
							 * System.out.println("Enter the account number : "); while(accountNumber < 0) {
							 * accountNumber = inputValidation.getIntegerInput("Account Number : "); }
							 * 
							 * System.out.println("How much would like to Withdraw: "); while(amount < 0) {
							 * amount = inputValidation.getDoubleInput("Amount : "); }
							 * accountService.depositMoney(accountNumber, amount);
							 * accountService.displayAccountDetails(accountNumber);
							 */
				        		 break;
				         
				        	 	case 7: //Show menu
				        	 		customerMenu();
				        	 		break;
				        	 	case 8: //Login out
				        	 		mainMenu(); //Prints the main menu
				        			
				        	 		ext = false;
				        	 		break;
				        	 	default:
							    	System.err.println("Invalid selection");
							    	break; 
				        	 } //end of switch
						}while(ext);//end of customer do
						
					}//end of if
				
				
						/*
						 * else{ System.out.
						 * println("Sorry, to use this part of the system you need an account assiosated with you."
						 * ); System.out.println("Please conact a bank manager for more assistance.");
						 * mainMenu(); //Prints the main menu
						 */				  //}//end of else 
				//}//end of if
				 	else{
			         System.err.println("Invalid");
				}
		break;
			case 3:
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
			}//end of first switch
				
		}while(loginOption !=4);//end of first do loop
		
//===========================================		

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
							System.out.println("Enter Title:");
							String title = scanner.nextLine();
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

							customerService.saveACustomer(title,firstName, lastName, address, city, contactNumber, email);

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
		System.out.println("|        [1] Bank Employee              |");
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
		System.out.println("|        [4] Details of Employee              |");
		System.out.println("|        [5] Details of Customer              |");
		System.out.println("|        [6] Details of Account               |");
		System.out.println("|        [7] Print transaction details        |");
		System.out.println("|        [8] Show menu                        |");
		System.out.println("|        [9] Logout                           |");
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
		System.out.println("|        [5] Close account             |");
		System.out.println("|        [6] Show menu                 |");
		System.out.println("|        [7] Logout                    |");
		System.out.println("========================================");
	}

}

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