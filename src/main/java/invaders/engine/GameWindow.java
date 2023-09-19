package invaders.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;

import invaders.entities.EntityViewImpl;
import invaders.entities.SpaceBackground;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;

import invaders.entities.EntityView;
import invaders.rendering.Renderable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;

public class GameWindow {
	private final int width;
    private final int height;
	private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private Renderable background;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 280.0;

    public double count = 0;

	public GameWindow(GameEngine model, int width, int height){

		this.width = width;
        this.height = height;
        this.model = model;
        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new SpaceBackground(model, pane);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(this.model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        entityViews = new ArrayList<EntityView>();
    }

	public void run() {
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
    }

    private void draw(){
        model.update();

        List<Renderable> renderables = model.getRenderables();

        for (Renderable entity : renderables) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entity : entityViews) {
            boolean removed = true;
            for (Renderable ren : renderables) {
                if (entity.matchesEntity(ren)) {
                    removed = false;
                }
            }

            if (removed) {
                entity.markForDelete();
            }
        }

        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);

        if(!model.getPlayer().isAlive()) {
            model.getRenderables().clear();
            Label label = new Label();
            label.setText("GAME OVER");
            label.setFont(new Font("Arial", 30));
            label.setMinWidth(width);
            label.setMinHeight(height);
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Paint.valueOf("WHITE"));
            pane.getChildren().add(label);
        }
        else if(model.noMoreAliens()) {
            model.getRenderables().clear();
            Label label = new Label();
            label.setText("PLAYER WINS!");
            label.setFont(new Font("Arial", 30));
            label.setMinWidth(width);
            label.setMinHeight(height);
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Paint.valueOf("WHITE"));
            pane.getChildren().add(label);
        }
    }

	public Scene getScene() {
        return scene;
    }
}
