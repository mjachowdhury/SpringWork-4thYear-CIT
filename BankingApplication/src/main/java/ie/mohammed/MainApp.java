package ie.mohammed;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.model.Employee;
import ie.mohammed.model.Customer;
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
		
		String userName, pass;
        Scanner in = new Scanner(System.in);
        boolean user = false;
        boolean accountRedo = false;
         
        do {

            System.out.println("Enter Employee ID:");
            userName = in.nextLine();
            for (Employee emp : employeeService.totalEmployees()) {
                if (userName.equals(emp.getEmployeeId())) {
                    System.out.println("Password:");
                    pass = in.nextLine();
                    for (Employee em : employeeService.totalEmployees()) {
                        if (pass.equals(em.getPassword())) {

                            user = true;
                        }
                    }
                }
            }

        } while (!user);
		
        long start = System.currentTimeMillis();
        long end = start + 60 * 1000; // 60 seconds * 1000 ms/sec
        int operation = 0;
        int userChoice;

        boolean quit = false;
        
        do {
            System.out.println("Spring Bank System:");
            System.out.println("------------------------");
            System.out.println("1. Create Customer & Saving Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Balance");
            System.out.println("5. Quit");
            System.out.print("Operation count: " + operation + "(Shut down at 5)");
            userChoice = in.nextInt();
            switch (userChoice) {
            case 1:

               //create customer, then saving account regard to existing customer( maximum 2 SA per Customer)
                System.out.println("Create Customer :");
                String firstName,
                lastName,
                address,
                city,
                contactNumber,
                email;
                
                String answer;
                int accountNumber;
                double balance;
                int accountId = 0;

                Scanner sc = new Scanner(System.in);
                Customer c = new Customer();
                Account account = new Account();
                int saID = 0;

                System.out.println("Enter First Name:");
                firstName = sc.nextLine();
                c.setFirstName(firstName);
                System.out.println("Enter Last Name:");
                lastName = sc.nextLine();
                c.setLastName(lastName);
                System.out.println("Enter Address:");
                address = sc.nextLine();
                c.setAddress(address);
                System.out.println("Enter City: ");
                city = sc.nextLine();
                c.setCity(city);
                System.out.println("Enter Email:");
                email = sc.nextLine();
                c.setEmail(email);
                
                do {
                	
                	System.out.println("Crating an account, Enter account number: ");
                	accountNumber = sc.nextInt();
                	account.setAccountNumber(accountNumber);
                	System.out.println("Enter the Balance: ");
                	balance = sc.nextDouble();
                	account.setBalance(balance);
                	accountService.saveAnAccount(accountNumber, balance);
                	accountId++;
                	System.out.println("Create another account? Y?N");
                	answer = sc.nextLine();
                	if(answer.equalsIgnoreCase("n")) {
                		accountRedo = true;
                	}
                	if(accountId == 2) {
                		System.out.println("Maximum account reached!");
                	}
                }while (!accountRedo && accountId !=2);
                
                customerService.createACustomer(c);
                operation ++;
                break;
            case 2:
            	
            	//deposit
            	
            	int accNumber;
            	double amount;
            	System.out.println("Enter the Account Number you want to deposit.");
            	accNumber = sc.nextInt();
            	account.setAccountNumber(accNumber);
            	
            	
                

        
		System.out.println("There are " + customerService.CountTotalCustomer());
		System.out.println("There are " + customerService.findAllCustomer());
		
		System.out.println("There are " + employeeService.totalEmployeesCount());
		System.out.println("Total Employee Details\n"+employeeService.totalEmployees());
		
		
	}

}
