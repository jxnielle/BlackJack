package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UI extends Application 
{
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initUI(primaryStage);
	}


	private void initUI(Stage primaryStage) {
		
		Image image = null;
		
		try 
		{
			image = new Image(new FileInputStream("src/main/resources/core/logo/logo.png"));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}				
				
		ImageView imageView = new ImageView();
		imageView.setImage(image);
	
		BorderPane canvas = new BorderPane();   
		canvas.setStyle("-fx-background-color: white;");
		
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(20, 12, 20, 12));
	    hbox.setStyle("-fx-background-color: rgb(28, 21, 133);");
	
		canvas.setTop(hbox); 
		canvas.setCenter(imageView);
		canvas.setBottom(startGameControls()); 
	      				
		Scene scene = new Scene(canvas,420, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Blackjack");
		primaryStage.show();
		
	}

	private HBox startGameControls() 
	{
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(50, 12, 50, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: rgb(28,21,133);");

		ComboBox<String> gameMode = new ComboBox<String>();
		gameMode.getItems().addAll("Console", "File Input");
		gameMode.getSelectionModel().selectFirst();
		gameMode.setStyle("-fx-font: 22 arial;");

	    Button startBtn = new Button("START");
	    startBtn.setPrefSize(200, 20);
	    startBtn.setStyle("-fx-font: 22 arial; -fx-base: rgb(247,193,15);");

	    
	    hbox.getChildren().addAll(gameMode, startBtn);
	    hbox.setAlignment(Pos.CENTER);

	    return hbox;
	}
}
