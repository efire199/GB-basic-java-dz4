import java.util.Random;

public class Map {
    private static int minSize;
    private static int maxSize;
    private static int sizeX;
    private static int sizeY;

    private static final char EMPTYCELL = '_';
    private static final char ENEMY = 'E';
    private static final char PLAYER = '*';
    private static final char OPENCELS = 'X';
    private Random random = new Random();
    private char[][] map;
    private Map enemyMap;


    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    public char[][] getMap() {
        return map;
    }

    public Map getEnemyMap() {
        return enemyMap;
    }

    public Map(int minSize, int maxSize) {
        Map.minSize = minSize;
        Map.maxSize = maxSize;
        sizeX = minSize + random.nextInt(maxSize - minSize + 1);
        sizeY = minSize + random.nextInt(maxSize - minSize + 1);
        map = new char[sizeX][sizeY];
        enemyMap = new Map(this);
        for (int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++){
                map[i][j]=EMPTYCELL;
            }
        }
    }

    /*public Map(int size) {
        map = new char[size][size];
    }*/

    public Map(Map map) {
        this.map = new char[Map.sizeX][Map.sizeY];

    }

    public void printMap (){
        System.out.print("  ");
        for (int i = 0; i < sizeY; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < sizeX; i++){
            System.out.print(i+1 + "|");
            for (int j = 0; j < sizeY; j++){
                System.out.print(map[i][j]);
                System.out.print('|');
            }
            System.out.println();
        }
    }

    public void printEnemyMap (){
        enemyMap.printMap();
    }

    public  void setPlayerPosition(int x, int y, int lx, int ly){
        map[lx][ly] = OPENCELS;
        map[x][y] = PLAYER;
    }

    public static boolean validationMove(int x, int y){
        if (x > sizeX-1 || y > sizeY-1 || x < 0 || y < 0){
            return false;
        }else {
            return true;
        }
    }

    public void enemySpawn(int x, int y){
        map[x][y] = ENEMY;
    }

    public  void deleteEnemy(int x, int y) {
        enemyMap.map[x][y] = 'X';
    }
}
