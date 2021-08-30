import java.util.Random;
import java.util.Scanner;

public class Enemy {
    private int healthPoint;
    private int attackPoint;
    private int positionX;
    private int positionY;
    private String name;
    private static final int MAXHEALTHPOINT = 20;
    private static final int MINHEALTHPOINT = 20;
    private static final int MAXATTACKPOINT = 10;
    private static final int MINATTACKPOINT = 5;
    private String status = "Live";

    private static Map map;
    private static Map enemyMap;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Enemy(String name, Map map) {
        this.map = map;
        this.name= name;
        this.enemyMap = map.getEnemyMap();
        this.  healthPoint = MINHEALTHPOINT + random.nextInt(MAXHEALTHPOINT + MINHEALTHPOINT + 1);
        attackPoint = MINATTACKPOINT + random.nextInt(MAXATTACKPOINT + MINATTACKPOINT + 1);
        do {
            positionX = random.nextInt(Map.getSizeX());
            positionY = random.nextInt(Map.getSizeY());
        } while (enemyMap.getMap()[positionX][positionY]== 'E');
        enemyMap.enemySpawn(positionX,positionY);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void enemyAttack(Player player){
        int attackHP = 0;
        int attackAP = 0;
        //Направление атаки
        int  atackVector = random.nextInt(4);
        switch (atackVector){
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
                    attackAP = player.getAttackPoint()*3/4;
                    System.out.println(name + " ненес урона HP - " + attackHP + " и ненес урона AP - " + attackAP);
                }
                break;
            default:
                System.out.println(name + "промахнулся");
        }
        player.setAttackPoint(player.getAttackPoint() - attackAP);
        player.setHealthPoint(player.getHealthPoint() - attackHP);
        if (player.getHealthPoint() <= 0){
            player.setStatus("Dead");
            System.out.println("Player is dead");
        }
    }
    //встреча с плеером

}
