package classes;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
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

		map = new int[height][width];
		JsonArray layers = root.getJsonArray("layers");
		JsonArray data = layers.getJsonObject(0).getJsonArray("data");
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[y][x] = data.getInt(y * width + x);
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
