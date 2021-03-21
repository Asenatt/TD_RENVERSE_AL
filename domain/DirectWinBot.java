package domain;
public class DirectWinBot extends DecoratorBot{//opponent bot
/*
 * Best bot, goes for the ez win
 * */
	public DirectWinBot(Puissance4 board, P4Player bot) {
		super(bot, board);
	}
	public int play() {
		for (int i=0; i < Puissance4.WIDTH; ++i) { 
			if (_board.checkWin(i, this))
				return i;
		}
		return _bot.play();
	}

}
