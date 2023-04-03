package classes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * De AI klasse zorgt er voor dat de characters
 * die in de simulatie zitten logica krijgen waardoor ze dus weten hoe ze moeten bewegen.
 */
public class AI {
    //    private final int width = 896;
//    private final int height = 560;
    private AffineTransform lastTx;
    private BufferedImage image;
    private ArrayList<BufferedImage> characterImages;
    private ArrayList<Integer> collisionMapArray;
    private ArrayList<BufferedImage> tiles;
    private int index;
    private int id;
    private int x;
    private int y;
    //    private boolean run;
    private Matrix matrix;
    private ArrayList<Matrix> matrixes = new ArrayList<>();
    private int row = 34;
    private int collom = 41;
    //    private Matrix exitPath;
//    private String previousPathFindingMove;
//    private int previousLowestValue;
    private int matrixCount = 0;
    private boolean isFinished = true;
    private boolean isFest = false;
    private String status = "";
    //    private int ticker = 0;
//    private int stage = 0;
    private boolean isJustSpawned;
    private int goToPodium = 0;

    public AI(ArrayList<BufferedImage> characterImages, ArrayList<Integer> collisionMapArray, ArrayList<BufferedImage> tiles, int id, ArrayList<Matrix> matrixes) {
        this.x = 664;
        this.y = 552;
        this.index = 1945;
        this.collisionMapArray = collisionMapArray;
        this.characterImages = characterImages;
        this.tiles = tiles;
        this.id = id;
        this.matrixes = matrixes;
        this.isJustSpawned = true;
        this.image = this.characterImages.get(0);
    }

    public boolean isFest() {
        return isFest;
    }

    public int getId() {
        return id;
    }

    public void setGoToPodium(int goToPodium) {
        this.goToPodium = goToPodium;
    }


//    public void setStage(String stage) {
//        switch (stage) {
//            case "Main stage" -> this.stage = 1;
//            case "Stage 2" -> this.stage = 2;
//            case "Stage 3" -> this.stage = 3;
//            case "Stage 4" -> this.stage = 4;
//        }
//    }


//    public void setMatrix(Matrix matrix) {
//        this.matrix = matrix;
//    }


//    public int getTicker() {
//        return ticker;
//    }

//    public void setTicker(int ticker) {
//        this.ticker = ticker;
//    }

