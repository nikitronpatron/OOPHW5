// Реализовать чтение команд с консоли и выполнить их в main методе
// Список команд:
// create-map 3 5 -- РЕАЛИЗОВАНО!
// create-robot 2 7
// move-robot id
// change-direction id LEFT

import java.util.Arrays;
import java.util.Scanner;

// import RobotMap.Robot;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите команду для создания карты: ");
        RobotMap map = null;

        while(true) {
            String command = in.nextLine();
            if(command.startsWith("create-map")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                    System.out.println(map.toString());
                    break;
                }
                catch(IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + ". Попробуйте еще раз. ");
                }
            }
            else {
                System.out.println("Команда не найдена. Попробуйте еще раз. ");;
            }
        }

        RobotMap.Robot robot = null;
        // Point position = null;

        while(true) {
            String command = in.nextLine();
            if(command.startsWith("create-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    robot = new Robot(arguments[0], Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));
                    System.out.println(robot.toString());
                    break;
                }
                catch(IllegalArgumentException e) {
                    System.out.println("При создании робота возникло исключение: " + e + ". Попробуйте еще раз. ");
                }
            }
            else {
                System.out.println("Команда не найдена. Попробуйте еще раз. ");
            }
        }

        

        while(true) {
            String command = in.nextLine();
            if(command.startsWith("move-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    robot = Robot.move();
                    break;
                }
                catch(IllegalArgumentException e) {
                    System.out.println("При попытке перемещения возникло исключение: " + e + ". Попробуйте еще раз.");
                }
            }
            else {
                System.out.println("Команда не найдена. Попробуйте еще раз. ");
            }
        }

        while(true) {
            String command = in.nextLine();
            if(command.startsWith("change-direction")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    robot = Robot.changeDirection(arguments[0]);
                    break;
                }
                catch(IllegalArgumentException e) {
                    System.out.println("При попытке смены направления возникло исключение: " + e + ". Попробуйте еще раз.");
                }
            }
            else {
                System.out.println("Команда не найдена. Попробуйте еще раз. ");
            }
        }
    }
}