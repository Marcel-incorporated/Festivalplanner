package classes;

import java.awt.geom.AffineTransform;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * De newAi klasse implementeert AI voor een character in de simulatie. Deze klasse zorgt er voor dat de characters
 * die in de simulatie zitten logica krijgen waardoor ze dus weten hoe ze moeten bewegen.
 */
public class newAi {
    private final int width = 896;
    private final int height = 560;
    private AffineTransform lastTx;
    private BufferedImage image;
    private ArrayList<BufferedImage> characterImages;
    private ArrayList<Integer> collisionMapArray;
    private ArrayList<BufferedImage> tiles;
    private int index;
    private int id;
    private int x;
    private int y;
    private boolean run;
    private Matrix matrix;
    private ArrayList<Matrix> matrixes = new ArrayList<>();
    private int row = 34;
    private int collom = 41;
    private Matrix exitPath;
    private String previousPathFindingMove;
    private int previousLowestValue;
    private int matrixCount = 0;
    private boolean isFinished = true;


    public newAi(ArrayList<BufferedImage> characterImages, ArrayList<Integer> collisionMapArray, ArrayList<BufferedImage> tiles, int id) {
        this.x = 664;
        this.y = 552;
        this.index = 1945;
        this.collisionMapArray = collisionMapArray;
        this.characterImages = characterImages;
        this.tiles = tiles;
        this.id = id;

        previousPathFindingMove = "";
        previousLowestValue = -999;

        this.image = this.characterImages.get(0);
    }

