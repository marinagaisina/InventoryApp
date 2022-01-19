package com.marinagaisina.inventoryapp.mainrunner;
import com.marinagaisina.inventoryapp.entities.Item;

import static java.lang.System.out;

import java.util.Scanner;

public class Runner {
        private static final Scanner sin = new Scanner(System.in);
        private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        mainMenu();
    }
    private static void mainMenu() {
        sb.append("\n1.Create item\n2. Edit item\n3.Delete item\n4.View a list of items\n5.Quit the application\nPlease Enter Selection:\n");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        boolean quit = false;
        while (!quit) {
            try{
                int option =Integer.parseInt(sin.next());
                switch (option) {
                    case 1:
                        if (addMenu() != null) {

                        };
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        sb.append("GoodBye!");
                        out.print(sb.toString());
                        sb.delete(0, sb.length());
                        quit = true;
                        break;
                }
            } catch (NumberFormatException e) {
                out.println("Wrong input! Input only integer numbers please:");
                //e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static Item addMenu() {
        Item newItem = new Item();
        sb.append("\nPlease enter required information for the item that you would like to add to Inventory System:\nPlease enter a name for the item:\n");
        out.println(sb.toString());
        sb.delete(0, sb.length());
        sin.nextLine();
        try {
            newItem.setItemName(sin.next());
            out.println("\nPlease enter required information for the item that you would like to add to Inventory System:\nPlease enter a name for the item:\n");
            out.println(sb.toString());
            sb.delete(0, sb.length());
        } catch (Exception e) {
            sb.append("No data was entered or wrong symbols were used");
            out.println(sb.toString());
            return null;
        } finally {
            sb.delete(0, sb.length());
        }
        return newItem;
    }
}
