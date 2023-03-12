package classes;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    private int indexFollow = 1945;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private BufferedImage image;

    public AI(Point2D position) throws FileNotFoundException {
        this.position = position;
        this.target = new Point2D.Double(Math.random() * 1000, Math.random() * 1000);

        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "map.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        //load the tilemap
        try {
            // Get the tileset JSON object
            JsonArray tilesets = root.getJsonArray("tilesets");
            JsonObject tileset = tilesets.getJsonObject(0);

            JsonArray layers = root.getJsonArray("layers");
            JsonObject layer = layers.getJsonObject(0);

            JsonArray jsonArray = layer.getJsonArray("data");
            mapArray = getIntArray(jsonArray);

            // Get the file name of the tilemap
            String fileNameTileMap = tileset.getString("image");

            // Load the tilemap image
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

        image = tiles.get(16);

    }

    public ArrayList<Integer> getIntArray(JsonArray jsonArray) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            intArray.add(jsonArray.getInt(i));
        }
        return intArray;
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 2);

        image = tiles.get(16);
        g.drawImage(image, tx, null);

        if (lastTx != null){
            image = tiles.get(0);
            g.drawImage(image, lastTx, null);
        }

        lastTx = tx;
    }

    public void update(ArrayList<AI> others) {
        for (AI other : others) {

            int nextValue = mapArray.get(indexFollow-56);

            indexFollow = indexFollow-56;

            System.out.println(nextValue);

            if (nextValue == 3){
                System.out.println("grass infront we have to stop now!");
            }
            else{
                position = new Point2D.Double(position.getX(), position.getY() - 16);
                other.setTarget(position);
            }
        }
    }

    public void setTarget(Point2D point) {
        this.target = point;
    }

    public Point2D getPosition() {
        return position;
    }
}


