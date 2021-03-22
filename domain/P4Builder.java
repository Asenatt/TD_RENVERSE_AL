package domain;

public interface P4Builder {

  public void createNewPuissance4();

  public void beginHeader();
  public void endHeader();

  public void beginTable();
  public void endTable();
	
  public void beginRow();
  public void endRow();
	
  public void beginColumn();
  public void endColumn();
	
  public void beginParagraph();
  public void endParagraph();

  public void addString(String s);
	
  public void finish();

}