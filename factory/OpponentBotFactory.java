package factory;

import domain.OpponentBot;
import domain.P4Player;
import domain.Puissance4;
import domain.RandomBot;

public class OpponentBotFactory implements BotFactory {

	public OpponentBotFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public P4Player createBot(Puissance4 board) {
		return new OpponentBot(board, new RandomBot(board));
	}

}
