package homework5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> users = new ArrayList<>();

    private static void handleManualInput() {

    }

    private static void handleFileInput() {

    }

    private static void handleRandomInput() {

    }

    private static void handleBubbleSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните список пользователей");
            return;
        }

        System.out.println("Bubble sort пока не реализован");

    }

    private static void handleSelectionSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните список пользователей");
            return;
        }

        System.out.println("Selection sort пока не реализован");

    }

    private static void handleInsertionSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните список пользователей");
            return;
        }

        System.out.println("Insertion sort пока не реализован");

    }

    private static void handleSave() {

    }

    private static void handleCount() {

    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Ручной ввод");
                    handleManualInput();
                    break;
                case 2:
                    System.out.println("Read from file");
                    handleFileInput();
                    break;
                case 3:
                    System.out.println("Random generate");
                    handleRandomInput();
                    break;
                case 4:
                    System.out.println("Bubble sort");
                    handleBubbleSort();
                    break;
                case 5:
                    System.out.println("Selection sort");
                    handleSelectionSort();
                    break;
                case 6:
                    System.out.println("Insertion sort");
                    handleInsertionSort();
                    break;
                case 7:
                    System.out.println("Save to file");
                    handleSave();
                    break;
                case 8:
                    System.out.println("Count occurrences");
                    handleCount();
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                1 - Ввод вручную
                2 - Ввод из файла
                3 - Random
                4 - Bubble sort
                5 - Selection sort
                6 - Insertion sort
                7 - Save to file
                8 - Count id
                0 - Exit
                """);
    }
}