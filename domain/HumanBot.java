package domain;
import java.util.Scanner;

public class HumanBot implements P4Player {
	Scanner sc = new Scanner(System.in);
	private Puissance4 _board;
	public HumanBot(Puissance4 board) {
		_board = board;
	}
	public int play() {
		//5Scanner sc = new Scanner(System.in);
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
		//sc.close();
		return move;
	}
	

}
