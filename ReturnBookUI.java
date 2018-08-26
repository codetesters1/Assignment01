import java.util.Scanner;

// Author - Bhanuka
// checked by Documentor
//Moderator done by Dushan
public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl controlBook;
	private Scanner inputScanner;//updated by Reviewer input to inputScanner
	private UI_STATE state;

	
	public ReturnBookUI(ReturnBookControl controlBook) {
		this.controlBook = controlBook;//updated by Reviewer book to controlBook
		inputScanner = new Scanner(System.in);//updated by Reviewer input to inputScanner
		state = UI_STATE.INITIALISED;
		controlBook.setUI(this);
	}


	public void runBook() {	//updated by moderator Dushan - run to runBook method	
		outputBook("Return Book Use Case UI\n"); //updated by Moderator output to outputBook
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = inputScanner("Scan Book (<enter> completes): ");//Moderator- input to inputScanner
				if (bookStr.getLength() == 0) {
					//"bookStr.getLength()" rater than "bookStr.length()"
					//the method name must be verb and useCamelBack style 
					control.setScanningComplete();
					//"control.setScanningComplete()" rather than "control.scanningComplete()"
					//the method name must be verb or verb phrase and useCamelBack style 
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.setBookScanned(bookId);
						//"control.setBookScanned(bookId)" rather than "control.bookScanned(bookId)"
					//the method name must be verb or verb phrase and useCamelBack style 
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				outputBook("Return processing complete"); //updated by Moderator output to outputBook
				return;
			
			default:
				outputBook("Unhandled state"); //updated by Moderator output to outputBook
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String inputBook(String prompt) { //updated by Moderator input to inputBook
		System.out.print(prompt);
		return inputScanner.nextLine();
	}	
		
		
	private void outputBook(Object object) { //updated by Moderator output to outputBook
		System.out.println(object);
	}
	
			
	public void displayBook(Object object) {//updated by Moderator display to displayBook
		outputBook(object);//updated by Moderator output to outputBook
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}
