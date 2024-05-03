package FileDo;

import toVehicle.Vehicle;
import util.FileRead;
import util.WriteFileToXML;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Comands {
    private final Method[] methods;
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;
    private FileRead fileRead;
    private final Set<String> scriptNames;
    private boolean isScriptExecuting;
    private WriteFileToXML writeFileToXML;

    public Comands(Set<Vehicle> vehicles, FileRead reader,  WriteFileToXML writeFileToXML){
        this.methods = Comands.class.getMethods();
        creationDate = java.time.ZonedDateTime.now();
        if (vehicles == null) {
            this.vehicles = new HashSet<>();
        } else {
            this.vehicles = vehicles;
            this.vehicles.addAll(vehicles);
        }
        this.fileRead = reader;
        this.isScriptExecuting = false;
        scriptNames = new HashSet<>();
        this.writeFileToXML = writeFileToXML;
    }
    public void exit() {
        System.out.println("Завершение программы...");
        System.exit(0);
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
        if (inputLine == null || inputLine.trim().isEmpty()) {
            System.out.println("Введите команду"); // Выводится, если строка пустая
            return false; // Возвращение для предотвращения дальнейших действий
        }

        String[] line = inputLine.trim().split(" ", 2); // Разделение строки на команду и аргумент
        String command = inputCommandToJavaStyle(line[0].toLowerCase()); // Преобразование к camelCase

        try {
            // Если команда "exit", завершите выполнение программы
            if ("exit".equalsIgnoreCase(command)) {
                exit();
                return true;
            }

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
