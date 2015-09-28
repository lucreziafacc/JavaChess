package view;

import model.*;

public interface View {

	ChessModel getModel();

	// 4: I've changed
	void onConfigurationChange();
}
