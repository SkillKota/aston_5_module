package homework5;

import homework5.collection.UserList;
import homework5.service.CounterService;
import homework5.service.FileInputService;
import homework5.service.FileSaveService;
import homework5.service.FileSaveUserService;
import homework5.service.InputService;
import homework5.service.ManualInputService;
import homework5.service.MultiThreadCounter;
import homework5.service.RandomUserGenerator;
import homework5.service.UserComparatorProvider;
import homework5.strategy.BubbleSortStrategy;
import homework5.strategy.InsertionSortStrategy;
import homework5.strategy.SelectionSortStrategy;
import homework5.strategy.SortStrategy;
import homework5.util.FillType;
import homework5.util.SortField;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserList users = new UserList();
    private static final InputService manualInputService = new ManualInputService();
    private static final InputService fileInputService = new FileInputService();
    private static final InputService randomInputService = new RandomUserGenerator();
    private static final SortStrategy bubbleSortStrategy = new BubbleSortStrategy();
    private static final SortStrategy selectionSortStrategy = new SelectionSortStrategy();
    private static final SortStrategy insertionSortStrategy = new InsertionSortStrategy();
    private static final UserComparatorProvider comparatorProvider = Main::getComparatorByField;
    private static final FileSaveService fileSaveService = new FileSaveUserService();
    private static final CounterService counterService = new MultiThreadCounter();

    private static void handleManualInput() {
        handleInputByType(FillType.MANUAL);
    }

    private static void handleFileInput() {
        handleInputByType(FillType.FILE);
    }

    private static void handleRandomInput() {
        handleInputByType(FillType.RANDOM);
    }

    private static void handleBubbleSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните коллекцию пользователей");
            return;
        }

        handleSort("Bubble sort", bubbleSortStrategy);
    }

    private static void handleSelectionSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните коллекцию пользователей");
            return;
        }

        handleSort("Selection sort", selectionSortStrategy);
    }

    private static void handleInsertionSort() {
        if (users.isEmpty()) {
            System.out.println("Сначала заполните коллекцию пользователей");
            return;
        }

        handleSort("Insertion sort", insertionSortStrategy);
    }

    private static void handleSave() {
        if (users.isEmpty()) {
            System.out.println("Нечего сохранять: коллекция пустая");
            return;
        }

        if (fileSaveService == null) {
            System.out.println("Сервис сохранения пока не подключен");
            return;
        }

        String filePath = readNonEmptyString("Введите путь к файлу для сохранения");

        try {
            fileSaveService.append(users, filePath);
            System.out.println("Данные успешно сохранены");
        } catch (RuntimeException exception) {
            System.out.println("Ошибка при сохранении: " + exception.getMessage());
        }
    }

    private static void handleCount() {
        if (users.isEmpty()) {
            System.out.println("Нечего считать: коллекция пустая");
            return;
        }

        if (counterService == null) {
            System.out.println("Сервис подсчета пока не подключен");
            return;
        }

        int id = readPositiveInt("Введите id для подсчета вхождений");

        try {
            int count = counterService.countById(users, id);
            System.out.println("Количество пользователей с id " + id + ": " + count);
        } catch (RuntimeException exception) {
            System.out.println("Ошибка при подсчете: " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    handleManualInput();
                    break;
                case 2:
                    handleFileInput();
                    break;
                case 3:
                    handleRandomInput();
                    break;
                case 4:
                    handleBubbleSort();
                    break;
                case 5:
                    handleSelectionSort();
                    break;
                case 6:
                    handleInsertionSort();
                    break;
                case 7:
                    handleSave();
                    break;
                case 8:
                    handleCount();
                    break;
                case 9:
                    printUsers();
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
                3 - Случайная генерация
                4 - Bubble sort
                5 - Selection sort
                6 - Insertion sort
                7 - Сохранить в файл
                8 - Подсчитать вхождения id
                9 - Показать текущую коллекцию
                0 - Exit
                """);
    }

    private static void handleInputByType(FillType fillType) {
        InputService inputService = resolveInputService(fillType);
        if (inputService == null) {
            System.out.println("Сервис для режима \"" + fillType.getDescription() + "\" пока не подключен");
            return;
        }

        int size = fillType == FillType.FILE ? 0 : readPositiveInt("Введите размер коллекции");

        try {
            UserList loadedUsers = inputService.input(size);
            replaceUsers(loadedUsers);
            System.out.println("Коллекция успешно заполнена");
            printUsers();
        } catch (RuntimeException exception) {
            System.out.println("Ошибка при заполнении: " + exception.getMessage());
        }
    }

    private static void handleSort(String algorithmName, SortStrategy sortStrategy) {
        if (sortStrategy == null) {
            System.out.println(algorithmName + " пока не подключен");
            return;
        }

        Comparator<User> comparator = requestComparator();
        if (comparator == null) {
            return;
        }

        try {
            sortStrategy.sort(users, comparator);
            System.out.println(algorithmName + " выполнен успешно");
            printUsers();
        } catch (RuntimeException exception) {
            System.out.println("Ошибка во время сортировки: " + exception.getMessage());
        }
    }

    private static Comparator<User> requestComparator() {
        if (comparatorProvider == null) {
            System.out.println("Провайдер компараторов пока не подключен");
            return null;
        }

        SortField sortField = requestSortField();
        Comparator<User> comparator = comparatorProvider.getComparator(sortField);
        if (comparator == null) {
            System.out.println("Компаратор для поля \"" + sortField.getDescription() + "\" не найден");
        }
        return comparator;
    }

    private static Comparator<User> getComparatorByField(SortField sortField) {
        switch (sortField) {
            case ID:
                return Comparator.comparingInt(User::getId);
            case NAME:
                return Comparator.comparing(User::getName);
            case EMAIL:
                return Comparator.comparing(User::getEmail);
            case PASSWORD:
                return Comparator.comparing(User::getPassword);
            default:
                return null;
        }
    }

    private static SortField requestSortField() {
        while (true) {
            System.out.println("""
                    Выберите поле сортировки:
                    1 - id
                    2 - name
                    3 - email
                    4 - password
                    """);
            int fieldNumber = readMenuChoice();
            SortField sortField = SortField.fromMenuNumber(fieldNumber);
            if (sortField != null) {
                return sortField;
            }
            System.out.println("Неверный выбор поля");
        }
    }

    private static InputService resolveInputService(FillType fillType) {
        switch (fillType) {
            case MANUAL:
                return manualInputService;
            case FILE:
                return fileInputService;
            case RANDOM:
                return randomInputService;
            default:
                return null;
        }
    }

    private static void replaceUsers(UserList loadedUsers) {
        if (loadedUsers == null) {
            throw new IllegalArgumentException("Сервис вернул null вместо коллекции");
        }

        users.clear();
        for (int index = 0; index < loadedUsers.size(); index++) {
            users.add(loadedUsers.get(index));
        }
    }

    private static void printUsers() {
        if (users.isEmpty()) {
            System.out.println("Коллекция пустая");
            return;
        }

        System.out.println("Текущая коллекция пользователей:");
        for (int index = 0; index < users.size(); index++) {
            System.out.println((index + 1) + ". " + users.get(index));
        }
    }

    private static int readMenuChoice() {
        while (true) {
            try {
                System.out.print("Введите пункт меню: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException exception) {
                System.out.println("Нужно ввести целое число");
                scanner.nextLine();
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Число должно быть больше 0");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException exception) {
                System.out.println("Нужно ввести целое число");
                scanner.nextLine();
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Строка не должна быть пустой");
        }
    }
}
