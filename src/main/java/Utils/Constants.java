package Utils;

import static Main.Game.SCALE;

import Main.Game;

public class Constants {

	public static final float GRAVITY = 0.04f * SCALE;
	public static final int ANI_SPEED = 25;
	
	public static class Environment {
		public static final int BG_LAYER2_WIDTH_DEFAULT = 320;
		public static final int BG_LAYER2_HEIGHT_DEFAULT = 180;
		public static final int BG_LAYER2_WIDTH = (int) (BG_LAYER2_WIDTH_DEFAULT * SCALE);
		public static final int BG_LAYER2_HEIGHT = (int) (BG_LAYER2_HEIGHT_DEFAULT * SCALE);

		public static final int BG_LAYER3_WIDTH_DEFAULT = 320;
		public static final int BG_LAYER3_HEIGHT_DEFAULT = 180;
		public static final int BG_LAYER3_WIDTH = (int) (BG_LAYER3_WIDTH_DEFAULT * SCALE);
		public static final int BG_LAYER3_HEIGHT = (int) (BG_LAYER3_HEIGHT_DEFAULT * SCALE);
	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT / 2 * SCALE);
		}

		public static class UrmButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE / 1.2 * SCALE);
		}
		
		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			//TODO: Wrong width
			public static final int SLIDER_DEFAULT_WIDTH = 160;
			
			
			public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * SCALE);
			public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * SCALE);
			public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * SCALE);
			
		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int ATTACK = 1;
		public static final int RUN = 2;
		public static final int JUMP = 3;
		public static final int FALL = 4;
		public static final int HIT = 5;
		public static final int DEAD = 6;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case IDLE:
				return 6;
			case ATTACK:
				return 6;
			case RUN:
				return 8;
			case JUMP:
				return 8;
			case FALL:
				return 8;
			case HIT:
				return 8;
			case DEAD:
				return 4;
			default:
				return 4;
			}
		}
	}
	
	public static class EnemyConstants {
		public static final int LIGHT_BANDIT = 0;
		
		public static final int IDLE = 0;
		public static final int RUN = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEATH = 4;
		
		public static final int LIGHT_BANDIT_WIDTH_DEFAULT = 48;
		public static final int LIGHT_BANDIT_HEIGHT_DEFAULT = 48;
		
		public static final int LIGHT_BANDIT_WIDTH = (int)(LIGHT_BANDIT_WIDTH_DEFAULT * Game.SCALE);
		public static final int LIGHT_BANDIT_HEIGHT = (int)(LIGHT_BANDIT_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int LIGHT_BANDIT_DRAWOFFSET_X = (int)(14 * Game.SCALE);
		public static final int LIGHT_BANDIT_DRAWOFFSET_Y = (int)(5 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch (enemy_type) {
			case LIGHT_BANDIT:
				switch(enemy_state) {
					case IDLE:
						return 8;
					case RUN:
						return 8;
					case ATTACK:
						return 8;
					case HIT:
						return 8;
					case DEATH:
						return 5;
					default:
						return 4;
					}
				}
			
			return 0;
		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case LIGHT_BANDIT:
				return 100; //2hits
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case LIGHT_BANDIT:
				return 25;
			default:
				return 0;
			}
		}
	}
}
