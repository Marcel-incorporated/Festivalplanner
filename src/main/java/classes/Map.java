package classes;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
	private int width;
	private int height;

	private int tileHeight;
	private int tileWidth;

	private ArrayList<BufferedImage> tiles = new ArrayList<>();

	private int[][] map;

	public Map(String fileName) throws FileNotFoundException {

		JsonReader reader = null;

		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName);
		reader = Json.createReader(new FileInputStream(file));
		JsonObject root = reader.readObject();

		this.width = root.getInt("width");
		this.height = root.getInt("height");

		//load the tilemap
		try {
			BufferedImage tilemap = ImageIO.read(getClass().getClassLoader().getResourceAsStream(root.getJsonObject("tilemap").getString("file")));

			tileHeight = root.getJsonObject("tilemap").getJsonObject("tile").getInt("height");
			tileWidth = root.getJsonObject("tilemap").getJsonObject("tile").getInt("width");

			for(int y = 0; y < tilemap.getHeight(); y += tileHeight) {
				for(int x = 0; x < tilemap.getWidth(); x += tileWidth) {
					tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		map = new int[height][width];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				map[y][x] = root.getJsonArray("map").getJsonArray(y).getInt(x);
			}
		}
	}

	public void draw(Graphics2D g2d) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(map[y][x] < 0)
					continue;

				g2d.drawImage(
					tiles.get(map[y][x]),
					AffineTransform.getTranslateInstance(x*tileWidth, y*tileHeight),
					null);
			}
		}
	}

}
