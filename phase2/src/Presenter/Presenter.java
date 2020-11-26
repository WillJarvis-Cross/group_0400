package Presenter;


/** Represents the Presenter for the UI
 * @author group 400
 */
public abstract class Presenter {

    /**
     * Prints if the user inputs an invalid option
     *
     */
    public void printInvalidOption() {
        System.out.println("The option you selected was invalid, please try again");
    }
}
