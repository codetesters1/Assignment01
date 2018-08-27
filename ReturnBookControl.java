public class ReturnBookControl {
	//Checked by facilitator Vidath
// checked by Documentor
	private ReturnBookUI ui;
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private Library Library;
	// "Library library" not "library library" as the class type must start with capital letter
	//class Name "Library" not "library" starts with capital letter - The file library.java must be updated to reflect this fact
	private Loan currentLoan;
	// "Loan currentLoan" not "loan currentLoan" as the class type must start with capital letter
	//class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
	

	public ReturnBookControl() { 
	/*returnBookControl not "ReturnBookControl" as method starts with small letter 
	- it is verb and in useCamelBack style*/ 
		this.library = library.getInstance();
		//"library.getInstance()" not "library.INSTANCE()" 
		//as the method that returns their sole instance is "getInstanse" 
		//it's verb in useCamelBack style
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI ui) { 
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		Book currentBook = library.Book(bookId);
		//"Book currentBook" rather than "book currentBook"
		//as the class name must starts with small letter and be verb in useCamelBack style
		//book.java must be updated to reflect this naming convention
		if (currentBook == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.isLoaned())) {
			/* "currentBook.isLoaned()" not "currentBook.On_loan()"
			the method name must be verb and useCamelBack style 
			*/
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;				
	}


}
