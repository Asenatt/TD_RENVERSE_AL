package domain;


public class Puissance4Impl implements Puissance4 {

	private static volatile Puissance4Impl instance;
	
	private P4Player [][] _tab;
	private boolean _finished;
	private int _freePlaces;
	private P4Player _player, _p1, _p2;
	public P4Player currentPlayer() {
		return _player;
	}

	public P4Player getPlayer1() {
		return _p1;
	}
	public P4Player getPlayer2() {
		return _p2;
	}
	
	private Puissance4Impl() {
		
	}
	/* Double checked locking:
	 * https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java 
	 * */
	public static Puissance4Impl getInstance() {
		Puissance4Impl result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Puissance4Impl.class) {
            if (instance == null) {
                instance = new Puissance4Impl();
            }
            return instance;
        }
	}
	
	public void init(P4Player p1, P4Player p2) {
		_p1 = p1;
		_p2 = p2;
		_player = _p1;
		_tab = new P4Player[WIDTH][HEIGHT];
		for (int i=0; i < WIDTH; ++i)
			for (int j=0; j < HEIGHT; ++j)
				_tab[i][j] = null;
		_finished = false;
		_freePlaces = WIDTH * HEIGHT;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("***************\n");
		for (int i=WIDTH-1; i >=0; --i) {
			str.append("|");
			for (int j=0; j < HEIGHT; ++j) {
				if (_tab[i][j] == _p1)
					str.append("X");
				if (_tab[i][j] == null)
					str.append(" ");
				if (_tab[i][j] == _p2)
					str.append("O");
				str.append("|");
			}
			str.append("\n");
		}
		str.append("***************\n");
		return str.toString();
	}

	
/*
 *Build the Puissance 4 depending of the builder 
 * */
	public void buildPuissance4(P4Builder bld){
		bld.createNewPuissance4();
		
		bld.beginHeader();
		bld.addString("***************");
		bld.endHeader();
		
		bld.beginTable();
		for (int i = HEIGHT-1; i >= 0; --i){
			bld.beginRow();
			for (int j = 0; j < WIDTH; ++j){
				bld.beginColumn();
				if (_tab[i][j] == _p1)
					bld.addString("X");
				if (_tab[i][j] == null)
					bld.addString(" ");
				if (_tab[i][j] == _p2)
					bld.addString("O");
				bld.endColumn();
			}
			bld.endRow();	
		}
		bld.addString("***************\n");
		bld.endTable();

		bld.finish();
	}
	
	/*
	 * Checks if the game is over or not
	 * */
	public boolean isFinished() {
		return _finished;
	}
	
	/*
	 * Checks if there is free space in a column
	 * */
	public boolean isFree(int col) {
		if(_freePlaces <= 0) return false;
		if (col <0 || col >= WIDTH) return false;
		int i=0;
		while(i < HEIGHT && _tab[i][col] != null)
			++i;
		if (i >= HEIGHT)
			return false;
		return true;

	}
	
	/*
	 * Switches players for turn by turn play
	 * */
	private P4Player switchPlayer() {
		if (_player == _p1)
			return _p2;
		else 
			return _p1;
	}
	
	
	/*
	 * Places token of a player and switches the player to the next one
	 * */
	public void play(int col) {
		if (isFinished()) return;
		--_freePlaces;
		int i=0;
		while(i < HEIGHT && _tab[i][col] != null)
			++i;
		if (i >= HEIGHT) {
			//error
		}
		_tab[i][col] = _player;
		if (testwin(i, col)) {
			System.out.println("player " + _player + " win");
			_finished = true;
			return;
		}
		_player = switchPlayer();
	}
	
	/*
	 * Tests if a player won by checking if 4 tokens are aligned diagonally, in a line or in column 
	 * */
	public boolean testwin(int i, int col) {
		int length = 1 , height = 1, diagonal1 = 1, diagonal2 = 1;
		P4Player p = _tab[i][col];

		for (int x = i + 1; x < WIDTH && _tab[x][col] == p; ++x) ++length;
		for (int x = i - 1; x >=  0   && _tab[x][col] == p; --x) ++length;

		for (int x = col + 1; x < WIDTH && _tab[i][x] == p; ++x) ++height;
		for (int x = col - 1; x >=  0   && _tab[i][x] == p; --x) ++height;

		for (int x = i + 1, y = col + 1; x < WIDTH && y < HEIGHT && _tab[x][y] == p; ++x, ++y) ++diagonal1;
		for (int x = i - 1, y = col - 1; x >= 0 && y >= 0 && _tab[x][y] == p; --x, --y) ++diagonal1;

		for (int x = i + 1, y = col - 1; x < WIDTH && y >= 0     && _tab[x][y] == p; ++x, --y) ++diagonal2;
		for (int x = i - 1, y = col + 1; x >= 0    && y < HEIGHT && _tab[x][y] == p; --x, ++y) ++diagonal2;
		//System.out.println("res " +l + " " + h + " " + d1 +  " " +d2);
		if (length >3)  return true;
		if (height >3)  return true;
		if (diagonal1 >3) return true;
		if (diagonal2 >3) return true;
		return  false;
	}
	
	/*
	 * checks if a next move is a winning one
	 * */
	public boolean checkWin(int col, P4Player player) {
		if (!isFree(col)) return false;
		int i=0;
		while(i < HEIGHT && _tab[i][col] != null)
			++i;
		_tab[i][col] = player;
		boolean result = testwin(i, col);
		_tab[i][col] = null;
		return result;
	}

}
