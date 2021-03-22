package domain;

public class DecoratorBot implements P4Player {
	protected Puissance4 _board;
	protected P4Player _bot;
	public DecoratorBot(P4Player bot, Puissance4 board) {
		_board = board;
		_bot = bot;
	}

	@Override
	public int play() {
		return _bot.play();
	}

}