    public newAi(ArrayList<BufferedImage> characterImages, ArrayList<Integer> collisionMapArray, ArrayList<BufferedImage> tiles, int id, ArrayList<Matrix> matrixes) {
        this.x = 664;
        this.y = 552;
        this.index = 1945;
        this.collisionMapArray = collisionMapArray;
        this.characterImages = characterImages;
        this.tiles = tiles;
        this.id = id;
        this.matrixes = matrixes;

        this.image = this.characterImages.get(0);
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public int getId() {
        return id;
    }

    public void update() {

        if (this.matrixes != null){
            if (matrixes.size() > matrixCount && isFinished){
                matrix = matrixes.get(matrixCount);
                matrixCount++;
                System.out.println("next");
                isFinished = false;
            }
        }

        if (this.matrix == null) {

            int random = getRandom6();

            if (random == 1){
                setMatrixes(getToiletMatrixes());
            }
            if (random == 3){
                setMatrixes(getBlueShopMatrixes());
            }
            if (random == 4){
                setMatrixes(getOrangeShopMatrixes());
            }

            //run = true;
//            while (run) {
//                switch (randomMove()) {
//                    case 1:
//                        if (canMove(1)) {
//                            //up
//                            y -= 16;
//                            index -= 56;
//                            run = false;
//                        }
//                        break;
//                    case 2:
//                        if (canMove(2)) {
//                            //right
//                            x += 16;
//                            index += 1;
//                            run = false;
//                        }
//                        break;
//                    case 3:
//                        if (canMove(3)) {
//                            //down
//                            y += 16;
//                            index += 56;
//                            run = false;
//                        }
//                        break;
//                    case 4:
//                        if (canMove(4)) {
//                            //left
//                            x -= 16;
//                            index -= 1;
//                            run = false;
//                        }
//                        break;
//                    default:
//                        System.out.println("shit man");
//                        break;
//                }
//            }
        } else {
            //34, 41 = spawn
            int north = -999;
            int east = -999;
            int south = -999;
            int west = -999;

            if (row - 1 > -1) {
                north = this.matrix.get(row - 1, collom);
            }
            if (collom + 1 <= 54) {
                east = this.matrix.get(row, collom + 1);
            }
            if (row + 1 <= 34) {
                south = this.matrix.get(row + 1, collom);
            }
            if (collom - 1 > -1) {
                west = this.matrix.get(row, collom - 1);
            }

            int lowestvalue = 999;

            if (north < lowestvalue && north != -999) {
                if (canMove(1)) {
                    lowestvalue = north;
                }
            }
            if (south < lowestvalue && south != -999) {
                if (canMove(3)) {
                    lowestvalue = south;
                }
            }
            if (east < lowestvalue && east != -999) {
                if (canMove(2)) {
                    lowestvalue = east;
                }
            }
            if (west < lowestvalue && west != -999) {
                if (canMove(4)) {
                    lowestvalue = west;
                }
            }

            System.out.println(lowestvalue);
            while (true) {
//                System.out.println("target: "  + matrix.getCollom());
//                System.out.println("current: " + collom);

                if (east == lowestvalue) {
                    //right
                    x += 16;
                    collom += 1;
                    index += 1;
                    break;
                }

                if (west == lowestvalue) {
                    //left
                    x -= 16;
                    collom -= 1;
                    index -= 1;
                    break;
                }

                if (south == lowestvalue) {
                    //down
                    y += 16;
                    row += 1;
                    index += 56;
                    break;
                }

                if (north == lowestvalue) {

                    //up
                    y -= 16;
                    row -= 1;
                    index -= 56;
                    break;
                }

            }
            if(lowestvalue == 0) {

                this.isFinished = true;

                if (this.matrixes.size() == this.matrixCount){
                    System.out.println("start random");
                    this.matrixes = null;
                    this.matrix = null;
                    matrixCount = 0;
                }
                //System.out.println("goin' to exit!");
                //makeExitPath();
                //matrix = exitPath;
            }
        }
    }

    public ArrayList<Matrix> getToiletMatrixes(){
        System.out.println("toilet");

        ArrayList<Matrix> toiletPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 4, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(1, 4, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(1, 9, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(1, 4, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 4, 0);

        toiletPath.add(checkpoint1);
        toiletPath.add(checkpoint2);
        toiletPath.add(checkpoint3);
        toiletPath.add(checkpoint4);
        toiletPath.add(endLocation);

        return toiletPath;
    }

    public ArrayList<Matrix> getBlueShopMatrixes(){
        System.out.println("blue");

        ArrayList<Matrix> toiletPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 6, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(15, 6, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(15, 3, 0);

        toiletPath.add(checkpoint1);
        toiletPath.add(checkpoint2);
        toiletPath.add(endLocation);

        return toiletPath;
    }

    public ArrayList<Matrix> getOrangeShopMatrixes(){
        System.out.println("orange");

        ArrayList<Matrix> toiletPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(5, 5, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(5, 3, 0);

        toiletPath.add(checkpoint1);
        toiletPath.add(endLocation);

        return toiletPath;
    }



    public void setMatrixes(ArrayList<Matrix> matrixes)
    {
        this.matrixes = matrixes;
    }

    public void draw(Graphics2D g) {
//        if (this.getY() > y) {

//        }
//        if (e.getValue().getY() < y) {
//            y -= 16;
//            index -= 56;
//        }
//        if (e.getValue().getX() > x) {
//            x += 16;
//            index += 1;
//        }
//        if (e.getValue().getX() < x) {
//
//        }


        AffineTransform tx = new AffineTransform();
        tx.translate(x - image.getWidth() / 2.0, y - image.getHeight() / 2.0);
        if (lastTx != null) {
            image = tiles.get(0);
            g.drawImage(image, lastTx, null);
        }
        image = characterImages.get(0);
        g.drawImage(image, tx, null);

        lastTx = tx;
    }

    public static int getRandom6() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int getIndexPos(int x, int y) {
        return x + this.width * y;
    }

    void andersom(int index) {
        this.x = index % this.width;
        this.y = index / this.width;
    }


    private int randomMove() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1;
        //System.out.println("Random number: " + randomNumber);
        return randomNumber;
    }

    public boolean canMove(int move) {
        //logic om te controleren of ai kan moven naar nieuwe positie
        int north = -999;
        int south = -999;
        int west = -999;
        int east = -999;

        if (!(index - 56 < 0)) {
            north = collisionMapArray.get(index - 56);
        }
        if (!(index + 56 > 1959)) {
            south = collisionMapArray.get(index + 56);
        }
        if (!(index - 1 < 0)) {
            west = collisionMapArray.get(index - 1);
        }
        if (!(index + 1 > 1959)) {
            east = collisionMapArray.get(index + 1);
        }

        switch (move) {
            case 1:
                if (north != -999 && north != 45) {
//                    for (newAi ai : MyAnimationTimer.realAis) {
//                        if (ai.getY() == this.getY() + 16 && ai.getX() == this.getX()) {
//                            return false;
//                        }
//                    }
                    return true;
                }
                break;
            case 2:
                if (east != -999 && east != 45) {
//                    for (newAi ai : MyAnimationTimer.realAis) {
//                        if (ai.getY() == this.getY() && ai.getX() == this.getX() + 16) {
//                            return false;
//                        }
//                    }
                    return true;
                }
                break;
            case 3:
                if (south != -999 && south != 45) {
//                    for (newAi ai : MyAnimationTimer.realAis) {
//                        if (ai.getY() == this.getY() - 16 && ai.getX() == this.getX()) {
//                            return false;
//                        }
//                    }
                    return true;
                }
                break;
            case 4:
                if (west != -999 && west != 45) {
//                    for (newAi ai : MyAnimationTimer.realAis) {
//                        if (ai.getY() == this.getY() && ai.getX() == this.getX() - 16) {
//                            return false;
//                        }
//                    }
                    return true;
                }
                break;
            default:
                System.out.println("shit man");
        }
        return false;
    }

//    public void makeExitPath() {
//        exitPath = new Matrix(35, 56);
//        exitPath.updateAround(34, 41, 0);
//    }

}
