package factory;
import domain.*;

public interface BotFactory {
	public P4Player createBot(Puissance4 board);
}
