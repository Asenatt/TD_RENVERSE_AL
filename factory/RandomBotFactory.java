package factory;
import domain.P4Player;
import domain.Puissance4;
import domain.RandomBot;

public class RandomBotFactory implements BotFactory {

	public RandomBotFactory() {
	}
	
	public P4Player createBot(Puissance4 board) {
		return new RandomBot(board);
	}


}
