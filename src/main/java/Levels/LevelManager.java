package Levels;

import Main.Game;
import Utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Main.Game.TILES_SIZE;
import static Main.Game.TILES_IN_HEIGHT;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;

	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		levelOne = new Level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage imgBufferedImage = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[315]; // 21 * 15 (tilesX * tilesY)
		for (int j = 0; j < 15; j++)
			for (int i = 0; i < 21; i++) {
				int index = j * 21 + i;
				levelSprite[index] = imgBufferedImage.getSubimage(i * 24, j * 24, 24, 24);
			}
	}

	public void draw(Graphics g, int levelOffset) {

		for (int j = 0; j < TILES_IN_HEIGHT; j++)
			for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], TILES_SIZE * i - levelOffset, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
			}
	}

	public void update() {

	}

	public Level getCurrentLevel() {
		return levelOne;
	}

}
