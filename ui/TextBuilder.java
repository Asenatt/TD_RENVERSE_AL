package ui;

import java.lang.StringBuilder;

public class TextBuilder implements domain.P4Builder {
  private StringBuilder _string = new StringBuilder();

  public void createNewPuissance4(){
    _string = new StringBuilder();
  }

  public void beginHeader(){
  }
  public void endHeader(){
    _string.append("\n");
  }

  public void beginTable(){
  }
	public void endTable(){
  }
	
	public void beginRow(){
  }
	public void endRow(){
    _string.append("|\n");
  }
	
	public void beginColumn(){
    _string.append("|");
  }
	public void endColumn(){
  }
	
	public void beginParagraph(){
    _string.append("\n");
  }
	public void endParagraph(){
  }

	public void addString(String s){
    _string.append(s);
  }
	
	public void finish(){
  }

  public String getPuissance4(){
    return _string.toString();
  }
}