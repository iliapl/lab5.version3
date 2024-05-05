package FileDo;

import toVehicle.FuelType;
import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Comands {
    private final Method[] methods;
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;
    private final FileRead fileRead;
    private final Set<String> scriptNames;
    private boolean isScriptExecuting;
    private Deque<String> commandHistory;
    private final int maxHistorySize = 7;
    private WriteFileToXML writeFileToXML;
    private VehiclesCollecton vehiclesCollecton;
    String path;

    public Comands(VehiclesCollecton vehiclesCollecton, FileRead reader, WriteFileToXML writeFileToXML, File file) {
        this.methods = Comands.class.getMethods();
        creationDate = java.time.ZonedDateTime.now();
        if (vehiclesCollecton.getVehicles() == null) {
            this.vehicles = new HashSet<>();
        } else {
            this.vehicles = vehiclesCollecton.getVehicles();
            this.vehicles.addAll(vehiclesCollecton.getVehicles());
        }
        this.fileRead = reader;
        this.isScriptExecuting = false;
        scriptNames = new HashSet<>();
        this.commandHistory = new LinkedList<>();
        this.writeFileToXML = writeFileToXML;
        this.vehiclesCollecton = vehiclesCollecton;
        path = file.getAbsolutePath();
    }

    public void help() {
        System.out.println("info: Выводит информацию о коллекции");
        System.out.println("show: Выводит все элементы коллекции");
        System.out.println("add: Добавляет элемент в коллекцию");
        System.out.println("updateID (+ пробел + число): Обновляет значение элемента коллекции, id которого равен заданному");
        System.out.println("removeByID (+ пробел + число): Удаляет элемент из коллекции по его id");
        System.out.println("clear: Очищает коллекцию");
        System.out.println("save: Сохраняет коллекцию в файл");
        System.out.println("executeScript: Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь");
        System.out.println("addIfMin: Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("removeGreater: Удаляет из коллекции все элементы, превышающие заданный");
        System.out.println("exit: Завершает выполнение программы без сохранения в файл");
        System.out.println("history: вывести последние 7 команд (без их аргументов)");
        System.out.println("sumOfEnginePower: вывести сумму значений поля enginePower для всех элементов коллекции");
        System.out.println("averageOfEnginePower: вывести среднее значение поля enginePower для всех элементов коллекции");
        System.out.println("printUniqueFuelType: вывести уникальные значения поля fuelType всех элементов в коллекции");
    }

    public void info() {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + vehicles.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + creationDate);
        System.out.println("Количество элементов: " + vehicles.size());
    }

    public void show() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Элементы коллекции:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.vehicleToString());
        }
    }

    public void add() {
        try {
            Vehicle newVehicle = fileRead.readVehicleFromConsole();
            vehicles.add(newVehicle);
            System.out.println("Добавлен новый транспорт: " + newVehicle.vehicleToString());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении нового транспорта: " + e.getMessage());
        }
    }

    public void updateID(String argument) {
        try {
            int id = Integer.parseInt(argument.trim());
            Vehicle vehicleToUpdate = null;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() == id) {
                    vehicleToUpdate = vehicle;
                    break;
                }
            }

            if (vehicleToUpdate == null) {
                System.out.println("Элемент с ID " + id + " не найден.");
                return;
            }
            Vehicle newVehicleData = fileRead.readVehicleFromConsole();
            vehicleToUpdate.update(newVehicleData);
            System.out.println("Элемент с ID " + id + " обновлен.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат ID. Введите целое число.");
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении элемента: " + e.getMessage());
        }
    }

    public void removeById(String argument) {
        try {
            int id = Integer.parseInt(argument.trim());
            boolean removed = vehicles.removeIf(vehicle -> vehicle.getId() == id);
            if (removed) {
                System.out.println("Элемент с ID " + id + " был успешно удален.");
            } else {
                System.out.println("Элемент с ID " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат ID. Введите целое число.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении элемента: " + e.getMessage());
        }
    }

    public void clear() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция уже пустая, нечего очищать.");
            return;
        }

        try {
            vehicles.clear();
            System.out.println("Коллекция была успешно очищена.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка при очистке коллекции: " + e.getMessage());
        }
    }

    public void save() {
        try {
            if (vehicles.isEmpty()) {
                System.out.println("Коллекция пуста, нечего сохранять.");
                return;
            }
            Files.newBufferedWriter(Path.of(path), StandardOpenOption.TRUNCATE_EXISTING);
            writeFileToXML.toSaveToXML();
            System.out.println("Коллекция успешно сохранена.");
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка при парсинге XML: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла неизвестная ошибка при сохранении: " + e.getMessage());
        }
    }


    public void executeScriptFileName(String fileFullName) {
        try {
            File file = new File(fileFullName);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                System.out.println("Выполняем команду " + line);
                executeCommand(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из файла " + e.getMessage());
        }
    }

    public void exit() {
        System.out.println("Завершение программы...");
        System.exit(0);
    }

    public void addIfMin() {
        try {
            Vehicle newVehicle = fileRead.readVehicleFromConsole();
            Optional<Vehicle> minVehicle = vehicles.stream().min(Comparator.comparingInt(Vehicle::getEnginePower));
            if (minVehicle.isPresent()) {
                int minEnginePower = minVehicle.get().getEnginePower();
                if (newVehicle.getEnginePower() < minEnginePower) {
                    vehicles.add(newVehicle);
                    System.out.println("Добавлен новый элемент, так как его enginePower меньше, чем у наименьшего элемента.");
                } else {
                    System.out.println("Новый элемент не добавлен, так как его enginePower не меньше минимального.");
                }
            } else {
                vehicles.add(newVehicle);
                System.out.println("Коллекция пуста. Добавлен первый элемент.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении элемента: " + e.getMessage());
        }
    }

    public void removeGreater() {
        try {
            Vehicle referenceVehicle = fileRead.readVehicleFromConsole();
            int referenceEnginePower = referenceVehicle.getEnginePower();
            int initialSize = vehicles.size();
            vehicles.removeIf(vehicle -> vehicle.getEnginePower() > referenceEnginePower);
            int elementsRemoved = initialSize - vehicles.size();
            if (elementsRemoved > 0) {
                System.out.println("Удалено " + elementsRemoved + " элементов, превышающих заданный.");
            } else {
                System.out.println("Нет элементов, превышающих заданный.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении элементов: " + e.getMessage());
        }
    }

    public void history() { //в истории даже несуществующие команды (плохо или нет)
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста.");
            return;
        }

        System.out.println("Последние 7 команд:");
        int index = 1;
        for (String command : commandHistory) {
            System.out.println(index + ". " + command);
            index++;
        }
    }

    public void sumOfEnginePower() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Сумма значения enginePower: 0.");
            return;
        }
        int totalEnginePower = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
        System.out.println("Сумма значений поля enginePower для всех элементов коллекции: " + totalEnginePower);
    }

    public void averageOfEnginePower() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Среднее значение enginePower: 0.");
            return;
        }
        int totalEnginePower = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
        long count = vehicles.stream().count();
        double averageEnginePower = (double) totalEnginePower / count;
        System.out.printf("Среднее значение enginePower для всех элементов коллекции: %.2f%n", averageEnginePower);
    }

    public void printUniqueFuelType() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Нет уникальных значений fuelType.");
            return;
        }
        Set<FuelType> uniqueFuelTypes = vehicles.stream().map(Vehicle::getFuelType).collect(Collectors.toSet());
        if (uniqueFuelTypes.isEmpty()) {
            System.out.println("Нет уникальных значений fuelType.");
        } else {
            System.out.println("Уникальные значения fuelType:");
            uniqueFuelTypes.forEach(System.out::println);
        }
    }

    public void recordCommand(String command) {
        if (commandHistory.size() >= maxHistorySize) {  // Если достигнут максимальный размер
            commandHistory.pollFirst();  // Удалить самый старый элемент
        }
        commandHistory.addLast(command);  // Добавить новую команду в конец
    }

    private static String inputCommandToJavaStyle(String str) {
        StringBuilder result = new StringBuilder();
        boolean needUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '_') {
                if (needUpperCase) {
                    c = Character.toUpperCase(c);
                    needUpperCase = false;
                }
                result.append(c);
            } else {
                needUpperCase = true;
            }
        }
        return result.toString();
    }

    //при помощи связки inputCommandToJavaStyle и executeCommand происходит нахождение метода с названием команды
    public boolean executeCommand(String inputLine) {
        recordCommand(inputLine);
        if (inputLine == null || inputLine.trim().isEmpty()) {
            System.out.println("Введите команду"); // Выводится, если строка пустая
            return false; // Возвращение для предотвращения дальнейших действий
        }

        String[] line = inputLine.trim().split(" ", 2); // Разделение строки на команду и аргумент
        String command = inputCommandToJavaStyle(line[0].toLowerCase()); // Преобразование к camelCase

        try {
            Method methodToInvoke = null;
            // Поиск метода, соответствующего названию команды
            for (Method method : methods) {
                if (method.getName().equalsIgnoreCase(command)) {
                    methodToInvoke = method;
                    break;
                }
            }

            if (methodToInvoke == null) {
                System.out.println("Такой команды не существует: " + command);
                return false; // Завершить выполнение, если метод не найден
            }

            // Если метод без аргументов, вызываем его
            if (methodToInvoke.getParameterCount() == 0) {
                methodToInvoke.invoke(this);
            } else if (line.length > 1) { // Если метод принимает аргументы, но нет аргументов
                String argument = line[1].trim(); // Убедитесь, что аргумент корректен
                if (argument.isEmpty()) {
                    System.out.println("Не хватает аргументов для команды: " + command);
                    return false;
                }
                methodToInvoke.invoke(this, argument); // Вызов метода с аргументом
            } else {
                System.out.println("Не хватает аргументов для команды: " + command); // Если аргумент отсутствует
            }
        } catch (InvocationTargetException e) {
            System.out.println("Ошибка при вызове метода: " + e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
