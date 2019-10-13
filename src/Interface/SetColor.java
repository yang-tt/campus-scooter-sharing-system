package Interface;

import java.awt.Color;

enum SetColor{
	red(Color.RED), orange(Color.ORANGE),yellow(Color.YELLOW),
	green(Color.GREEN),gray(Color.GRAY),blue(Color.BLUE),
	pink(Color.pink);
	
	private Color c;
	
	
	private SetColor(Color c)
	{
		this.c = c;
	}
	
	public Color getMyColor() {
		return c;
	}
}
