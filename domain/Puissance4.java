package domain;


public interface Puissance4 {
	public static final int WIDTH  = 7;
	public static final int HEIGHT = 7;
	public void init(P4Player p1, P4Player p2);
	public P4Player player1();
	public P4Player player2();
	public P4Player currentPlayer();
	public boolean end();
	public void play(int col);
	public String toString();
	public boolean isFree(int col);
	public boolean checkWin(int col, P4Player player);
}