    public void update() {
        if (this.matrixes != null) {
            if (matrixes.size() > matrixCount && isFinished) {
                matrix = matrixes.get(matrixCount);
                matrixCount++;
//                System.out.println("next");
                isFinished = false;
            }
        }

        if (this.matrix == null) {
            //random moven als AI geen taak heeft
            if (goToPodium == 0 && !isFest && status.equals("")) {
//                System.out.println("going random");
                int random = getRandom7();
                if (random == 1) {
                    setMatrixes(getToiletMatrixes());
                }
                if (random == 2) {
                    setMatrixes(getBlueShopMatrixes());
                }
                if (random == 3) {
                    setMatrixes(getOrangeShopMatrixes());
                }
            }
            if (goToPodium == 1 && !isFest) {
                setFest(true);
                setStatus("mainStage");
                System.out.println("going mainstage");
                setMatrixes(getMainStageMatrixes());
            }
            if (goToPodium == 2 && !isFest) {
                setFest(true);
                setStatus("leftTinyStage");
                System.out.println("going left stage");
                setMatrixes(getLeftTinyStageMatrixes());
            }
            if (goToPodium == 3 && !isFest) {
                setFest(true);
                setStatus("middleTinyStage");
                System.out.println("going middle stage");
                setMatrixes(getMiddleTinyStageMatrixes());
            }
            if (goToPodium == 4 && !isFest) {
                setFest(true);
                setStatus("rightTinyStage");
                System.out.println("going right stage");
                setMatrixes(getRightTinyStageMatrixes());
            }
        } else {
            //34, 41 = spawn
            //logic om naar de laagste value te lopen in de matrix
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

            //System.out.println(lowestvalue);
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
            if (lowestvalue == 0) {
                this.isFinished = true;

                if (this.matrixes.size() == this.matrixCount) {
                    if (status.equals("mainStageBack") || status.equals("leftTinyStageBack") || status.equals("rightTinyStageBack") || status.equals("middleTinyStageBack")) {
                        setFest(false);
                        setStatus("");
                        goToPodium = 0;
                    }
                    isJustSpawned = false;
                    this.matrixes = null;
                    this.matrix = null;
                    matrixCount = 0;
                }
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        System.out.println("Status geset naar: " + status);
        this.status = status;
    }

    public void setFest(boolean fest) {
        isFest = fest;
    }

    public void setMatrixes(ArrayList<Matrix> matrixes) {
        this.matrixes = matrixes;
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        //locatie zetten op x en y
        tx.translate(x - image.getWidth() / 2.0, y - image.getHeight() / 2.0);
        //vorige AI overtekenen met pad
        if (lastTx != null) {
            image = tiles.get(0);
            g.drawImage(image, lastTx, null);
        }
        image = characterImages.get(0);
        //AI tekenen op nieuwe positie
        g.drawImage(image, tx, null);
        //huidige positie opslaan om later te overtekenen
        lastTx = tx;
    }

    public static int getRandom7() {
        Random rand = new Random();
        return rand.nextInt(7) + 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

//    int getIndexPos(int x, int y) {
//        return x + this.width * y;
//    }

//    void andersom(int index) {
//        this.x = index % this.width;
//        this.y = index / this.width;
//    }

//    private int randomMove() {
//        Random rand = new Random();
//        int randomNumber = rand.nextInt(4) + 1;
//        //System.out.println("Random number: " + randomNumber);
//        return randomNumber;
//    }

    public boolean canMove(int move) {
        //logic om te controleren of ai kan moven naar nieuwe positie
        int north = -999;
        int south = -999;
        int west = -999;
        int east = -999;

        //value voor de volgende move instellen
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

        //checken of er in de volgende move geen uit de map value, of collision tile is
        switch (move) {
            case 1:
                if (north != -999 && north != 45) {
                    return true;
                }
                break;
            case 2:
                if (east != -999 && east != 45) {
                    return true;
                }
                break;
            case 3:
                if (south != -999 && south != 45) {
                    return true;
                }
                break;
            case 4:
                if (west != -999 && west != 45) {
                    return true;
                }
                break;
            default:
                System.out.println("shit man");
        }
        return false;
    }

    public boolean isJustSpawned() {
        return isJustSpawned;
    }

    //    public void makeExitPath() {
//        exitPath = new Matrix(35, 56);
//        exitPath.updateAround(34, 41, 0);
//    }


    public ArrayList<Matrix> getRightTinyStageMatrixes() {
//        System.out.println("right tiny matrix");
        ArrayList<Matrix> rightTinyStageMatrixes = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 4, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 7, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(17, 36, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(19, 37, 0);

        Matrix checkpoint5 = new Matrix(35, 56);
        checkpoint5.updateAround(19, 43, 0);

        Matrix checkpoint6 = new Matrix(35, 56);
        checkpoint6.updateAround(6, 51, 0);

        rightTinyStageMatrixes.add(checkpoint1);
        rightTinyStageMatrixes.add(checkpoint2);
        rightTinyStageMatrixes.add(checkpoint3);
        rightTinyStageMatrixes.add(checkpoint4);
        rightTinyStageMatrixes.add(checkpoint5);
        rightTinyStageMatrixes.add(checkpoint6);

        return rightTinyStageMatrixes;
    }

    public ArrayList<Matrix> getMiddleTinyStageMatrixes() {
//        System.out.println("middle tiny matrix");
        ArrayList<Matrix> middleTinyStagePath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 4, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 7, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(17, 15, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(29, 16, 0);

        Matrix checkpoint5 = new Matrix(35, 56);
        checkpoint5.updateAround(31, 26, 0);

        middleTinyStagePath.add(checkpoint1);
        middleTinyStagePath.add(checkpoint2);
        middleTinyStagePath.add(checkpoint3);
        middleTinyStagePath.add(checkpoint4);
        middleTinyStagePath.add(checkpoint5);

        return middleTinyStagePath;
    }

    public ArrayList<Matrix> getLeftTinyStageMatrixes() {
//        System.out.println("left tiny matrix");
        ArrayList<Matrix> leftTinyStagePath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 4, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 7, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(17, 12, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(31, 15, 0);

        Matrix checkpoint5 = new Matrix(35, 56);
        checkpoint5.updateAround(30, 5, 0);

        leftTinyStagePath.add(checkpoint1);
        leftTinyStagePath.add(checkpoint2);
        leftTinyStagePath.add(checkpoint3);
        leftTinyStagePath.add(checkpoint4);
        leftTinyStagePath.add(checkpoint5);

        return leftTinyStagePath;
    }

    public ArrayList<Matrix> getToiletMatrixes() {
//        System.out.println("toilet");

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


    public ArrayList<Matrix> getBlueShopMatrixes() {
//        System.out.println("blue");

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

    public ArrayList<Matrix> getOrangeShopMatrixes() {
//        System.out.println("orange");

        ArrayList<Matrix> toiletPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(5, 5, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(5, 3, 0);

        toiletPath.add(checkpoint1);
        toiletPath.add(endLocation);

        return toiletPath;
    }

    public ArrayList<Matrix> getMainStageMatrixes() {
//        System.out.println("Main stage");

        ArrayList<Matrix> mainPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(5, 5, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 5, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(17, 31, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(11, 31, 0);

        Matrix checkpoint5 = new Matrix(35, 56);
        checkpoint5.updateAround(11, 24, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 24, 0);

        mainPath.add(checkpoint1);
        mainPath.add(checkpoint2);
        mainPath.add(checkpoint3);
        mainPath.add(checkpoint4);
        mainPath.add(checkpoint5);
        mainPath.add(endLocation);

        return mainPath;
    }

    public ArrayList<Matrix> getBackFromMainStageMatrixes() {
//        System.out.println("Back from main stage");

        ArrayList<Matrix> mainPath = new ArrayList<>();

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 4, 0);

        Matrix checkpoint5 = new Matrix(35, 56);
        checkpoint5.updateAround(17, 5, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(17, 31, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(11, 31, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(11, 24, 0);

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(6, 24, 0);

        mainPath.add(checkpoint1);
        mainPath.add(checkpoint2);
        mainPath.add(checkpoint3);
        mainPath.add(checkpoint4);
        mainPath.add(checkpoint5);
        mainPath.add(endLocation);

        return mainPath;
    }

    public ArrayList<Matrix> getBackFromLeftTinyStage() {
//        System.out.println("Back from left tiny stage");

        ArrayList<Matrix> mainPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(30, 15, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 15, 0);

        Matrix checkPoint3 = new Matrix(35, 56);
        checkPoint3.updateAround(17, 7, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 4, 0);

        mainPath.add(checkpoint1);
        mainPath.add(checkpoint2);
        mainPath.add(checkPoint3);
        mainPath.add(endLocation);

        return mainPath;
    }

    public ArrayList<Matrix> getBackFromMiddleTinyStage() {
//        System.out.println("Back from middle tiny stage");
        ArrayList<Matrix> mainPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(30, 17, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 15, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(17, 7, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 4, 0);

        mainPath.add(checkpoint1);
        mainPath.add(checkpoint2);
        mainPath.add(checkpoint3);
        mainPath.add(endLocation);

        return mainPath;
    }

    public ArrayList<Matrix> getBackFromRightTinyStage() {
//        System.out.println("Back from right tiny stage");
        ArrayList<Matrix> mainPath = new ArrayList<>();

        Matrix checkpoint1 = new Matrix(35, 56);
        checkpoint1.updateAround(18, 43, 0);

        Matrix checkpoint2 = new Matrix(35, 56);
        checkpoint2.updateAround(17, 18, 0);

        Matrix checkpoint3 = new Matrix(35, 56);
        checkpoint3.updateAround(16, 12, 0);

        Matrix checkpoint4 = new Matrix(35, 56);
        checkpoint4.updateAround(17, 7, 0);

        Matrix endLocation = new Matrix(35, 56);
        endLocation.updateAround(6, 4, 0);

        mainPath.add(checkpoint1);
        mainPath.add(checkpoint2);
        mainPath.add(checkpoint3);
        mainPath.add(checkpoint4);
        mainPath.add(endLocation);

        return mainPath;
    }
}
