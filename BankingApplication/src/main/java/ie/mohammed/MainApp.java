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

		int user;
		int loginOption;
		String password;

		mainMenu();
		do {
			loginOption = 0;
			user = 0;
			// userLastName = null;
			password = null;

			while (loginOption < 1 || loginOption > 3) {
				loginOption = inputValidation.getIntegerInput("\nOPTION: ");

			}

			switch (loginOption) {
			case 1: // Admin choice
				System.out.println();
				while (user < 1) {
					user = inputValidation.getIntegerInput("BANK EMPLOYEE/ADMIN ID: ");
				}
				while (password == null) {
					password = InputValidation.getPasswordInput("PASSWORD : ");
				}
				System.out.println("");

				Employee emp = employeeService.findById(user);

				if (emp.getPassword().equals(password)) {
					System.out.println("VALID LOGIN...");

					System.out.println();
					System.out.println("Hello.., " + emp.getFirstName() + " " + emp.getLastName());

					adminMenu();

					boolean ext = true;
					do {
						int userOption = 0;
						while (userOption < 1 || userOption > 12) {
							userOption = inputValidation.getIntegerInput("\nOPTION: ");
						}

						switch (userOption) {
						case 1: // Create Bank Employee account
							System.out.println(".. CREATE BANK EMPLYOEE.. ");
							System.out.println("-------------------------\n");
							String firstName = null;
							String lastName = null;
							String choosePassword = null;

							while (firstName == null) {
								firstName = InputValidation.getStringInput("PLEASE ENTER THE FIRST NAME: ");
							}
							while (lastName == null) {
								lastName = InputValidation.getStringInput("PLEASE ENTER THE LAST NAME: ");
							}
							while (choosePassword == null) {
								choosePassword = InputValidation.getPasswordInput("PLESAE ENTER THE PASSWORD: ");
							}

							employeeService.saveANewEmployee(firstName, lastName, choosePassword);
							System.out.println("\nNEW BANK EMPLOYEE HAS BEEN CREATED.");
							break;

						case 2: // Create Customer account/register with the bank
							System.out.println("..REGISTER WITH THE BANK..");
							System.out.println("--------------------------\n");
							String title = null;
							int titleNum = 0; // Used to select the user title
							String firstNameC = null;
							String lastNameC = null;
							String address = null;
							String city = null;
							String contactNumber = null;
							String email = null;
							String cusPassword = null;

							System.out.println("[1] Mr");
							System.out.println("[2] Mrs");
							System.out.println("[3] Ms");
							System.out.println("[4] Miss");

							while (titleNum < 1 || titleNum > 4) {
								titleNum = inputValidation.getIntegerInput("SELECT TITLE : ");
							}

							if (titleNum == 1) {
								title = "Mr";
							}
							if (titleNum == 2) {
								title = "Mrs";
							}
							if (titleNum == 3) {
								title = "Ms";
							}
							if (titleNum == 4) {
								title = "Miss";
							}

							while (firstNameC == null) {
								firstNameC = InputValidation.getStringInput("PLEASE ENTER CUSTOMER FIRST NAME: ");
							}
							while (lastNameC == null) {
								lastNameC = InputValidation.getStringInput("PLEASE ENTER CUSTOMER LAST NAME: ");
							}
							while (address == null) {
								address = InputValidation.getStringInput("PLEASE ENTER ADDRESS: ");
							}
							while (city == null) {
								city = InputValidation.getStringInput("PLEASE ENTER CITY: ");
							}
							System.out.println("PLEASE ENTER CONTACT NUMBER: ");
							contactNumber = scanner.nextLine();

							System.out.println("PLEASE ENTER EMAIL:");
							email = scanner.nextLine();

							while (cusPassword == null) {
								cusPassword = InputValidation.getPasswordInput("PLEASE CHOOSE PASSWORD: ");
							}
							customerService.saveACustomer(title, firstNameC, lastNameC, address, city, contactNumber,
									email, cusPassword);
							System.out.println("\nNEW CUSTOMER HAS BEEN REGISTERED WITH THE BANK. ");
							break;

						case 3: // setting up a customer account
							System.out.println("..SETTING UP CUSTOMOER ACCOUNT..");
							System.out.println("-------------------------------\n");
							int customerId = 0;
							int accountNumber = 0;
							double overDraft = -1;
							double amount = -1;

							while (accountNumber < 1) {
								accountNumber = inputValidation.getIntegerInput("ENTER ACCOUNT NUMBER: ");
							}
							while (customerId < 1) {
								customerId = inputValidation.getIntegerInput("ENTER CUSTOMER ID: ");
							}
							while (amount < 0) {
								amount = inputValidation
										.getDoubleInput("PLEASE SET THE DEFAULT BALANCE ON THE ACCOUNT: ");
							}
							while (overDraft < 0) {
								overDraft = inputValidation
										.getDoubleInput("HOW MUCH OF AN OVERDRAFT IS THIS ACCOUNT GOING TO HAVE? :  ");
							}

							accountService.saveAnAccount(accountNumber, customerId, amount, overDraft);
							System.out.println("\nNEW CUSTOMER ACCOUNT HAS BEEN CREATED");
							break;

						case 4: // list of account associate with customer
							System.out.println("ENTER THE CUSTOMER ID :");
							int cusId = scanner.nextInt();
							List<Account> accountWithCustomer = accountService.findAccountWithCustomer(cusId);
							for (Account acc : accountWithCustomer) {
								if (acc.getAccountId() == acc.getCustomerId())
									System.out.println(acc.toString());
							}
							// accountService.findAccountWithCustomer(user);
							break;

						case 5:
							// account with more than 10000 euro
							System.out.println("LIST OF ACCOUNT(S) HAS MORE THAN 10K..");
							System.out.println("--------------------------------------");
							System.out.println(accountService.findAccountsGreaterThan10000());
							break;
						
						case 6:
							// to see list of bank employee
							// employeeService.
							System.out.println("..ALL THE EMPLOYEES DETAILS..");
							System.out.println("------------------------------");
							List<Employee> employee = employeeService.totalEmployees();
							for (Employee empl : employee)
								System.out.println(empl);
							break;

						case 7:
							// to see list of customer							 
							System.out.println("..ALL THE CUSTOMERS DETAILS..");
							System.out.println("------------------------------");
							List<Customer> customer = customerService.findAllCustomer();
							for (Customer cum : customer)
								System.out.println(cum);
							break;

						case 8:

							// to see all customer account details							 
							System.out.println("..ALL THE ACCOUNTS DETAILS..");
							System.out.println("-----------------------------");
							List<Account> account = accountService.findAllAccount();
							for (Account acc : account)
								System.out.println(acc);
							break;

						case 9:// check customer balance
							System.out.println("..CUSTOMER ACCOUNT DETAILS..");
							System.out.println("-----------------------------");
							int accountNumberCheckBalance = 0;
							System.out.println("Please enter the customer account number: ");
							while (accountNumberCheckBalance < 1) {
								accountNumberCheckBalance = inputValidation.getIntegerInput("Account number: ");
							}
							accountService.displayAccountDetails(accountNumberCheckBalance);

							break;
							
						case 10:// show total amount
							System.out.println("..TOTAL AMOUNT CURRENTLY IN THE BANK..");
							System.out.println("---------------------------------------");
							System.out.println("TOTAL AMOUNT : " + accountService.totalAmonut());
							break;

						case 11: // show menu
							adminMenu();
							break;

						case 12: // login out of the admin account
							mainMenu();// print the main menu

							ext = false;
							break;
							
						default:
							System.out.println("Invalid Selection..");
							break;
						}
					} while (ext);
				} // end of if
				else {
					System.out.println("Invalid");
				}
				break;
				
			case 2:// Account holder

				System.out.println("");
				while (user < 1) {
					user = inputValidation.getIntegerInput("ENTER CUSTOMER  ID:  ");
				}
				while (password == null) {
					password = InputValidation.getPasswordInput("PASSWORD: ");
				}

				System.out.println("");

				Customer cus = customerService.findById(user);

				if (cus.getPassword().equals(password)) {
					System.out.println("VALID LOGIN...");

					System.out.println();
					System.out.println("Hello, " + cus.getFirstName() + " " + cus.getLastName());

					customerMenu();// custoemr menu

					boolean ext = true;

					do {
						int userOption = 0;
						while (userOption < 1 || userOption > 8) {
							userOption = inputValidation.getIntegerInput("\nOPTION: ");
						} // end of while

						switch (userOption) {
						
						case 1: // deposit
							System.out.println("DEPOSIT SERVICE ...");
							double amount = 0;
							int accountNumber = 0;
							System.out.println("ENTER ACCOUNT NUMBER :");
							accountNumber = scanner.nextInt();
							System.out.println("ENTER AMOUNT : ");
							amount = scanner.nextDouble();
							accountService.depositMoney(accountNumber, amount);
							System.out.println("YOUR ACCOUNT HAS BEEN UPDATED AND TOTAL AMOUNT NOW:"
									+ accountService.findByAccountNumber(accountNumber));
							break;

						case 2: // withdraw
							System.out.println("WITHDRAW SERVICE...");
							double amountSubstract = 0;
							int accountNum = 0;
							System.out.println("ENTER ACCOUNT NUMBER :");
							accountNum = scanner.nextInt();
							System.out.println("ENTER AMOUNT: ");
							amountSubstract = scanner.nextDouble();
							// System.out.println("Enter overDraft:");
							accountService.withdrawMoney(accountNum, amountSubstract);
							System.out.println("YOUR ACCOUNT HAS BEEN UPDATED AND TOTAL AMOUNT NOW:"
									+ accountService.findByAccountNumber(accountNum));

							break;

						case 3: // transfer money to another account
							System.out.println("MONEY TRANSFER SERVICE ....");
							System.out.println("ENTER OWN ACCOUNT ID :");
							int ownAccountID = scanner.nextInt();
							System.out.println("ENTER TRANSFER ACCOUNT ID :");
							int transferAccountId = scanner.nextInt();
							System.out.println("HOW MUCH YOU WANT TO TRANSFER: ");
							double totalAmount = scanner.nextDouble();
							accountService.TranferMoneyToAnotherAccount(ownAccountID, transferAccountId, totalAmount);
							System.out.println("YOUR ACCOUNT HAS BEEN UPDATED AND TOTAL AMOUNT NOW:"
									+ accountService.findByAccountID(ownAccountID));

							break;

						case 4:	// close and account
							System.out.println("ENTER THE ACCOUNT ID : ");
							int accountID = scanner.nextInt();

							if (user == accountID) {
								accountService.closeAnAccount(user);
								System.out.println("YOUR ACCOUNT IS NO LONGER AVAILABLE..");
								System.out.println("GOOD BYE..........");
								System.out.println("YOU ARE RE-DIRECTED TO MAIN MENU....");
								mainMenu();
								ext = false;
								break;
							} else {
								System.out.println("Account ID DID NOT MATCH...TRY AGAIN");
								customerMenu();
							}

							break;

						case 5:// check balance

							System.out.println("ENTER ACCOUNT ID : ");
							int checkBal = scanner.nextInt();
							if (user != checkBal) {
								System.out.println("WRONG ACCOUNT ID ..");
							} else {
								accountService.displayAccountDetailsById(user);
							}
							break;

						case 6:	// Show menu
							customerMenu();
							break;
						
						case 7:
							System.out.println("Under implementation...");
							/*
							 * System.out.println("..ADD A CUSTOMER TO AN ACCOUNT(JOIN ACCOUNT) ..");
							 * System.out.println("-----------------------------------------------\n");
							 * System.out.println("ENTER CUSTOMER ID :"); int cusIdToJoin =
							 * scanner.nextInt(); System.out.println("ENTER ACCOUNT ID : "); int accIdToJoin
							 * = scanner.nextInt();
							 * 
							 * accountService.addAPersonToAccount(cusIdToJoin, accIdToJoin);
							 * System.out.println("ACCOUNT IS JOIN NOW..");
							 */
							break;
							
						case 8:	// Login out
							mainMenu(); // Prints the main menu

							ext = false;
							break;

						default:
							
							System.err.println("INVALID SELECTION...");
							break;
							
						} // end of switch
					} while (ext);// end of customer do

				} // end of if

				else {
					System.err.println("Invalid");
				}
				break;
				
			case 3:
				String response = "";
				while (!response.toLowerCase().equals("n") && !response.toLowerCase().equals("y")) {
					response = InputValidation.getPasswordInput("ARE YOU YOU WANT TO EXIT? (Y/N)");
				}
				if (response.toLowerCase().equals("n")) {
					System.out.println("THANKS FOR STAYING.. :)");
				}
				if (response.toLowerCase().equals("y")) {
					System.exit(0);
				}
				break;
				
			default:
				System.err.println("Invalid selection");
				break;
			}// end of first switch

		} while (loginOption != 4);// end of first do loop

		context.close();
	}

	//Main Menu
	public static void mainMenu() {
		System.out.println("");
		System.out.println("============================================");
		System.out.println("|  WEL COME TO SPRING BANK APPLICATION     |");
		System.out.println("|        PLEASE SELECT AN OPTION           |");
		System.out.println("============================================");
		System.out.println("| OPTION :                                 |");
		System.out.println("|         [1] BANK EMPLOYEE LOGIN          |");
		System.out.println("|         [2] CUSTOMER LOGIN               |");
		System.out.println("|         [3] EXIT                         |");
		System.out.println("===========================================|");
	}
	
	//Admin Menu
	public static void adminMenu() {
		System.out.println("");
		System.out.println("====================================================================");
		System.out.println("|                 PLEASE SELECT AN OPTION                          |");
		System.out.println("====================================================================");
		System.out.println("| OPTION :                                                         |");
		System.out.println("|         [1] CREATE NEW BANK EMPLOYEE                             |");
		System.out.println("|         [2] CREATE NEW BANK CUSTOMER                             |");
		System.out.println("|         [3] SET UP AN ACCOUNT FOR A CUSTOMER                     |");
		System.out.println("|         [4] LIST OF ACCOUNT ASSOCIATED WITH CUSTOMER             |");
		System.out.println("|         [5] ACCOUNT WITH GREATER THAN 10,000 EUROS               |");
		System.out.println("|                                                                  |");
		System.out.println("|         [6] DETAILS OF EMPLOYEE                                  |");
		System.out.println("|         [7] DETAILS OF CUSTOMER                                  |");
		System.out.println("|         [8] DETAILS OF ACCOUNT                                   |");
		System.out.println("|                                                                  |");
		System.out.println("|         [9] PRINT TRANSACTION DETAILS                            |");
		System.out.println("|         [10]SHOW TOTAL AMOUNT                                    |");
		System.out.println("|         [11]SHOW MENU                                            |");
		System.out.println("|         [12]LOGOUT                                               |");
		System.out.println("====================================================================");
	}
	
	//Customer Menu
	public static void customerMenu() {
		System.out.println("=====================================================");
		System.out.println("|          PLEASE SELECT AN OPTION                  |");
		System.out.println("=====================================================");
		System.out.println("| OPTION :                                          |");
		System.out.println("|         [1] DEPOSIT                               |");
		System.out.println("|         [2] WITHDRAW                              |");
		System.out.println("|         [3] TRANSFER MONEY TO ANOTHER ACCOUNT     |");
		System.out.println("|         [4] CLOSE ACCOUNT                         |");
		System.out.println("|                                                   |");
		System.out.println("|         [5] CHECK BALANCE                         |");
		System.out.println("|         [6] SHOW CUSTOMER MENU                    |");
		System.out.println("|         [7] ADD PERSON TO AN ACCOUNT              |");
		System.out.println("|         [8] LOGOUT                                |");
		System.out.println("|===================================================|");

	}

}
