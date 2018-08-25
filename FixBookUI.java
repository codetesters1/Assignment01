import java.util.Scanner;
// checked by Moderator

public class FixBookUI { 

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl control;
	private Scanner input;
	private UI_STATE state;

	
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}


	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookString = input("Scan Book (<enter> completes): "); //Variable name should be meaningful and should not be abbreviations
				if (bookString.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = input("Fix Book? (Y/N) : "); //Variable name should be meaningful
				boolean fix = false;
				if (answer.toUpperCase().equals("Y")) {
					fix = true;
				}
				control.fixBook(fix);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
