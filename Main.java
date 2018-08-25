import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input; //Changed the variable name from Input to input
	private static library lib;//Changed the variable name from Lib to lib
	private static String menu;//Changed variable neme from MENU to menu
	private static Calendar calendar; //changed the variable name CAL to calendar
	private static SimpleDateFormat SDF; 
	
	
	private static String Get_menu() {
		StringBuilder strBuilder = new StringBuilder(); //changed variable 'sb' to 'strBuilder'
		
		strBuilder.append("\nLibrary Main Menu\n\n") //changed variable 'sb' to 'strBuilder'
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return strBuilder.toString(); //changed variable 'sb' to 'strBuilder'
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in);//Changed the variable name from input to input
			lib = library.INSTANCE();//Changed the variable name from lib to lib
			calendar = Calendar.getInstance(); //changed the variable name CAL to calendar
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : lib.Members()) {//Changed the variable name from lib to lib
				output(m);
			}
			output(" ");
			for (book b : lib.Books()) {//Changed the variable name from lib to lib
				output(b);
			}
						
			menu = Get_menu(); //Changed variable neme from MENU to menu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(calendar.Date()));//changed the variable name CAL to calendar
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (loan loan : lib.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : lib.Books()) {//Changed the variable name from lib to lib
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : lib.Members()) {//Changed the variable name from lib to lib
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);//changed the variable name CAL to calendar
			lib.checkCurrentLoans();//Changed the variable name from lib to lib
			output(SDF.format(calendar.Date()));//changed the variable name CAL to calendar
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		book book = lib.Add_book(author, title, callNo);//Changed the variable name from lib to lib
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = lib.Add_mem(lastName, firstName, email, phoneNo);//Changed the variable name from lib to lib
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();//Changed the variable name from input to input
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
