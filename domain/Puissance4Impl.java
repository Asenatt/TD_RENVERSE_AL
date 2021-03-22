package domain;


public class Puissance4Impl implements Puissance4 {
	/*
	 * Volatile is to avoid the case in which a thread thinks 
	 * mistakingly that another thread has instantiated the object
	 * but in fact the instantiation is not finished
	 * */
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
		_tab = new P4Player[HEIGHT][WIDTH];
		for (int line = 0; line < HEIGHT; ++line)
			for (int col = 0; col < WIDTH; ++col)
				_tab[line][col] = null;
		_finished = false;
		_freePlaces = WIDTH * HEIGHT;
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
		for (int line = HEIGHT-1; line >= 0; --line){
			bld.beginRow();
			for (int col = 0; col < WIDTH; ++col){
				bld.beginColumn();
				if (_tab[line][col] == _p1)
					bld.addString("X");
				if (_tab[line][col] == null)
					bld.addString(" ");
				if (_tab[line][col] == _p2)
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
		int line = getHighestAvailableLine(col);
		if (line >= HEIGHT)
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
		int line = getHighestAvailableLine(col);
		_tab[line][col] = _player;
		if (testwin(line, col)) {
			System.out.println("player " + _player + " win");
			_finished = true;
			return;
		}
		_player = switchPlayer();
	}
	
	/*
	 * Tests if a player won by checking if 4 tokens are aligned diagonally, in a line or in column 
	 * */
	public boolean testwin(int line, int col) {
		int length = 1 , height = 1, diagonal1 = 1, diagonal2 = 1;
		P4Player p = _tab[line][col];

		for (int x = line + 1; x < HEIGHT && _tab[x][col] == p; ++x) ++height;
		for (int x = line - 1; x >=  0   && _tab[x][col] == p; --x) ++height;

		for (int x = col + 1; x < WIDTH && _tab[line][x] == p; ++x) ++length;
		for (int x = col - 1; x >=  0   && _tab[line][x] == p; --x) ++length;

		for (int x = line + 1, y = col + 1; x < HEIGHT && y < WIDTH && _tab[x][y] == p; ++x, ++y) ++diagonal1;
		for (int x = line - 1, y = col - 1; x >= 0 && y >= 0 && _tab[x][y] == p; --x, --y) ++diagonal1;

		for (int x = line + 1, y = col - 1; x < HEIGHT && y >= 0     && _tab[x][y] == p; ++x, --y) ++diagonal2;
		for (int x = line - 1, y = col + 1; x >= 0    && y < WIDTH && _tab[x][y] == p; --x, ++y) ++diagonal2;
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
		int line = getHighestAvailableLine(col);
		_tab[line][col] = player;
		boolean result = testwin(line, col);
		_tab[line][col] = null;
		return result;
	}
	
	private int getHighestAvailableLine(int col) {
		int line = 0;
		while(line < HEIGHT && _tab[line][col] != null)
			++line;
		return line;
	}

}
