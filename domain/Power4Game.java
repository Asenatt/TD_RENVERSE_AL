package domain;
import Factory.*;

public class Power4Game {
	/*
	 * 	Runs the game
	 * 
	 * */
	public static void run(Puissance4 board) {
		while(!board.end()) {
			System.out.println(board);
			System.out.println("Player " + board.currentPlayer() +  " turn");
			board.play(board.currentPlayer().play());
		}		
		System.out.println(board);
	}
	public static void main(String argv[]) {
		Puissance4 board = Puissance4Impl.getInstance();
		BotFactory directWinBotFactory = new DirectWinBotFactory();
		BotFactory humanBotFactory = new HumanBotFactory();
		P4Player p1 = humanBotFactory.createBot(board);
		//P4Player p2 = new DirectWinBot(board, new OpponentBot(board, new RandomBot(board)));
		
		P4Player p2 = directWinBotFactory.createBot(board);
		board.init(p1, p2);
		run(board);
	}

}


