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
            sb.append("\n1.Create inventory item.\n2. Edit inventory item.\n3.Delete inventory item." +
                    "\n4.View a list of inventory items.\n5.Put an item into cart.\n6.Remove an item from cart." +
                    "\n7.Edit an item's quantity in cart.\n8.View cart.\n9.Quit the application.\n");
            out.print(sb.toString());
            sb.delete(0, sb.length());
            sb.append("------------------------------------------------------\nPlease Enter Selection:\n");
            out.print(sb.toString());
            sb.delete(0, sb.length());
            try{
                int option =Integer.parseInt(sin.next());
                sin.nextLine();
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
                        itemService.display();
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        sb.append("GoodBye!");
                        out.print(sb.toString());
                        sb.delete(0, sb.length());
                        quit = true;
                        break;
                    default:
                        sb.append("\nPlease enter numbers from 1 to 9!");
                        out.println(sb.toString());
                        sb.delete(0, sb.length());
                        break;
                }
            } catch (NumberFormatException e) {
                out.println("Operation wasn't completed.\nMake sure you follow the required format and noted the restrictions for your input data.\nTry again:");
            } catch (NoSuchElementException e) {        // ctrl+D combination
                out.println("You used unsupported key combination.\nExiting the program...");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static Item addMenu() {
        sb.append("Please enter required information for the item that you would like to add to Inventory System:\nPlease enter a name for the item:\n");
        out.print(sb.toString());
        String name, desc;
        double price;
        int availQ;
        try {
            name = sin.nextLine();
            out.print("Please enter a description for the item:\n");
            desc = sin.nextLine();
            out.print("Please enter a price for the item (value 0 is not accepted):\n");
            price = sin.nextDouble();
            out.print("Please enter a number of available quantity for the item (value 0 is not accepted):\n");
            availQ = sin.nextInt();
        } catch (InputMismatchException e) {
            sin.nextLine();
            out.println("Operation wasn't completed.\nMake sure you follow the required format and noted the restrictions for your input data.\nTry again:");
            return null;
        } catch (NoSuchElementException e) {        // ctrl+D combination
            sin.nextLine();
            out.println("You used unsupported key combination.\nTry again:");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sb.delete(0, sb.length());
        }
        return new Item(name, desc, price, availQ);
    }
}
