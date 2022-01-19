package com.marinagaisina.inventoryapp.mainrunner;
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
}
