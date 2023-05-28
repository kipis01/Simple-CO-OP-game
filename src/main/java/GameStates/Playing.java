package GameStates;

import Entities.EnemyManager;
//import Entities.NPC.PrisonGuard;
import Entities.Player;
import Levels.LevelManager;
import Ui.PauseOverlay;
import Utils.LoadSave;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static Main.Game.*;

public class Playing extends State implements Statemethods {
	private Player player;

	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private boolean paused = false;
	private PauseOverlay pauseOverlay;

	private int xLevelOffset;
	
	//Level border needed to offset the background when in the first 20% or the last 80%
	private int leftBorder = (int) (0.2 * GAME_WIDTH);
	private int rightBorder = (int) (0.8 * GAME_WIDTH);
	
	//Entire level width (img width)
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	
	//Remaining tiles for the level
	private int maxTilesOffset = lvlTilesWide - TILES_IN_WIDTH;
	private int maxLevelOffsetX = maxTilesOffset * TILES_SIZE;

	private BufferedImage backgroundImg, backgroundImgLayer2, backgroundImgLayer3;

	public Playing(Game game) {
		super(game);
		initClasses();

		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMG);
		backgroundImgLayer2 = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMG_LAYER_2);
		backgroundImgLayer3 = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMG_LAYER_3);
	}

	private void initClasses() {
		//Load the level
		levelManager = new LevelManager(game);
		
		//Load the enemies
		enemyManager = new EnemyManager(this);
		
		//Load the player
		player = new Player(100, 100, (int) (56 * game.SCALE), (int) (56 * game.SCALE), levelManager);
		player.loadLevelData(levelManager.getCurrentLeve().getLevelData());
		pauseOverlay = new PauseOverlay(this);;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	
	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			enemyManager.update(levelManager.getCurrentLeve().getLevelData(), player);
			
			checkCloseToBorder();
		} else {
			pauseOverlay.update();
		}
	}

	//Check when to move the background based on the player position
	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLevelOffset;

		if (diff > rightBorder)
			xLevelOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLevelOffset += diff - leftBorder;
		if (xLevelOffset > maxLevelOffsetX)
			xLevelOffset = maxLevelOffsetX;
		else if (xLevelOffset < 0)
			xLevelOffset = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		drawLayer2(g);
		drawLayer3(g);
		
		levelManager.draw(g, xLevelOffset);

		// Draw the player
		player.render(g, xLevelOffset);
	
		// Draw the enemies
		enemyManager.draw(g, xLevelOffset);

		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
	}

	private void drawLayer2(Graphics g) {
		for (int i = 0; i < 3; i++) {
			g.drawImage(backgroundImgLayer2, 0 + i * Game.GAME_WIDTH - (int) (xLevelOffset * 0.3), 0, Game.GAME_WIDTH,
					Game.GAME_HEIGHT, null);
		}
	}

	private void drawLayer3(Graphics g) {
		for (int i = 0; i < 3; i++) {
			g.drawImage(backgroundImgLayer3, 0 + i * Game.GAME_WIDTH - (int) (xLevelOffset * 0.6), 0, Game.GAME_WIDTH,
					Game.GAME_HEIGHT, null);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseDragged(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_SHIFT:
			player.setSprint(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SHIFT:
			player.setSprint(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
	}

	public void unpauseGame() {
		paused = false;
	}

	public Player getPlayer() {
		return player;
	}

}
