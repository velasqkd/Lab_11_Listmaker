import java.util.Scanner;
import java.util.ArrayList;
public class ListMaker {
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final String menuPrompt = "A - add D - delete I - insert P - print Q - quit";
        boolean running = true;
        String item = "";
        String cmd = "";
        String quitYN = "";
        int userSelection;

        do {
            displayList();
            displayMenu();
            cmd = in.nextLine();
            cmd = SafeInput.getRegExString(in, menuPrompt, "[AaDdIiPpQq]");

            switch (cmd) {
                case "A":
                case "a":
                    addItem(in);
                    break;

                case "D":
                case "d":
                    deleteItem(in);
                    break;

                case "I":
                case "i":
                    insertItem(in);
                    break;

                case "P":
                case "p":
                    printList(in);
                    break;

                case "Q":
                case "q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
                        running = false;
                    }
                    break;
            }
        } while (running);
        System.out.println("The end.");
    }
    public static void displayMenu() {
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    public static void displayList() {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("[The list is currently empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
    }

    public static void addItem(Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add:");
        list.add(item);
    }

    public static void deleteItem(Scanner in) {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }
        int selection = SafeInput.getRangedInt(in, "Enter the number of the item to delete:", 1, list.size());
        list.remove(selection - 1);
    }

    public static void insertItem(Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert:");
        int position = SafeInput.getRangedInt(in, "Enter the position to insert at:", 1, list.size() + 1);
        list.add(position - 1, item);
    }

    public static void printList(Scanner in) {
        System.out.println("\nFull List Contents:");
        displayList();
    }
}
