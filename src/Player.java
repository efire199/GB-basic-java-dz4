import java.util.Random;
import java.util.Scanner;

public class Player {
    private static int healthPoint = 10;
    private static int attackPoint = 30;
    private static final int MOVEUP = 8;
    private static final int MOVELEFT = 4;
    private static final int MOVERIGHT = 6;
    private static final int MOVEDOWN = 2;
    private static String name;
    private int positionX;
    private int positionY;
    private int lastPositionX;
    private int lastPositionY;
    private static Map map;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private String status = "Live";

    public Player(String name, Map map) {
        this.map = map;
        this.name = name;
        positionX = random.nextInt(Map.getSizeX());
        positionY = random.nextInt(Map.getSizeY());
        map.setPlayerPosition(positionX,positionY,positionX,positionY);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void movePlayer(){
        lastPositionX = positionX;
        lastPositionY = positionY;
        boolean valid = true;
        int b = '5';
        do {
            valid = true;
            System.out.print("Ход игрока: ");
            b = scanner.nextInt();
            switch (b){
                case MOVEUP:
                    positionX -= 1;
                    break;
                case MOVEDOWN:
                    positionX += 1;
                    break;
                case MOVELEFT:
                    positionY -= 1;
                    break;
                case MOVERIGHT:
                    positionY += 1;
                    break;
                default:
                    System.out.println("Некорректное значение");
                    valid = false;
            }
           if(!map.validationMove(positionX,positionY)){
               System.out.println("Ход за пределы карты");
               positionX = lastPositionX;
               positionY = lastPositionY;
               valid = false;
           }
        }while (!valid);
        map.setPlayerPosition(positionX,positionY,lastPositionX,lastPositionY);
    }

    public static void attackPlayer(int a,Enemy enemy){
        int attackHP = 0;
        int attackAP = 0;
        switch (a){
            case 1:
                if (random.nextInt(100)>50){
                attackHP = attackPoint * 2;
                System.out.println(name + " ненес урона HP - " + attackHP);
                }
                break;
            case 2:
                attackHP = attackPoint;
                System.out.println(name + " ненес урона HP - " + attackHP);
                break;
            case 3:
                if (random.nextInt(100)>20){
                    attackHP = attackPoint * 80 / 100;
                    attackAP = enemy.getAttackPoint()*3/4;
                    System.out.println(name + " ненес урона HP - " + attackHP + " и ненес урона AP - " + attackAP);
                }
                break;
            default:
                System.out.println("Промах (некорректный ввод");
        }
        enemy.setAttackPoint(enemy.getAttackPoint() - attackAP);
        enemy.setHealthPoint(enemy.getHealthPoint() - attackHP);
        if (enemy.getHealthPoint() <= 0){
            enemy.setStatus("Dead");
            map.deleteEnemy(enemy.getPositionX(),enemy.getPositionY());
            System.out.println("Enemy is dead");

        }
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

}
