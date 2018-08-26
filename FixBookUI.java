import java.util.Scanner;
// Author by Waqas
// checked by Moderator
//Updated by Reviewer-Dushan
//Checked by Documenter - Bhanuka

public class FixBookUI { 

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl controlBook;//updated by reviewer - control to controlBook -must be meaningful
	private Scanner inputScanner; //commented by Author (WAQAS) version 4.0 final vairable update - updated by reviewer Dushan- input to inputScanner
	private UI_STATE stateUi;//updated by reviewer Dushan- state to stateUi

	
	public FixBookUI(FixBookControl controlBook) { //updated by reviewer - control to controlBook -must be meaningful
		this.controlBook = controlBook; //updated by reviewer - control to controlBook -must be meaningful
		inputScanner = new Scanner(System.in); //updated by reviewer Dushan- input to inputScanner
		stateUi = UI_STATE.INITIALISED; //updated by reviewer Dushan- state to stateUi
		controlBook.setUI(this);//updated by reviewer - control to controlBook -must be meaningful
	}


	public void setState(UI_STATE stateUi) {
		this.stateUi = stateUi;//updated by reviewer Dushan- state to stateUi
	}

	
	public void runFixBook() { //updated by reviewer Dushan- method name must be meaningful and camelBack style
		outputFixBook("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (stateUi) { //updated by reviewer Dushan- state to stateUi
			
			case READY:
				String bookString = inputScanner("Scan Book (<enter> completes): "); //Variable name should be meaningful and should not be abbreviations
				if (bookString.length() == 0) {
					controlBook.scanningComplete(); //updated by reviewer Dushan - version 4.0 final update to variables
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();
						controlBook.bookScanned(bookId); //updated by reviewer Dushan
					}
					catch (NumberFormatException e) {
						outputFixBook("Invalid bookId"); //updated by reviewer Dushan
					}
				}
				break;	
				
			case FIXING:
				String answer = inputScanner("Fix Book? (Y/N) : "); //version 4.0 final update Variable name should be meaningful
					//updated by reviewer Dushan
				boolean fix = false;
				if (answer.toUpperCase().equals("Y")) {
					fix = true;
				}
				controlBook.fixBook(fix);
				break;
								
			case COMPLETED:
				outputFixBook("Fixing process complete");
				return;
			
			default:
				outputFixBook("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}

	
	private String inputFixBook(String prompt) {
		System.out.print(prompt);
		return inputScanner.nextLine();
	}	
		
		
	private void outputFixBook(Object object) {
		System.out.println(object);
	}
	

	public void displayFixBook(Object object) {
		outputFixBook(object);
	}
	
	
}
