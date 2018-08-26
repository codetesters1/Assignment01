import java.text.SimpleDateFormat;
import java.util.Scanner;

// checked by Moderator
//Updated by reviewer-Dushan
//Checked by Facilitatorr - Bhanuka

public class Main {
	
	private static Scanner inputScanner; //Changed the variable name from Input to input
	//updated by reviewer Dushan- input to inputScanner
	private static Library libraryMain;//Changed the variable name from Lib to lib
	//updated by reviewer Dushan- Class name library to Library and variable from library to libraryMain
	private static String mainMenu;//Changed variable neme from MENU to menu
	//updated by reviewer Dushan- menu to mainMenu
	private static Calendar calendarMain; //changed the variable name CAL to calendar
	//updated by reviewer Dushan- calender to calenderMain
	private static SimpleDateFormat simpleDataFormat; //changed the variable name SDF to simpleDataFormat
	
	
	private static String GetMenu() { //changed class name from 'Get_menu' to GetMenu
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
			inputScanner = new Scanner(System.in);//Changed the variable name from input to input
			libraryMain = library.INSTANCE();//Changed the variable name from lib to lib
			calendarMain = Calendar.getInstance(); //changed the variable name CAL to calendar
			simpleDataFormat = new SimpleDateFormat("dd/MM/yyyy");//changed variable name
	
			for (Member member : libraryMain.Members()) {//Changed the variable name from lib to lib and m to member
				//updated by reviewer Dushan- class name member to Member
				output(member);
			}
			output(" ");
			for (Book book : lib.Books()) {//Changed the variable name from lib to lib and b to book
				////updated by reviewer Dushan- class name book to Book
				outputMain(book); ////updated by reviewer Dushan- output method to outputMain
			}
						
			menu = GetMenu(); //Changed variable neme from MENU to menu and changed class name
			
			boolean exitMenu = false; //changed e to exitMenu
			
			while (!exitMenu) { //changed variable name
				

				outputMain("\n" + simpleDataFormat.format(calendar.Date()));//changed the variable name CAL to calendar
				//updated by reviewer Dushan- output method to outputMain
				String choice = inputMain(menu);//variable name change	
				//updated by reviewer Dushan- input method to inputMain
				switch (choice.toUpperCase()) { //changed variable name c to choice
=======
				outputMain("\n" + simpleDataFormat.format(calendar.Date()));//replaced the variable name CAL to calendar
				//updated by reviewer Dushan- output method to outputMain
				String c = inputMain(MENU);
				//updated by reviewer Dushan- input method to inputMain
				
				switch (choice.toUpperCase()) {
				
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
					exitMenu = true; //changed variable name
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				libraryMain.SAVE();
			}			
		} catch (RuntimeException e) {
			outputMain(e);//updated by reviewer Dushan- output method to outputMain
		}		
		outputMain("\nEnded\n"); //updated by reviewer Dushan- output method to outputMain
	}	

	
	private static void payFine() { 
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		outputMain(""); //updated by reviewer Dushan- output method to outputMain
		for (Loan loan : lib.CurrentLoans()) {
			//Updated by reviewer Dushan- loan to Loan-Class Name
			outputMain(loan + "\n"); //updated by reviewer Dushan- output method to outputMain
		}		
	}


	private static void ListBook() { //changed variable name 

		outputMain(""); //updated by reviewer Dushan- output method to outputMain
		for (Book book : lib.Books()) {//Changed the variable name from lib to lib
			////updated by reviewer Dushan- class book to Book
			outputMain(book + "\n"); //updated by reviewer Dushan- output method to outputMain
		}		
	}



	private static void listMembers() {
		outputMain(""); //updated by reviewer Dushan- output method to outputMain
		for (Member member : lib.Members()) {//Changed the variable name from lib to lib
			//updated by reviewer member class to Member
			outputMain(member + "\n"); //updated by reviewer Dushan- output method to outputMain
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void feturnBook() {
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
			output(simpleDataFormat.format(calendar.Date()));//changed the variable name CAL to calendar and SDF to simpleDataFormat
			
		} catch (NumberFormatException e) {
			 outputMain("\nInvalid number of days\n"); //updated by reviewer Dushan- output method to outputMain
		}
	}


	private static void addBook() {
		
		String author = inputMain("Enter author: ");//updated by reviewer Dushan- input method to inputMain
		String title  = inputMain("Enter title: ");//updated by reviewer Dushan- input method to inputMain
		String callNo = inputMain("Enter call number: ");//updated by reviewer Dushan- input method to inputMain
		Book book = lib.Add_book(author, title, callNo);//Changed the variable name from lib to lib
		//udpated by reviewer Dushan- book class to Book
		outputMain("\n" + book + "\n");//updated by reviewer Dushan- input method to inputMain
		
	}

	
	private static void addMember() {
		try {
			String lastName = inputMain("Enter last name: ");//updated by reviewer Dushan- input method to inputMain
			String firstName  = inputMain("Enter first name: ");//updated by reviewer Dushan- input method to inputMain
			String email = inputMain("Enter email: ");//updated by reviewer Dushan- input method to inputMain
			int phoneNo = Integer.valueOf(inputMain("Enter phone number: ")).intValue();//updated by reviewer Dushan- input method to inputMain
			Member member = lib.Add_mem(lastName, firstName, email, phoneNo);//Changed the variable name from lib to lib
			//udpated by reviewer Dushan- member class to Member
			outputMain("\n" + member + "\n");//updated by reviewer Dushan- output method to outputMain
			
		} catch (NumberFormatException e) {
			 outputMain("\nInvalid phone number\n");//updated by reviewer Dushan- output method to outputMain
		}
		
	}


	private static String inputMain(String prompt) {
		System.out.print(prompt);
		return inputScanner.nextLine();//Changed the variable name from input to input
	}
	
	
	
	private static void outputMain(Object object) {
		System.out.println(object);
	}

	
}
