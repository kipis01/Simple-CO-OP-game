package Main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null); // center the window
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				gamePanel.getGame().windowFocusLost();

			}

			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}