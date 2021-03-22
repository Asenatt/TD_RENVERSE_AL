package domain;

/*
 * Best bot, goes for the ez win
 * */
public class DirectWinBot extends DecoratorBot{

	public DirectWinBot(Puissance4 board, P4Player bot) {
		super(bot, board);
	}
	
	/*
	 * Checks if move is correct with 'checkWin' as expected
	 * */
	public int play() {
		for (int i = 0; i < Puissance4.WIDTH; ++i) { 
			if (_board.checkWin(i, this))
				return i;
		}
		return _bot.play();
	}

}
