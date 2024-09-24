package forCommands.Commands;

import forCommands.Command;
import forCommands.CommandManager;
import java.io.*;

public class ExecuteScript implements Command {
    private final CommandManager commandManager;
    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void execute(String argument) {
        File file = new File(argument);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Файл пуст, считывать с файла нечего.");
                return;
            }
            while (line != null) {
                System.out.println("Выполняем команду: " + line);
                commandManager.executeCommand(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
        }
    }
}
    /*
    private final Method methods;


    public ExecuteScript(Method methods) {
        this.methods = methods;
    }

    @Override
    public void execute(String argument){
        try {
            File file = new File(argument);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Файл пуст, считывать с файла нечего");
                return;
            }
            while (line != null) {
                System.out.println("Выполняем команду " + line);
                executeCommand(line);
                line = reader.readLine();
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (
                IOException e) {
            System.out.println("Ошибка при чтении из файла " + e.getMessage());
        }
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
        String[] line = inputLine.trim().split(" ", 2);// Разделение строки на команду и аргумент
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
} */
