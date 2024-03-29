package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

class PlacementPanelListener extends ChangeListener {
	Grid grid;
	GameState gamestate;
	PlacementPanel placementpanel;
	public PlacementPanelListener (Grid g, GameState gs, PlacementPanel pp) {
		grid = g;
		gamestate = gs;
		placementpanel = pp;
		// Connect to view
		for (int i = 0; i < pp.actors.length; i++) {
			for (int j = 0; j < pp.actors [i].length; j++) {
				pp.actors[i][j].addListener(this);
			}
		}
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		GridButton b = (GridButton) actor;
    	if (gamestate.selectedShip == null) {
    		return;
    	}
		Ship selected = gamestate.getSelectedShip();
		if(selected.isPlaced()){
			grid.removeShip(selected);
			
		}
		int x = selected.getX();
		int y = selected.getY();
		selected.setLocation(b.x, b.y);
		if(grid.addShip(selected)){
			for (int i = 0; i < selected.getWidth(); i++) {
				for (int j = 0; j < selected.getHeight(); j++) {
					GridButton g = ((GridButton) placementpanel.actors[b.x+i][grid.getNumCellsY() -1 -(b.y+j)]);
					g.setText("s");
				}
			}
			gamestate.selectedShip = null;
		}
		else{
			selected.setLocation(x, y);
		}
	}
}

public class PlacementPanel extends GridButtonPanel{
	Grid grid;
	public PlacementPanel (Grid g) {
		super(g.getNumCellsX(), g.getNumCellsY(), "~");
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());
		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		makeButtonGrid(skin);
	}
	public void setPlacement(Grid g){
		grid = g;
		for(int x = 0; x < grid.getNumCellsX(); x++){
			for(int y = 0; y < grid.getNumCellsY(); y++){
				if(grid.hasShip(x, y)){
					((TextButton) this.actors[x][y]).setText("s");
				}
			}
		}	
	}
}
