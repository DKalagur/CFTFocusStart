package ru.cft.team.View;

import ru.cft.team.Model.Item;
import ru.cft.team.Model.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Model myModel;
    private boolean isValidInput;
    private final Scanner scanner;
    private List<Item> cars;
    private int tableSize;

    public View(Model myModel) throws SQLException, ClassNotFoundException {
        this.myModel = myModel;
        scanner = new Scanner(System.in);

        for (; ; ) {
            tableSize = myModel.readTable().size();

            if (tableSize == 0) {
                showShortMenu();
            } else {
                showMenu();
            }
        }
    }

    private void showMenu() throws SQLException, ClassNotFoundException {
        int operationWithTableChoice = 0;

        showAllItems();

        System.out.println("Выберите действие: 1 - добавить авто, 2 - редактировать авто, 3 - удалить авто, 4 - выход");

        do {
            String input = scanner.nextLine();

            isValidInput = checkNumericInput(input, 1, 4);
            if (!isValidInput) {
                System.out.println("Выберите действие в виде числа в диапазоне от " + 1 + " до " + 4);
            } else {
                operationWithTableChoice = Integer.parseInt(input);
            }
        } while (!isValidInput);

        switch (operationWithTableChoice) {
            case 1:
                addCar();
                break;
            case 2:
                editCar();
                break;
            case 3:
                deleteCar();
                break;
            case 4:
                System.exit(1);
        }
    }

    public void showShortMenu() throws SQLException, ClassNotFoundException {
        int operationWithTableChoice = 0;

        System.out.println("Список пуст");
        System.out.println("Выберите действие: 1 - добавить авто, 2 - выход");

        do {
            String input = scanner.nextLine();

            isValidInput = checkNumericInput(input, 1, 2);
            if (!isValidInput) {
                System.out.println("Выберите действие в виде числа в диапазоне от " + 1 + " до " + 2);
            } else {
                operationWithTableChoice = Integer.parseInt(input);
            }
        } while (!isValidInput);

        switch (operationWithTableChoice) {
            case 1:
                addCar();
                showAllItems();
                break;
            case 2:
                System.exit(1);
        }
    }

    public void showAllItems() throws SQLException, ClassNotFoundException {
        cars = myModel.readTable();

        System.out.println("Список автомобилей:");

        System.out.printf("%-2s ", "N");
        for (int i = 1; i < Item.COLUMNS.length; ++i) {
            System.out.printf("%-15s ", Item.COLUMNS[i]);
        }
        System.out.printf("%n");

        for (int i = 0; i < cars.size(); ++i) {
            System.out.printf("%-2s %-15s %-15s %-15s %-15s%n", i + 1 + ".", cars.get(i).getBrand(), cars.get(i).getModel(), cars.get(i).getBodyType(), cars.get(i).getYear());
        }
    }

    public void addCar() throws SQLException, ClassNotFoundException {
        Item car = getDataAboutNewCar();
        myModel.addRow(car);
    }

    public void deleteCar() throws SQLException, ClassNotFoundException {
        System.out.println("Введите номер авто для удаления:");

        int number = chooseItemForChange(tableSize);
        Item car = new Item();
        car.setId(cars.get(number - 1).getId());

        myModel.delRow(car);
    }

    public void editCar() throws SQLException, ClassNotFoundException {
        System.out.println("Введите номер авто для редактирования:");

        int number = chooseItemForChange(tableSize);
        Item car = getDataAboutNewCar();
        car.setId(cars.get(number - 1).getId());

        myModel.updateRow(car);
    }

    private int chooseItemForChange(int tableSize) {
        int number = 0;
        do {
            String input = scanner.nextLine();

            isValidInput = checkNumericInput(input, 1, tableSize);
            if (!isValidInput) {
                System.out.println("Выберите номер в диапазоне от " + 1 + " до " + tableSize);
            } else {
                number = Integer.parseInt(input);
            }
        } while (!isValidInput);
        return number;
    }

    public Item getDataAboutNewCar() {
        Item car = new Item();

        String brand;
        do {
            System.out.println("Введите производителя авто:");
            brand = scanner.nextLine().toUpperCase();
            isValidInput = checkTextInput(brand);
        } while (isValidInput);

        car.setBrand(brand);

        String model;
        do {
            System.out.println("Введите модель:");
            model = scanner.nextLine().toUpperCase();
            isValidInput = checkTextInput(model);
        } while (isValidInput);

        car.setModel(model);

        int year = 0;
        System.out.println("Введите год выпуска:");

        do {
            String input = scanner.nextLine();

            isValidInput = checkNumericInput(input, Item.START_YEAR, Item.END_YEAR);
            if (!isValidInput) {
                System.out.println("Введите год выпуска автомобиля в виде числа в диапазоне от " + Item.START_YEAR + " до " + Item.END_YEAR);
            } else {
                year = Integer.parseInt(input);
            }
        } while (!isValidInput);

        car.setYear(year);

        System.out.println("Выберите тип кузова:");
        for (int i = 0; i < Item.bodyTypes.size(); ++i) {
            System.out.println(i + 1 + " - " + Item.bodyTypes.get(i));
        }

        int bodyType = 0;

        do {
            String input = scanner.nextLine();

            isValidInput = checkNumericInput(input, 1, Item.bodyTypes.size());
            if (!isValidInput) {
                System.out.println("Выберите номер в диапазоне от " + 1 + " до " + Item.bodyTypes.size());
            } else {
                bodyType = Integer.parseInt(input);
            }
        } while (!isValidInput);

        car.setBodyType(Item.bodyTypes.get(bodyType - 1));

        return car;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkNumericInput(String input, int leftBorder, int rightBorder) {
        int numericAnswer;
        boolean validInput = false;

        if (isNumber(input)) {
            numericAnswer = Integer.parseInt(input);

            if (numericAnswer >= leftBorder && numericAnswer <= rightBorder) {
                validInput = true;
            }
        }

        return validInput;
    }

    private boolean checkTextInput(String input) {
        return input == null || input.trim().length() == 0;
    }
}