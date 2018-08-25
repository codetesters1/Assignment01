public class PayFineControl {
	// checked by author
	private PayFineUI uiName;
	//version 2.0 -use meaningful name ,camelBack so "payFineUI uiName;" not "payFineUI ui;"
	//version 3.0 clas name first letter must be capital , it should be PayFineUI not payFineUI
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE stateCon;  //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"
	
	private Library libraryName;
	//version 2.0 class Name "Library" not "library" starts with capital letter - The file loan.java must be updated to reflect this fact
	//version 2.0 -use meaningful name ,camelBack so "Library libraryName;" not "Library library;"
	private Member memberName;
	//version 2.0 class Name "Member" not "member" starts with capital letter - The file loan.java must be updated to reflect this fact
	//version 2.0 -use meaningful name ,camelBack so "Member memberName;" not "Member member;"
	public PayFineControl() {
		this.libraryName = libraryName.INSTANCE();//version 2.0 Variable name must be meaningful - camelBack style
		stateCon = CONTROL_STATE.INITIALISED; //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"
	}
	
	
	public void setUI(PayFineUI uiName) { //version 2.0 -use meaningful name ,camelBack so "payFineUI uiName;" not "payFineUI ui;"
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.uiName = ui;//version 2.0 Variable name must be meaningful - camelBack style
		uiName.setState(PayFineUI.UI_STATE.READY);//version 2.0 Variable name must be meaningful - camelBack style
		stateCon = CONTROL_STATE.READY;	 //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"	
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		memberName = libraryName.getMember(memberId);//version 2.0 Variable name must be meaningful - camelBack style
		
		if (memberName == null) { //version 2.0 Variable name must be meaningful - camelBack style
			uiName.displayUi("Invalid Member Id");  //version 2.0 Variable name must be meaningful - camelBack style
			//version 3.0 method name should change frfom display to displayUi
			return;
		}
		uiName.displayUi(memberName.toString()); //version 2.0 Variable name must be meaningful - camelBack style
		//version3.0 -method name must be camelBack style not display - should be displayUi
		//version3.0 -PayFineUI class file - display method should be updated to displayUi
		uiName.setState(PayFineUI.UI_STATE.PAYING); //version 2.0 Variable name must be meaningful - camelBack style
		stateCon = CONTROL_STATE.PAYING; //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"
	}
	
	
	public void cancelUi() { //version 3.0 method name must be camelBack and start from lowercase - should be cancelUi not cancel
		uiName.setState(PayFineUI.UI_STATE.CANCELLED); //version 2.0 Variable name must be meaningful - camelBack style
		stateCon = CONTROL_STATE.CANCELLED; //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"
	}


	public double payFine(double fineAmount) { //version 3.0 Variable name must be meaningful - camelBack style - should be fineAmount  not amount
		if (!stateCon.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double changeAmt = memberName.payFine(fineAmount); //version 3.0 Variable name must be meaningful - camelBack style - should be fineAmount  not amount
		//version 3.0 Variable name must be meaningful - camelBack style - should be memberName  not member
		////version 3.0 Variable name must be meaningful - camelBack style - should be changeAmt  not change
		if (changeAmt > 0) { //version 3.0 Variable name must be meaningful - camelBack style - should be changeAmt  not change
			uiName.displayUi(String.format("Change: $%.2f", changeAmt));  //version 3.0 Variable name must be meaningful - camelBack style
			//version 3.0 method name should change frfom display to displayUi
		}
		uiName.displayUi(memberName.toString()); //version 3.0 method name should change frfom display to displayUi
		////version 3.0 Variable name must be meaningful - camelBack style- should be uiName not ui
		uiName.setState(PayFineUI.UI_STATE.COMPLETED);
		////version 3.0 Variable name must be meaningful - camelBack style- should be uiName not ui
		stateCon = CONTROL_STATE.COMPLETED; //version 3.0 -use meaningful name ,camelBack so "CONTROL_STATE stateCon;" not "CONTROL_STATE state;"
		return changeAmt; //version 3.0 Variable name must be meaningful - camelBack style
	}
	


}
