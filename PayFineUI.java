import java.util.Scanner;


public class PayFineUI {


	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl payControl;// use meaningful name ,camelBack so "PayFineControl payControl;" not "PayFineControl control;"
	private Scanner inputScanner;//version 1.0 changed variable name from 'input' to 'inputScanner'
	private UI_STATE state;

	
	public PayFineUI(PayFineControl payControl) {// use meaningful name ,camelBack so "PayFineControl payControl;" not "PayFineControl control;"
		this.payControl = payControl;
		inputScanner = new Scanner(System.in);//version 1.0 changed variable name from 'input' to 'inputScanner' as a parameter
		state = UI_STATE.INITIALISED;
		payControl.setUI(this);
	}
	
	
	public void setState(UI_STATE state) {
		this.state = state;
	}


	public void runDisplay() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String memStr = inputScanner("Swipe member card (press <enter> to cancel): "); // Variable name must be meaningful - camelBack style
				if (memStr.length() == 0) {
					payControl.cancel(); // Variable name must be meaningful - camelBack style
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					payControl.cardSwiped(memberId); // Variable name must be meaningful - camelBack style
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double payAmount = 0; // variable name : "payAmount;" not "amount;" - all uppercase is used for constants only
				String amtStr = inputScanner("Enter amount (<Enter> cancels) : "); // Variable name must be meaningful - camelBack style
				if (amtStr.length() == 0) {
					payControl.cancel(); // Variable name must be meaningful - camelBack style
					break;
				}
				try {
					payAmount = Double.valueOf(amtStr).doubleValue(); // Variable name must be meaningful - camelBack style
				}
				catch (NumberFormatException e) {}
				if (payAmount <= 0) { // Variable name must be meaningful - camelBack style
					output("Amount must be positive");
					break;
				}
				payControl.payFine(payAmount); // Variable name must be meaningful - camelBack style
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
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
