package domain;
import java.util.Scanner;

public class HumanPlayer implements P4Player {
	Scanner sc = new Scanner(System.in);
	private Puissance4 _board;
	public HumanPlayer(Puissance4 board) {
		_board = board;
	}
	/*
	 * Checks if move is correct with 'isFree()' as expected
	 * */
	public int play() {
		int move = -1;
		while (!_board.isFree(move)) {
			System.out.println("Enter column :");
			try {
				String str = sc.nextLine();
				move = Integer.parseInt(str);
			} catch (Exception e) {
				System.out.println("Bad width value" + e.toString());
			}
		}
		return move;
	}
	

}
