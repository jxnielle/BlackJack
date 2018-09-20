package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI extends Application 
{
	Scene fileScene, consoleScene, homeScene;
	HBox sceneHeader, sceneFooter, fileNameArea, scoreRow, playerMoveArea;
    VBox scoreSection;
	Label fileNameLabel, winnerLabel, scoreLabel, headerLabel, playerSectionLabel, dealerSectionLabel, playerMoveLabel;
	TextField fileName;
	ComboBox<String> gameInput, playerMove;
    Button startBtn, goBtn;
    FlowPane playerSection, dealerSection;
	
	public static void main(String[] args) 
	{
		launch(args);
	}	

	@Override
	public void start(Stage primaryStage) throws Exception 
	{		
		initUI(primaryStage);
	}

	private void initUI(Stage primaryStage) 
	{			 	      				
		setStyling();
		homeScene = new Scene(initHomeScene(primaryStage),420, 400);
		primaryStage.setScene(homeScene);
		primaryStage.setMaxWidth(1280);
		primaryStage.setTitle("Blackjack");
		primaryStage.show();	
	}
		
	private BorderPane initHomeScene(Stage primaryStage) 
	{	
		homeSceneControls(primaryStage);
		
		BorderPane canvas = new BorderPane();   
		canvas.setStyle("-fx-background-color: white;");
		
		Image logo = null;
		
		try 
		{
			logo = new Image(new FileInputStream("src/main/resources/core/logo/logo.png"));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}		
		
		ImageView logoImageView = new ImageView();
		logoImageView.setImage(logo);
				
		canvas.setTop(sceneHeader); 
		canvas.setCenter(logoImageView);
		canvas.setBottom(sceneFooter); 
		
		return canvas;
	}
		
	private void homeSceneControls(Stage primaryStage) 
	{   
		gameInput.getItems().addAll("Console", "File Input");
		gameInput.getSelectionModel().selectFirst();
		gameInput.setOnAction(new EventHandler<ActionEvent>() 
	    {
		    @Override public void handle(ActionEvent e) 
		    {
			    if(gameInput.getValue().equalsIgnoreCase("console")) 
			    {
			    	fileNameArea.setVisible(false);
			    }
			    else
			    {
			    	fileNameArea.setVisible(true);
			    }
		    }
		});

	    startBtn.setOnAction(new EventHandler<ActionEvent>() 
	    {
		    @Override public void handle(ActionEvent e) 
		    {
			    if(gameInput.getValue().equalsIgnoreCase("console")) 
			    {
			    	ConsoleMode(primaryStage);
			    }
			    else
			    {
			    	String path = "src/main/resources/core/moves/";
			    	String nameOfFile = path + fileName.getText();
			    	File f = new File(nameOfFile);
			    	if(f.exists() && !f.isDirectory()) { 
				    	FileInput(primaryStage, nameOfFile);
			    	}
			    }
		    }
		});
	    	    
	    sceneFooter.getChildren().addAll(gameInput, fileNameArea ,startBtn);
	}
	
	private void FileInput(Stage primaryStage, String fileName) 
	{
		playerSectionLabel.setText("Player's Hand: ");
	    playerSection.getChildren().addAll(playerSectionLabel);
		
		dealerSectionLabel.setText("Dealer's Hand: ");
	    dealerSection.getChildren().addAll(dealerSectionLabel);

	    sceneHeader.getChildren().clear();
	    headerLabel.setText("File Input");
	    sceneHeader.getChildren().addAll(headerLabel);
	    
	    scoreSection.getChildren().clear();

		Blackjack blackjack = new Blackjack();
		
		try 
		{
			blackjack.setHandsFromFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (blackjack.getWinner().equals(blackjack.getDealerHand())) 
		{
			displayDealerWin(blackjack);
		}
		else 
		{
			displayPlayerWin(blackjack);
		}	
		
		scoreRow.getChildren().add(scoreSection);
    
	    HBox centerPane = new HBox();
	    centerPane.getChildren().addAll(playerSection, dealerSection);
	    
		BorderPane canvas = new BorderPane();  
		canvas.setTop(sceneHeader);  
		canvas.setCenter(centerPane);	
		canvas.setBottom(scoreRow);
		
		fileScene = new Scene(canvas, homeScene.getWidth(), homeScene.getHeight());
		primaryStage.setScene(fileScene);
	}
	
	private void displayDealerWin(Blackjack blackjack) 
	{
		winnerLabel.setText("Dealer Won");
		scoreLabel.setText("Dealer's Hand Value: " + blackjack.getDealerHand().getHandValue() + "  ,  Player's Hand Value: " + blackjack.getPlayerHand().getHandValue());
		scoreSection.getChildren().addAll(winnerLabel, scoreLabel);
		try 
		{
			dealerSection.getChildren().addAll(displayCardsInFile(blackjack.getDealerHand()));
			playerSection.getChildren().addAll(displayCardsInFile(blackjack.getPlayerHand()));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void displayPlayerWin(Blackjack blackjack) {
		winnerLabel.setText("Player Won");
		scoreLabel.setText("Player's Hand Value: " + blackjack.getPlayerHand().getHandValue() + "  ,  Dealer's Hand Value: " + blackjack.getDealerHand().getHandValue());
		scoreSection.getChildren().addAll(winnerLabel, scoreLabel);
		try 
		{
			dealerSection.getChildren().addAll(displayCardsInFile(blackjack.getDealerHand()));
			playerSection.getChildren().addAll(displayCardsInFile(blackjack.getPlayerHand()));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}
	
	private boolean displayInitialBlackjack(Blackjack blackjack) 
	{
		Hand playerHand = blackjack.getPlayerHand();
		Hand dealerHand = blackjack.getDealerHand();
		
		if (playerHand.hasBlackjack()) 
		{
			for (Card card: dealerHand.getCards()) 
			{
				card.setVisibility(true);
			}
			
			displayPlayerWin(blackjack);
			return true;
		}
		else if (dealerHand.hasBlackjack()) 
		{
			for (Card card: dealerHand.getCards()) 
			{
				card.setVisibility(true);
			}
			
			displayDealerWin(blackjack);
			return true;
		}
		
		return false;
	}
	
	private ArrayList<ImageView> displayCardsInFile(Hand aHand) throws FileNotFoundException {
		ArrayList<ImageView> imageView = new ArrayList<ImageView>();
		ArrayList<Image> cardImages = new ArrayList<Image>();	

		String cardsDir = "src/main/resources/core/cards/";
		String imgFile = "";
		int i = 0;
		for (Card card : aHand.getCards()) {
			if (card.isVisible()) {			
				imgFile = cardsDir+card.toString().toLowerCase() + ".png";
			}
			else {
				imgFile = cardsDir + "back.png";
			}
			
			cardImages.add(new Image(new FileInputStream(imgFile)));
			imageView.add(new ImageView(cardImages.get(i)));
			imageView.get(i).setFitHeight(217);
			imageView.get(i).setFitWidth(150);
			i++;		
		}
				
		return imageView;
	}

	private void goBtnAction(Blackjack blackjack) 
	{
    	try 
    	{
    		Hand playerHand = blackjack.getPlayerHand();
    		Hand dealerHand = blackjack.getDealerHand();
    		
		    String move = playerMove.getValue();
		    if(move != null && move.equalsIgnoreCase("stand")) 
		    {
		    	scoreSection.getChildren().remove(playerMoveArea);
		    	blackjack.hitUntilStand();
		    	
				for (Card card: dealerHand.getCards()) {
					card.setVisibility(true);
				}
				
		    	dealerSection.getChildren().clear();
		    	dealerSection.getChildren().addAll(dealerSectionLabel);
		    	playerSection.getChildren().clear();
		    	playerSection.getChildren().addAll(playerSectionLabel);

				if(blackjack.getWinner().equals(blackjack.getDealerHand())) 
				{
					displayDealerWin(blackjack);
				}
				else 
				{
					displayPlayerWin(blackjack);
				}								
		    }
		    else if (move != null && move.equalsIgnoreCase("hit")) 
		    {
					playerSection.getChildren().clear();
			    	playerHand.add(blackjack.getDeck().drawCard());
			    	playerSection.getChildren().addAll(playerSectionLabel);
					playerSection.getChildren().addAll(displayCardsInFile(playerHand));
					
					if (playerHand.isBust()) {
						for (Card card: dealerHand.getCards()) {
							card.setVisibility(true);
						}
						
				    	scoreSection.getChildren().remove(playerMoveArea);
				    	dealerSection.getChildren().clear();
				    	dealerSection.getChildren().addAll(dealerSectionLabel);
				    	playerSection.getChildren().clear();
				    	playerSection.getChildren().addAll(playerSectionLabel);
				    	
				    	displayDealerWin(blackjack);							    	
					}
					
					if (playerHand.hasBlackjack()) {
						//player wins
						for (Card card: dealerHand.getCards()) {
							card.setVisibility(true);
						}
						
				    	scoreSection.getChildren().remove(playerMoveArea);
				    	dealerSection.getChildren().clear();
				    	dealerSection.getChildren().addAll(dealerSectionLabel);
				    	playerSection.getChildren().clear();
				    	playerSection.getChildren().addAll(playerSectionLabel);
				    	
				    	displayPlayerWin(blackjack);
					}							
		    }
		    else {    	
		    }
		} 
    	catch (Exception e2) 
    	{
		}
	}
	
	private void ConsoleMode(Stage primaryStage) 
	{	    
		playerSectionLabel.setText("Player's Hand: ");
	    playerSection.getChildren().addAll(playerSectionLabel);
	    
		dealerSectionLabel.setText("Dealer's Hand: ");
	    dealerSection.getChildren().addAll(dealerSectionLabel);
	    
	    sceneHeader.getChildren().clear();
		headerLabel.setText("BLACKJACK");
	    sceneHeader.getChildren().addAll(headerLabel);
	  
	    Blackjack blackjack = new Blackjack();
	    blackjack.addInitialCards();
	    
	    Hand playerHand = blackjack.getPlayerHand();
	    Hand dealerHand = blackjack.getDealerHand();
	       
		try 
		{
			if(!displayInitialBlackjack(blackjack)) {
				
				playerMoveArea.getChildren().addAll(playerMoveLabel, playerMove, goBtn);
				scoreSection.getChildren().addAll(playerMoveArea);
				playerSection.getChildren().addAll(displayCardsInFile(playerHand));
				dealerSection.getChildren().addAll(displayCardsInFile(dealerHand));
								
			    goBtn.setOnAction(new EventHandler<ActionEvent>() 
			    {
				    @Override public void handle(ActionEvent e) 
				    {
				    	goBtnAction(blackjack);
				    }
				}); 
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	    
		BorderPane canvas = new BorderPane();  
	    scoreRow.getChildren().add(scoreSection);

	    HBox centerPane = new HBox();
	    centerPane.getChildren().addAll(playerSection, dealerSection);
	    	    
		canvas.setTop(sceneHeader);  
		canvas.setCenter(centerPane);	
		canvas.setBottom(scoreRow);
		fileScene = new Scene(canvas, homeScene.getWidth(), homeScene.getHeight());
		primaryStage.setScene(fileScene);	
	}

	private void setStyling() {
		sceneHeader = new HBox();
	    sceneHeader.setPadding(new Insets(20, 12, 20, 12));
	    sceneHeader.setStyle("-fx-background-color: rgb(28, 21, 133);");
	    
	    sceneFooter = new HBox();
	    sceneFooter.setPadding(new Insets(50, 12, 50, 12));
	    sceneFooter.setSpacing(10);
	    sceneFooter.setStyle("-fx-background-color: rgb(28,21,133);");
	    sceneFooter.setAlignment(Pos.CENTER);
	    
		fileNameArea = new HBox();
		fileNameLabel = new Label("File Name : ");
		fileNameLabel.setStyle("-fx-font: 22 arial;");
		fileNameLabel.setTextFill(Color.web("white"));

    	fileName = new TextField();
    	fileName.setStyle("-fx-font: 22 arial;");
    	fileNameArea.getChildren().addAll(fileNameLabel, fileName);
    	fileNameArea.setVisible(false);  
    	
    	playerMoveArea = new HBox();
    	
		gameInput = new ComboBox<String>();
		gameInput.setStyle("-fx-font: 22 arial;");
		
		playerMove = new ComboBox<String>();
		playerMove.getItems().addAll("Hit", "Stand");
		playerMove.setStyle("-fx-font: 22 arial;");
		
	    startBtn = new Button("START");
	    startBtn.setPrefSize(200, 20);
	    startBtn.setStyle("-fx-font: 22 arial; -fx-base: rgb(247,193,15);");
	    
	    goBtn = new Button("GO");
	    goBtn.setPrefSize(100, 20);
	    goBtn.setStyle("-fx-font: 22 arial; -fx-base: rgb(247,193,15);");
	        
		winnerLabel = new Label();
		winnerLabel.setTextFill(Color.web("white"));
		
		scoreLabel = new Label();
		scoreLabel.setTextFill(Color.web("white"));
		
		playerSectionLabel = new Label();
		playerSectionLabel.setStyle("-fx-font: 22 arial;");
		playerSectionLabel.setTextFill(Color.web("white"));
	    playerSectionLabel.setPrefWidth(640);;
		
		dealerSectionLabel = new Label();
		dealerSectionLabel.setStyle("-fx-font: 22 arial;");
	    dealerSectionLabel.setPrefWidth(640);
		
		headerLabel = new Label();
		headerLabel.setStyle("-fx-font: 22 arial;");
		headerLabel.setTextFill(Color.web("white"));
		
		playerMoveLabel = new Label("Choose a move: ");
		playerMoveLabel.setTextFill(Color.web("white"));
		
	    scoreSection = new VBox();
	    scoreSection.setStyle("-fx-font: 22 arial;");
	    
	    dealerSection = new FlowPane();
	    dealerSection.setPadding(new Insets(5, 5, 5, 5));
	    dealerSection.setVgap(4);
	    dealerSection.setHgap(4);
	    dealerSection.setPrefWrapLength(170);
	    dealerSection.setPrefWidth(640);
	    dealerSection.setStyle("-fx-background-color: yellow;");
	    
	    playerSection = new FlowPane();
	    playerSection.setPadding(new Insets(5, 5, 5, 5));
	    playerSection.setVgap(4);
	    playerSection.setHgap(4);
	    playerSection.setPrefWrapLength(170);
	    playerSection.setPrefWidth(640);
	    playerSection.setStyle("-fx-background-color: red;");
	    
		scoreRow = new HBox();
		scoreRow.setStyle("-fx-background-color: rgb(28, 21, 133);");
		scoreRow.setPadding(new Insets(20, 12, 20, 12));
		scoreRow.setAlignment(Pos.CENTER);
	}
}