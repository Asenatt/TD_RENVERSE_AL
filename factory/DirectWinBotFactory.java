package factory;

import domain.*;
public class DirectWinBotFactory implements BotFactory {

	public DirectWinBotFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public P4Player createBot(Puissance4 board) {
		return new DirectWinBot(board, new OpponentBot(board, new RandomBot(board)));
	}

}
