public class ReturnBookControl {
	//Author - Bhanuka 
// checked by Documentor
	//Reviewed by Waqas
	//Updated by moderator
	private ReturnBookUI uiBook; //updated by moderator Dushan- camelBack style - ui to uiBook
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE stateCon; //updated by moderator Dushan- camelBack style -state to stateCon
	
	private Library libraryBook; //updated by moderator- library to libraryBook
	// "Library library" not "library library" as the class type must start with capital letter
	//class Name "Library" not "library" starts with capital letter - The file library.java must be updated to reflect this fact
	private Loan currentLoan;
	// "Loan currentLoan" not "loan currentLoan" as the class type must start with capital letter
	//class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
	

	public ReturnBookControl() { 
	/*returnBookControl not "ReturnBookControl" as method starts with small letter 
	- it is verb and in useCamelBack style*/ 
		this.libraryBook = libraryBook.getInstance();
		//"library.getInstance()" not "library.INSTANCE()" 
		//as the method that returns their sole instance is "getInstanse" 
		//it's verb in useCamelBack style
		stateCon = CONTROL_STATE.INITIALISED; //updated by moderator Dushan- camelBack style -state to stateCon
	}
	
	
	public void setUI(ReturnBookUI uiBook) { 
		if (!stateCon.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.uiBook = uiBook; 
		uiBook.setState(ReturnBookUI.UI_STATE.READY);
		stateCon = CONTROL_STATE.READY;		
		//updated by moderator Dushan- camelBack style 
	}


	public void bookScanned(int bookId) {
		if (!stateCon.equals(CONTROL_STATE.READY)) { //updated by moderator Dushan- camelBack style 
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		Book currentBook = libraryBook.Book(bookId); //updated by moderator Dushan- camelBack style 
		//"Book currentBook" rather than "book currentBook"
		//as the class name must starts with small letter and be verb in useCamelBack style
		//book.java must be updated to reflect this naming convention
		if (currentBook == null) {
			uiBook.display("Invalid Book Id"); //updated by moderator Dushan- camelBack style 
			return;
		}
		if (!currentBook.isLoaned())) {
			/* "currentBook.isLoaned()" not "currentBook.On_loan()"
			the method name must be verb and useCamelBack style 
			*/
			uiBook.displayBook("Book has not been borrowed");
			return;
		}		
		currentLoan = libraryBook.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		uiBook.displayBook("Inspecting");
		uiBook.displayBook(currentBook.toString());
		uiBook.displayBook(currentLoan.toString());
		//updated by moderator Dushan- camelBack style 
		//ReturnBookUi class method display changed to displayBook
		
		if (currentLoan.isOverDue()) {
			uiBook.displayBook(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		uiBook.setState(ReturnBookUI.UI_STATE.INSPECTING); //updated by moderator Dushan- camelBack style 
		stateCon = CONTROL_STATE.INSPECTING;	 //updated by moderator Dushan- camelBack style 	
	}


	public void scanningComplete() {
		if (!stateCon.equals(CONTROL_STATE.READY)) { //updated by moderator Dushan- camelBack style 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		uiBook.setState(ReturnBookUI.UI_STATE.COMPLETED);	 //updated by moderator Dushan- camelBack style 	
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		libraryBook.dischargeLoan(currentLoan, isDamaged); //updated by moderator Dushan- camelBack style 
		currentLoan = null;
		uiBook.setState(ReturnBookUI.UI_STATE.READY); //updated by moderator Dushan- camelBack style 
		stateCon = CONTROL_STATE.READY;	 //updated by moderator Dushan- camelBack style 			
	}


}
