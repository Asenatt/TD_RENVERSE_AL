package domain;


public interface Puissance4 {
	public static final int WIDTH  = 7;
	public static final int HEIGHT = 7;
	public void init(P4Player p1, P4Player p2);
	public P4Player getPlayer1();
	public P4Player getPlayer2();
	public P4Player currentPlayer();
	public boolean isFinished();
	public void play(int col);
	public String toString();
	public void buildPuissance4(P4Builder bld);
	public boolean isFree(int col);
	public boolean checkWin(int col, P4Player player);
}
