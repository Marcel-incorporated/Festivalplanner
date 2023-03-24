package classes;

import java.awt.geom.AffineTransform;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private Matrix matrix = new Matrix(35, 56);

    public newAi(ArrayList<BufferedImage> characterImages, ArrayList<Integer> collisionMapArray, ArrayList<BufferedImage> tiles, int id) {
        this.x = 664;
        this.y = 552;
        this.index = 1945;
        this.collisionMapArray = collisionMapArray;
        this.characterImages = characterImages;
        this.tiles = tiles;
        this.id = id;

        this.image = this.characterImages.get(0);
    }

    public int getId() {
        return id;
    }

    public void update() {
        run = true;

        while (run) {
            switch (randomMove()) {
                case 1:
                    if (canMove(1)) {
                        //up
//                        matrix.updateAround(newPos.getX(), newPos.getY(), 0);
                        y -= 16;
                        index -= 56;
                        run = false;
                    }
                    break;
                case 2:
                    if (canMove(2)) {
                        //right
//                        matrix.updateAround(newPos.getX(), newPos.getY(), 0);
                        x += 16;
                        index += 1;
                        run = false;
                    }
                    break;
                case 3:
                    if (canMove(3)) {
                        //down
//                        matrix.updateAround(newPos.getX(), newPos.getY(), 0);
                        y += 16;
                        index += 56;
                        run = false;
                    }
                    break;
                case 4:
                    if (canMove(4)) {
                        //left
//                        matrix.updateAround(newPos.getX(), newPos.getY(), 0);
                        x -= 16;
                        index -= 1;
                        run = false;
                    }
                    break;
                default:
                    System.out.println("shit man");
                    break;
            }
        }
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
}
