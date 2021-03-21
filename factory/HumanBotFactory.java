package Factory;

import domain.HumanBot;
import domain.P4Player;
import domain.Puissance4;

public class HumanBotFactory implements BotFactory {

	public HumanBotFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public P4Player createBot(Puissance4 board) {
		return new HumanBot(board);
	}

}
