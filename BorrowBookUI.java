import java.util.Scanner;

//Documentor Checked
//Moderator Checked (Dushan)
public class BorrowBookUI {
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl controlBook; // moderator (Dushan) updated - control to controlBook	
	private Scanner inputScanner; // moderator (Dushan) updated -input to inputScanner	
	private UI_STATE stateUi; // moderator (Dushan) updated - stete -stateUi

	
	public BorrowBookUI(BorrowBookControl controlBook) {
		this.controlBook = controlBook; // moderator (Dushan) updated - control to controlBook
		inputScanner = new Scanner(System.in); // moderator (Dushan) updated -input to inputScanner
		stateUi = UI_STATE.INITIALISED; // moderator (Dushan) updated - stete -stateUi
		controlBook.setUI(this); // moderator (Dushan) updated - control to controlBook
	}

	
	private String inputBook(String promptBook) { // moderator (Dushan) updated - input to inputBook and prompt to promptBook
		System.out.print(promptBook); // moderator (Dushan) updated -prompt to promptBook
		return inputScanner.nextLine();// moderator (Dushan) updated - input to inputScanner
	}	
		
		
	private void outputBook(Object object) { // moderator (Dushan) updated - output to outputBook
		System.out.println(object);
	}
	
			
	public void setState(UI_STATE stateUi) {
		this.stateUi = stateUi; // moderator (Dushan) updated - state to stateUi
	}

	
	public void runBook() { // Updated by moderator Class name to be meaningful run to runBook
		outputBook("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (stateUi) {  // Updated by moderator variable name to be meaningful			
			
			case CANCELLED:
				outputBook("Borrowing Cancelled");  // Updated by moderator Class name to be meaningful
				return;

				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					controlBook.cancelBook();//updated by moderator BorrowBookControl method -cancelBook
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					controlBook.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					outputBook("Invalid Member Id");  // Updated by moderator Class name to be meaningful
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				controlBook.cancelBook(); //updated by moderator(Dushan) BorrowBookControl method -cancelBook
				break;
			
				
			case SCANNING:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					controlBook.completeBook(); //updated by moderator BorrowBookControl method -completeBook
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					controlBook.Scanned(bookId); 
					
				} catch (NumberFormatException e) {
					outputBook("Invalid Book Id"); // Updated by moderator Class name to be meaningful
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					controlBook.cancelBook(); // Updated by moderator(Dushan) variable name to be meaningful
					
				} else {
					controlBook.commitLoans(); // Updated by moderator variable name to be meaningful
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				outputBook("Borrowing Completed");  // Updated by moderator Class name to be meaningful
				return;
	
				
			default: 
				outputBook("Unhandled state");  // Updated by moderator Class name to be meaningful
				throw new RuntimeException("BorrowBookUI : unhandled state :" + stateUi);			
			}
		}		
	}


	public void displayBook(Object object) { //updated by moderator(Dushan) class name to camelBack and meaningful
		outputBook(object);  // Updated by moderator Class name to be meaningful		 
	}


}
