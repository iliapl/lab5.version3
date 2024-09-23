package forCOmmands.Commands;

import forCOmmands.Command;

public class Help implements Command {
    @Override
    public void execute(String argument){
        System.out.println("info: Выводит информацию о коллекции");
        System.out.println("show: Выводит все элементы коллекции");
        System.out.println("add: Добавляет элемент в коллекцию");
        System.out.println("updateID (+ пробел + число): Обновляет значение элемента коллекции, id которого равен заданному");
        System.out.println("removeByID (+ пробел + число): Удаляет элемент из коллекции по его id");
        System.out.println("clear: Очищает коллекцию");
        System.out.println("save: Сохраняет коллекцию в файл");
        System.out.println("executeScriptFileName + имя скрипта: Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь");
        System.out.println("addIfMin: Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("removeGreater: Удаляет из коллекции все элементы, превышающие заданный");
        System.out.println("exit: Завершает выполнение программы без сохранения в файл");
        System.out.println("history: вывести последние 7 команд (без их аргументов)");
        System.out.println("sumOfEnginePower: вывести сумму значений поля enginePower для всех элементов коллекции");
        System.out.println("averageOfEnginePower: вывести среднее значение поля enginePower для всех элементов коллекции");
        System.out.println("printUniqueFuelType: вывести уникальные значения поля fuelType всех элементов в коллекции");
    }
}
