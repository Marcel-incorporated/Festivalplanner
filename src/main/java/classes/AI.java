package classes;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class AI {
    private Point2D position;
    private AffineTransform lastTx;
//    private double angle;
    private double speed = 1.0;
    private Point2D target;
    private int width;
    private int height;
    private int tileHeight;
    private int tileWidth;
    private ArrayList<Integer> mapArray;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private BufferedImage image;
    private ArrayList<BufferedImage> colorTiles;
    private int indexPosition;
    private Matrix pathFindingMatrix;

    public AI(Point2D position, ArrayList<BufferedImage> colorTiles) throws FileNotFoundException {
        this.position = position;
        this.target = new Point2D.Double(Math.random() * 1000, Math.random() * 1000);
        this.colorTiles = colorTiles;
        this.indexPosition = 1945;

        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "collision.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        //Laad de tegelmap
        try {
            //Haal de tegelset JSON object op
            JsonArray tilesets = root.getJsonArray("tilesets");
            JsonObject tileset = tilesets.getJsonObject(0);

            JsonArray layers = root.getJsonArray("layers");
            JsonObject layer = layers.getJsonObject(0);

            JsonArray jsonArray = layer.getJsonArray("data");
            mapArray = getIntArray(jsonArray);

            //Haal bestandsnaam van de tegelmap op
            String fileNameTileMap = tileset.getString("image");

            //Laad de tegelmap afbeelding
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileNameTileMap);
            BufferedImage tilemapImage = ImageIO.read(inputStream);

            tileHeight = tileset.getInt("tileheight");
            tileWidth = tileset.getInt("tilewidth");

            for(int y = 0; y < tilemapImage.getHeight(); y += tileHeight) {
                for(int x = 0; x < tilemapImage.getWidth(); x += tileWidth) {
                    tiles.add(tilemapImage.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = colorTiles.get(0);

    }

    public ArrayList<Integer> getIntArray(JsonArray jsonArray) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            intArray.add(jsonArray.getInt(i));
        }
        return intArray;
    }

    public void update() {

        int north = -999;
        int south = -999;
        int west = -999;
        int east = -999;

        if (!(indexPosition - 56 < 0) && !(indexPosition - 56 > 1959)){
            north = mapArray.get(indexPosition-56);
        }
        if (!(indexPosition + 56 > 1959)){
            south = mapArray.get(indexPosition+56);
        }
        if (!(indexPosition - 1 < 0) && !(indexPosition - 1 > 1959)){
            west = mapArray.get(indexPosition-1);
        }
        if (!(indexPosition + 1 > 1959)){
            east = mapArray.get(indexPosition+1);
        }

        int direction = getRandomMove();
        switch(direction) {
            case 1:
                //up
                if (north != -999 && north != 45 && !(position.getY() - 16 < 0))
                {
                    indexPosition -= 56;
                    this.position = new Point2D.Double(position.getX(), position.getY() - 16);
//                    setTarget(position);
                }
                break;
            case 2:
                //right
                if (east != -999 && east != 45 && !(position.getX() + 16 > 896))
                {
                    indexPosition += 1;
                    this.position = new Point2D.Double(position.getX() + 16, position.getY());
//                    setTarget(position);
                }
                break;
            case 3:
                //left
                if (west != -999 && west != 45 && !(position.getX() - 16 < 0))
                {
                    indexPosition -= 1;
                    this.position = new Point2D.Double(position.getX() - 16, position.getY());
//                    setTarget(position);
                }
                break;
            case 4:
                //down
                if (south != -999 && south != 45 && !(position.getY() + 16 > 896))
                {
                    indexPosition += 56;
                    this.position = new Point2D.Double(position.getX(), position.getY() + 16);
//                    setTarget(position);
                }
                break;
        }
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight() / 2.0);
        if (lastTx != null){
            image = tiles.get(0);
            g.drawImage(image, lastTx, null);
        }
        image = colorTiles.get(0);
        g.drawImage(image, tx, null);

        lastTx = tx;
    }

//    public void update() {
//
//        // TODO ais mogen niet collide met elkaar
//
//        for (AI other : others) {
//

//
//            boolean isDone = false;
//

//
////             north = - 56
////             south = + 56
////             west = - 1
////             east = + 1

////            System.out.println(north);
////            System.out.println(south);
////            System.out.println(west);
////            System.out.println(east);
//
//            // height="560.0" width="896.0" canvas
//
//            // als er geen pathfinding matrix geset is
//            if (other.getPathFindingMatrix() == null){
//
//
//                // krijg een random move kijk of dit kan en voer deze uit
//                do {
//                    int move = getRandomMove();
//                    switch (move){
//                        case 1:
//
//                                position = new Point2D.Double(position.getX(), position.getY() - 16);
//                                other.setTarget(position);
//                                isDone = true;
////                                System.out.println("north");
//                            }
//                            break;
//                        case 2:
//                                position = new Point2D.Double(position.getX() + 16, position.getY());
//                                other.setTarget(position);
//                                isDone = true;
////                                System.out.println("east");
//                            }
//                            break;
//                        case 3:

//                                position = new Point2D.Double(position.getX(), position.getY()+16);
//                                other.setTarget(position);
//                                isDone = true;
////                                System.out.println("south");
//                            }
//                            break;
//                        case 4:
//
//                                position = new Point2D.Double(position.getX() - 16, position.getY());
//                                other.setTarget(position);
//                                isDone = true;
////                                System.out.println("west");
//                            }
//                            break;
//                    }
//                } while (!isDone);
//            }
//            else{
//                int x;
//                int y;
//
//                int[] cords = getMatrixXY(indexPosition);
//
//                x = cords[0];
//                y = cords[1];
//
//                // TODO
//                //  kijk om de cords vier kanten op noord zuid oost en west
//                //  controleer welk nummer het laagst is door in de other.getPathFindingMatrix() te kijken dit is een matrix
//                //  de laagste value is de stap die je wil maken den zei dit natuurlijk een 45 is in de mapArray de index waar je ben moet worden
//                //  bij gehouden met de indexPosition
//            }
//        }
//    }


    public void setTarget(Point2D point) {
        this.target = point;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getIndexPosition() {
        return indexPosition;
    }

    public void setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
    }

    public Matrix getPathFindingMatrix() {
        return pathFindingMatrix;
    }

    public void setPathFindingMatrix(Matrix pathFindingMatrix) {
        this.pathFindingMatrix = pathFindingMatrix;
    }

    public int getRandomMove() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1;
        //System.out.println("Random number: " + randomNumber);
        return randomNumber;
    }

    public int[] getMatrixXY(int number){
        int[] coordinates = new int[2];

        int x = 0;
        int y = 0;

        int row = 35;

        y = number / 35;
        x = number % 35;

        coordinates[0] = x;
        coordinates[1] = y;

        return coordinates;
    }

}


