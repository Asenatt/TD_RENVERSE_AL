package domain;
import java.util.Random;

public class DecoratorBot implements P4Player {
	Puissance4 _board;
	P4Player _bot;
	public DecoratorBot(P4Player bot, Puissance4 board) {
		_board = board;
		_bot = bot;
	}

	@Override
	public int play() {
		return _bot.play();
	}

}
