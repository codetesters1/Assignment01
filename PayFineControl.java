public class PayFineControl {
	
	private payFineUI uiName;
	//version 2.0 -use meaningful name ,camelBack so "payFineUI uiName;" not "payFineUI ui;"
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private Library libraryName;
	//version 2.0 class Name "Library" not "library" starts with capital letter - The file loan.java must be updated to reflect this fact
	//version 2.0 -use meaningful name ,camelBack so "Library libraryName;" not "Library library;"
	private Member memberName;
	//version 2.0 class Name "Member" not "member" starts with capital letter - The file loan.java must be updated to reflect this fact
	//version 2.0 -use meaningful name ,camelBack so "Member memberName;" not "Member member;"
	public PayFineControl() {
		this.libraryName = libraryName.INSTANCE();//version 2.0 Variable name must be meaningful - camelBack style
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(PayFineUI uiName) { //version 2.0 -use meaningful name ,camelBack so "payFineUI uiName;" not "payFineUI ui;"
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.uiName = ui;//version 2.0 Variable name must be meaningful - camelBack style
		uiName.setState(PayFineUI.UI_STATE.READY);//version 2.0 Variable name must be meaningful - camelBack style
		state = CONTROL_STATE.READY;		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		memberName = libraryName.getMember(memberId);//version 2.0 Variable name must be meaningful - camelBack style
		
		if (memberName == null) { //version 2.0 Variable name must be meaningful - camelBack style
			uiName.display("Invalid Member Id");  //version 2.0 Variable name must be meaningful - camelBack style
			return;
		}
		uiName.display(member.toString()); //version 2.0 Variable name must be meaningful - camelBack style
		uiName.setState(PayFineUI.UI_STATE.PAYING); //version 2.0 Variable name must be meaningful - camelBack style
		state = CONTROL_STATE.PAYING;
	}
	
	
	public void cancel() {
		uiName.setState(PayFineUI.UI_STATE.CANCELLED); //version 2.0 Variable name must be meaningful - camelBack style
		state = CONTROL_STATE.CANCELLED;
	}


	public double payFine(double amount) {
		if (!state.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
		return change;
	}
	


}
