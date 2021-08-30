import java.util.Scanner;

public class Game {
    private final static int minMapSize = 3;
    private final static int maxMapSize = 4;
    private static Player player;
    private static String playerName = "Test";
    private static Scanner scanner = new Scanner(System.in);
    private static Map playerMap;
    private static String SatusGame;
    private static int countEnemy;
    private static Enemy[] allEnemy;

    public static void main(String[] args) {
        initialize();
        System.out.println(gamePlay());
    }

    public static void initialize(){
        System.out.println("Player Map: ");
        playerMap = new Map(minMapSize,maxMapSize);
        playerMap.printMap();
        System.out.println("Enemy Map: ");
        playerMap.printEnemyMap();
        player = new Player(playerName,playerMap);
        System.out.println("Start player position " + player.getPositionX() + " " + player.getPositionY());
        playerMap.printMap();
        // generate enemy
        countEnemy = (Map.getSizeX() + Map.getSizeY())/2;
        allEnemy = new Enemy[countEnemy];
        for (int i = 0; i< countEnemy; i++){
            allEnemy[i] = new Enemy("Enemy_" + i, playerMap);
        }
        System.out.println("Enemy Map: ");
        playerMap.printEnemyMap();
    }


    public static String gamePlay(){
        SatusGame = "in progress";
        String gameResult = "Game Over";
        do {
            player.movePlayer();
            playerMap.printMap();
            checkEncounter(player.getPositionX(),player.getPositionY());
            conditionsLose();
            Game.conditionsWin();
        }while (SatusGame.equals("in progress"));
        switch (SatusGame){
            case "You win!!!":
                gameResult = "You win!!!";
                break;
            case "You lose!!!":
                gameResult = "You lose!!!";
                break;
        }
        return gameResult;
    }

    public static void conditionsWin() {
        boolean win = true;
        //SatusGame = "You win!!!";
        for (int i = 0; i < Map.getSizeX(); i++){
            for (int j = 0; j < Map.getSizeY(); j++){
                if ((playerMap.getMap()[i][j]!= '*') && (playerMap.getMap()[i][j]!= 'X')){
                    win = false;
                    break;
                }
                if (!win){
                    break;
                }
            }
        }
        if (win){
            SatusGame = "You win!!!";
        }
    }

    public static void conditionsLose(){
        if (player.getHealthPoint() <= 0){
            SatusGame = "You lose!!!";
        }
    }

    public static void checkEncounter(int px, int py){
        for (int i = 0; i < countEnemy; i++){
            if (allEnemy[i].getPositionX() == px && allEnemy[i].getPositionY() == py){
                if(allEnemy[i].getHealthPoint()>0) {
                    fight(allEnemy[i]);
                }else{
                    System.out.println("Лежит труп врага");
                }
            }
        }
    }

    public static void fight(Enemy enemy){
        System.out.println("FIGHT!!!");
        System.out.println("1. Атака в голову (урон х2, шанс попадания 50%");
        System.out.println("2. Атака в тело (Урон х1, шанс попадания 100%)");
        System.out.println("3. Атака по конечностям (Урон х0.8, уменьшает атаку врага на 25%, шанс попадания 80%");
        do{
            System.out.println("Направление атаки: ");
            Player.attackPlayer(scanner.nextInt(),enemy);
            if(enemy.getStatus() == "Live"){
                enemy.enemyAttack(player);
            }
        } while (enemy.getStatus() == "Live" && player.getStatus() == "Live");
    }

}
