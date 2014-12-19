package com.mygdx.battleship;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Battleship extends Game {
	
	public static final int WIDTH=700,HEIGHT=700;
	
	boolean paused = false;
	SpriteBatch batch;
	GameState gamestate;
	PlayerPanel p1View;
	PlayerPanel p2View;
	SelectShipPanel shipSelect;
	Table rootTable;
	SplitPane sp;
	Stage stage;
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Ship[] miltonBradley = new Ship[]{new Ship(2,1), new Ship(3,1), new Ship(3,1), new Ship(4,1), new Ship(5,1)};
		gamestate = new GameState(miltonBradley);
		rootTable = new Table();
		rootTable.setFillParent(true);
		rootTable.debug();
		p1View = new PlayerPanel(gamestate.p1Grid, gamestate.p2Grid, false);
		p2View = new PlayerPanel(gamestate.p2Grid, gamestate.p1Grid, true);
		/*rootTable.add(p2View).pad(5).expand().fill();		
		rootTable.add(p1View).pad(5).expand().fill();
		p1View.debug();
		p2View.debug();
		*/
		shipSelect = new SelectShipPanel(miltonBradley);
		rootTable.add(shipSelect).expand().fill();
		// Disables automatic rendering calls
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		
		// Create listeners
		AttackingPanelListener p1AttackingListener = new AttackingPanelListener(gamestate.p1Grid, gamestate, p1View.attackPanel);
		AttackingPanelListener p2AttackingListener = new AttackingPanelListener(gamestate.p2Grid, gamestate, p2View.attackPanel);
		PlacementPanelListener p1PlacementListener = new PlacementPanelListener(gamestate.p1Grid, gamestate, p1View.placementPanel);
		PlacementPanelListener p2PlacementListener = new PlacementPanelListener(gamestate.p2Grid, gamestate, p2View.placementPanel);
		
//		p1AttackingPanel = new PlacementPanel (gamestate.p1Grid, playerView.getWidth(), playerView.getHeight());
		//rootTable.add(new PlacementPanel(gamestate.p2Grid));//p1View);
		//p2AttackingPanel = new PlacementPanel (gamestate.p2Grid);
		stage.addActor(rootTable);
		
		//sp = new SplitPane ((Actor)p1AttackingPanel, (Actor)p2AttackingPanel, true, new Skin());
	}
	/*public void resize (int width, int height) {
		System.out.println("battleship resize "+width +" , "+ height);
	    stage.getViewport().update(width, height, true);
	    this.p1View.resize(width/2, height);
	    this.p2View.resize(width/2, height);
	}*/
	
	public void update () {
		// Check for player wins
		if (gamestate.p1Grid.isEmpty()) {
			System.out.println("Player 2 wins!");
		}
		else if (gamestate.p2Grid.isEmpty()) {
			System.out.println("Player 1 wins!");
		}
		
		// Disable buttons if it is not that player's turn
		if (gamestate.playerTurn) {
			p2View.attackPanel.setDisabled(true);
			p1View.attackPanel.setDisabled(false);
		}
		else {
			p1View.attackPanel.setDisabled(true);
			p2View.attackPanel.setDisabled(false);
		}
	}
	@Override
	public void render () {
		if (!paused) {
			update ();
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    //rootTable.debug();
		//p1View.debug();
		//p1View.attackPanel.debug();
		//p1View.placementPanel.debug();
/*		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
        stage.draw();
		
		batch.begin();

		p1AttackingPanel.draw(batch);
		//sp.draw(batch, 1);
		batch.end();*/
		//super.render();
		stage.act();
		stage.draw();
//		rootTable.drawDebug(stage);
	}
/*	public void dispose () {
		p1AttackingPanel.dispose();
		//p2AttackingPanel.dispose();
	}*/
}
