package classes;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class AI {
    private Point2D position;
//    private double angle;
    private double speed = 1.0;
    private Point2D target;
    private int width;
    private int height;
    private int tileHeight;
    private int tileWidth;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private BufferedImage image;

    public AI(Point2D position) throws FileNotFoundException {
        this.position = position;
        this.target = new Point2D.Double(Math.random() * 1000, Math.random() * 1000);
//        this.angle = Math.random() * 2 * Math.PI;
//        try {
//            image = ImageIO.read(this.getClass().getResource("/bomb_party_v3.png"));
//            image = image.getSubimage(0, 0, 16, 16);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

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

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 2);
//        tx.rotate(angle + Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
        g.drawImage(image, tx, null);
//        g.setColor(Color.pink);
//        g.draw(new Ellipse2D.Double(position.getX() - 25, position.getY() - 25, 50, 50));
    }

    public void update(ArrayList<AI> others) {
//        double angleTo = Math.atan2(target.getY() - position.getY(), target.getX() - position.getX());
        for (AI other : others) {
            other.setTarget(new Point2D.Double(position.getX(), position.getY() - 16));
        }

//        double diff = angleTo - angle;
//        if (diff < -Math.PI) diff += 2 * Math.PI;
//        if (diff > Math.PI) diff -= 2 * Math.PI;
//
//        if (Math.abs(diff) < 0.1) angle = angleTo;
//        else if (diff > 0) angle += 0.1;
//        else angle -= 0.1;

//        Point2D oldPos = position;

        position = new Point2D.Double(position.getX(), position.getY() -16);
//
//        boolean hasCollision = false;
//
//        for (AI other : others) {
//            if (other == this) continue;
//            if (other.position.distanceSq(this.position) < 50 * 50) hasCollision = true;
//        }
//
//        if (hasCollision) {
//            position = oldPos;
//            angle += 0.2;
//        }

    }

    public void setTarget(Point2D point) {
        this.target = point;
    }

    public Point2D getPosition() {
        return position;
    }
}


