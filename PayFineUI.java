import java.util.Scanner;

// checked by Facilitator
public class PayFineUI {


	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl payControl; // use meaningful name ,camelBack so "PayFineControl payControl;" not "PayFineControl control;"
	private Scanner inputScanner; //version 1.0 changed variable name from 'input' to 'inputScanner'
	private UI_STATE stateUi; //version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"

	
	public PayFineUI(PayFineControl payControl) {// use meaningful name ,camelBack so "PayFineControl payControl;" not "PayFineControl control;"
		this.payControl = payControl;
		inputScanner = new Scanner(System.in);//version 1.0 changed variable name from 'input' to 'inputScanner' as a parameter
		stateUi = UI_STATE.INITIALISED; //version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"
		payControl.setUI(this);
	}
	
	
	public void setState(UI_STATE stateUi) { //version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"
		this.stateUi = stateUi; //version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"
	}


	public void runDisplay() { //version 3.0 method name should be meaningful and camelBack style - runDisplay not run
		outputDisplay("Pay Fine Use Case UI\n"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
		
		while (true) {
			
			switch (stateUi) { //version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"
			
			case READY:
				String memStr = inputScanner("Swipe member card (press <enter> to cancel): "); // Variable name must be meaningful - camelBack style
				if (memStr.length() == 0) {
					payControl.cancelUi(); // Variable name must be meaningful - camelBack style
					//version 3.0 - PayControlUi- cancel method should be cancelUi
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					payControl.cardSwiped(memberId); // Variable name must be meaningful - camelBack style
				}
				catch (NumberFormatException e) {
					outputDisplay("Invalid memberId"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
				}
				break;
				
			case PAYING:
				double payAmount = 0; // variable name : "payAmount;" not "amount;" - all uppercase is used for constants only
				String amtStr = inputScanner("Enter amount (<Enter> cancels) : "); // Variable name must be meaningful - camelBack style
				if (amtStr.length() == 0) {
					payControl.cancelUi(); // Variable name must be meaningful - camelBack style
					//version 3.0 - PayControlUi- cancel method should be cancelUi
					break;
				}
				try {
					payAmount = Double.valueOf(amtStr).doubleValue(); // Variable name must be meaningful - camelBack style
				}
				catch (NumberFormatException e) {}
				if (payAmount <= 0) { // Variable name must be meaningful - camelBack style
					outputDisplay("Amount must be positive"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
					break;
				}
				payControl.payFine(payAmount); // Variable name must be meaningful - camelBack style
				break;
								
			case CANCELLED:
				outputDisplay("Pay Fine process cancelled"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
				return;
			
			case COMPLETED:
				outputDisplay("Pay Fine process complete"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
				return;
			
			default:
				outputDisplay("Unhandled state"); //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
				throw new RuntimeException("FixBookUI : unhandled state :" + stateUi);	//version 3.0 -use meaningful name ,camelBack so "UI_STATE stateUi;" not "UI_STATE state;"		
			
			}		
		}		
	}

	
	private String inputDisplay(String promptMsg) { //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
		//version 3.0 variable name must be cambelBack style - promptMsg not prompt
		System.out.print(promptMsg); //version 3.0 variable name must be cambelBack style - promptMsg not prompt
		return input.nextLine();
	}	
		
		
	private void outputDisplay(Object objectOut) { //version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
		//version 3.0 variable name must be cambelBack style - objectOut not object
		System.out.println(object);  //version 3.0 variable name must be cambelBack style - objectOut not object
	}	
			

	public void displayOut(Object objectDisplay) { //version 3.0 method name should be meaningful and camelBack style - displayOut not display
		outputDisplay(objectDisplay); //version 3.0 variable name must be cambelBack style - objectDisplay not object
		//version 3.0 method name should be meaningful and camelBack style - outputDisplay not output
	}


}
