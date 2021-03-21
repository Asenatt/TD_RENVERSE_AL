package domain;
import Factory.*;
import ui.*;

public class Power4Game {
	/*
	 * 	Runs the game
	 * 
	 * */
	public static void run(Puissance4 board) {
		TextBuilder bld = new TextBuilder();
		while(!board.isFinished()) {
			//System.out.println(board);
			board.buildPuissance4(bld);
			System.out.println(bld.getPuissance4());
			System.out.println("Player " + board.currentPlayer() +  " turn");
			board.play(board.currentPlayer().play());
		}		
		//System.out.println(board);
		board.buildPuissance4(bld);
		System.out.println(bld.getPuissance4());
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


