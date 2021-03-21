package domain;
public class OpponentBot extends DecoratorBot {

	public OpponentBot(Puissance4 board, P4Player bot) {
		super(bot, board);
	}
	public int play() {
		P4Player _opponent;
		if (_board.getPlayer1() != this)
			_opponent = _board.getPlayer1();
		else
			_opponent = _board.getPlayer2();
		for (int i=0; i < Puissance4.WIDTH; ++i) { 
			if (_board.checkWin(i, _opponent))
				return i;
		}
		return _bot.play();
	}

}
