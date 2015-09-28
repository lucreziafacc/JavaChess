package model;

import view.*;
import controller.*;

public interface Model {
	
	public Piece at(int x, int y);

	public void setView(View view);

	
}
