public class FixBookControl {
// Author by Waqas
// checked by Moderator
//Checked by Documenter - Bhanuka
	
	private FixBookUI userInterface; //Variable name should start by lowercase letter, use camelCase and should be meaningful
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE stateCon;//updated by reviewer(Dushan) - state to stateCon - use camelback for variables
	
	private Library libraryFixBook;   //Class name should start with Capital Letter
	//updated by reviewer(Dushan) - library to libraryFixBook
	private Book currentBook; //Class name should start with Capital Letter


	public FixBookControl() {
		this.libraryFixBook = libraryFixBook.INSTANCE(); //updated by reviewer(Dushan) - library to libraryFixBook
		stateCon = CONTROL_STATE.INITIALISED; //updated by reviewer(Dushan) - state to stateCon - use camelback for variables
	
	}
	
	
	public void setUI(FixBookUI userInterface) {
		if (!stateCon.equals(CONTROL_STATE.INITIALISED)) { //updated by reviewer(Dushan) - state to stateCon - use camelback for variables
	
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.userInterface = userInterface;
		userInterface.setState(FixBookUI.UI_STATE.READY);
		stateCon = CONTROL_STATE.READY;	 //updated by reviewer(Dushan) - state to stateCon - use camelback for variables
	
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = libraryFixBook.Book(bookId); //updated by reviewer(Dushan) - library to libraryFixBook
		
		if (currentBook == null) {
			userInterface.displayFixBook("Invalid bookId"); //updated by reviewer(Dushan) - method name must change to displaFixBook in FixBookUI.java
			return;
		}
		if (!currentBook.isDamaged()) { //updated by reviewer(Dushan) - method name must change to isDamaged in Book.java
			userInterface.displayFixBook("\"Book has not been damaged"); //updated by reviewer(Dushan) - method name must change to displaFixBook in FixBookUI.java
			return;
		}
		userInterface.displayFixBook(currentBook.toString()); //version 5.0 updated by author (Waqas) variable name- updated by reviewer(Dushan) - method name must change to displayFixBook in FixBookUI.java
		userInterface.setState(FixBookUI.UI_STATE.FIXING);
		stateCon = CONTROL_STATE.FIXING; //updated by reviewer(Dushan) - state changed to stateCon		
	}


	public void fixBook(boolean fix) {
		if (!stateCon.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			libraryFixBook.repairBook(currentBook); //updated by reviewer(Dushan) - library to libraryFixBook
		}
		currentBook = null;
		userInterface.setState(FixBookUI.UI_STATE.READY);
		stateCon = CONTROL_STATE.READY;	//updated by reviewer(Dushan) - state to stateCon	
	}

	
	public void scanningComplete() {
		if (!stateCon.equals(CONTROL_STATE.READY)) { //updated by reviewer(Dushan) - state to stateCon	
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		userInterface.setState(FixBookUI.UI_STATE.COMPLETED);	//version 5.0 updated variable names	
	}






}
