package domain;
import java.util.Random;

/*
 * Plays randomly
 * */
public class RandomBot implements P4Player {

	Puissance4 _board;
	public RandomBot(Puissance4 board) {
		_board = board;
	}
	
	/*
	 * Checks if move is correct with 'checkWin' as expected
	 * */
	public int play() {
		Random r = new Random();
		int res = r.nextInt(Puissance4.WIDTH);
		while(!_board.isFree(res))
			res = r.nextInt(Puissance4.WIDTH);
		return res;
	}
}
