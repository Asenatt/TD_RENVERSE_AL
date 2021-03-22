package ui;

import java.lang.StringBuilder;

public class TextBuilder implements domain.P4Builder {
  private StringBuilder _string = new StringBuilder();

  @Override
  public void createNewPuissance4(){
    _string = new StringBuilder();
  }

  @Override
  public void beginHeader(){
  }
  @Override
  public void endHeader(){
    _string.append("\n");
  }

  @Override
  public void beginTable(){
  }
  @Override
	public void endTable(){
  }
	
  @Override
	public void beginRow(){
  }
  @Override
	public void endRow(){
    _string.append("|\n");
  }
	
  @Override
	public void beginColumn(){
    _string.append("|");
  }
  @Override
	public void endColumn(){
  }
	
  @Override
	public void beginParagraph(){
    _string.append("\n");
  }
  @Override
	public void endParagraph(){
  }

  @Override
	public void addString(String s){
    _string.append(s);
  }
	
  @Override
	public void finish(){
  }

  public String getPuissance4(){
    return _string.toString();
  }
}