package com.marinagaisina.inventoryapp.mainrunner;
import com.marinagaisina.inventoryapp.entities.Item;
import com.marinagaisina.inventoryapp.service.CartService;
import com.marinagaisina.inventoryapp.service.ItemService;

import static java.lang.System.out;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
        private static final Scanner sin = new Scanner(System.in);
        private static final StringBuilder sb = new StringBuilder();
        private static ItemService itemService;
        private static CartService cartService;

    static {
        try {
            itemService = new ItemService();
            cartService = new CartService();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
    private static void mainMenu() {
        boolean quit = false;
        while (!quit) {
            sb.append("-----------------------------------------------------");
            sb.append("\n1.Create item\n2. Edit item\n3.Delete item\n4.View a list of items\n5.Quit the application\n");
            out.print(sb.toString());
            sb.delete(0, sb.length());
            sb.append("------------------------------------------------------\nPlease Enter Selection:\n");
            out.print(sb.toString());
            sb.delete(0, sb.length());
            try{
                //sin.nextLine();
                int option =Integer.parseInt(sin.nextLine());
                switch (option) {
                    case 1:
                        Item newItem = addMenu();
                        if (newItem != null) {
                            itemService.add(newItem);
                        }
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
                    default:
                        sb.append("\nPlease enter numbers from 1 to 5!");
                        out.println(sb.toString());
                        sb.delete(0, sb.length());
                        break;
                }
            } catch (NumberFormatException e) {
                out.println("Previous operation wasn't completed. Starting over.\nMake sure you follow the required format and noted the restrictions for your input data.");
            } catch (NoSuchElementException e) {
                out.println("You used unsupported symbol. Exiting the app.");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static Item addMenu() {
        Item newItem = new Item();
        sb.append("Please enter required information for the item that you would like to add to Inventory System:\nPlease enter a name for the item:\n");
        out.print(sb.toString());
        try {
            newItem.setItemName(sin.nextLine());
            out.print("Please enter a description for the item:\n");
            newItem.setItemDesc(sin.nextLine());
            out.print("Please enter a price for the item (value 0 is not accepted):\n");
            newItem.setItemPrice(sin.nextDouble());
            sin.nextLine();
            out.print("Please enter a number of available quantity for the item (value 0 is not accepted):\n");
            newItem.setAvailableQuantity(sin.nextInt());
            sin.nextLine();
        } catch (InputMismatchException e) {
            out.println("Please input a number, when a number is requested for your input.\nItem wasn't created.");
            return null;
        } catch (Exception e) {
            out.println("No data was entered or wrong symbols were used. Item wasn't created.");
            return null;
        } finally {
            sb.delete(0, sb.length());
        }
        return newItem;
    }
}
