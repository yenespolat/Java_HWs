/*
 * Yasin Enes Polat 150117015
 * Cenk Kaan Kanar 150117021
 * This program is simple game. You drag tiles to complete path, after completing path, the ball moves from starting tile to end tile following the solution path.
 * Our program has additional functionalities like animations, music, correct solve sound and info page.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.shape.*;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


public class Pane16 extends Application{
	
	static Media media = new Media(new File("Audio/correctsolve.wav").toURI().toString());
	static MediaPlayer correct = new MediaPlayer(media);
	
	Level level1 = new Level("level1");
	Level level2 = new Level("level2");
	Level level3 = new Level("level3");
	Level level4 = new Level("level4");
	Level level5 = new Level("level5");
	
	Level currentLevel = level1;
	
	Scene scene;
	Scene entranceScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
			Image pipesign = new Image("INFO/infopipe.png");          //In this part of the code,we have created ImageViews that we have used in the game and arranged them.
			Image infosign = new Image("INFO/infosign.png");
			
			Image logo = new Image("Proje/logo.png");
			ImageView logoView = new ImageView(logo);
			logoView.setFitHeight(150);
			logoView.setFitWidth(450);
			
			ImageView pipeshow = new ImageView(pipesign);				
			ImageView pipeshow3 = new ImageView(pipesign);				
			ImageView infoshow = new ImageView(infosign);				
			infoshow.setFitHeight(20);									
			infoshow.setFitWidth(20);										

			String themesong = "Audio/themesong.wav";                         //By using mediaplayer we added a theme song in the game.
			Media sounds = new Media(new File(themesong).toURI().toString());
			MediaPlayer theme = new MediaPlayer(sounds);
			theme.play();
			
			theme.setOnEndOfMedia(new Runnable() { //To continuously play the theme song.
		        public void run() {
		        	theme.stop();
		            theme.play();
		        }
				});
			correct.setOnEndOfMedia(new Runnable() { //To continuously play the correct sound.
			    public void run() {
			        correct.stop();
			    }
			});;
			
			Reflection ref= new Reflection();              //Reflection effect was added to the motto text.
			ref.setFraction(1f);
			ref.setBottomOpacity(0.6f);
			ref.setTopOpacity(0.4f);

			Text motto = new Text(120,120,"WELCOME TO GAME PIPER");
			motto.setFont(Font.font("Verdana",FontPosture.ITALIC,30));
			motto.setFill(Color.CRIMSON);
			motto.setEffect(ref);
			Image audioOn = new Image("Proje/audioiconOn.png");               //Audio icons were created  -turn the music off  and turn it on- .
			Image audioOff = new Image("Proje/audioiconOff.png");
			ImageView aOn =new ImageView(audioOn);
			ImageView aOff = new ImageView(audioOff);
			Button sound =new Button();
			sound.setGraphic(aOn);
			aOn.setFitHeight(35);                      //The size of sound button was adjusted.
			aOn.setFitWidth(35);
			aOff.setFitHeight(35);
			aOff.setFitWidth(35);
			
			sound.setOnMouseClicked(e->{
				if(theme.getVolume()==0) {
					theme.setVolume(50);
					sound.setGraphic(aOn);             // In this part we attached the feature that whenever user click on the sound icon
				}										//if the sound is off it turns it on and do the same for the reverse.
				else { 	
					theme.setVolume(0);
					sound.setGraphic(aOff);
				}

			});
                       

			Text playtext = new Text("PLAY");
			StackPane circlepane = new StackPane();                            //In this part,a StackPane was created for the circle which leads to
			playtext.setFont(Font.font("Verdana",FontPosture.ITALIC,30));    //gaming area.Then DropShadow effect was created.
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(5.0);
			dropShadow.setOffsetX(3.0);
			dropShadow.setOffsetY(3.0);
			dropShadow.setColor(Color.DARKMAGENTA);


			Circle circleplay = new Circle();       //A circle was created and play text inserted in it.
			circleplay.setRadius(70);				//Also dropShadow effect was exerted on the play text.
			circleplay.setStrokeWidth(10);
			circleplay.setStroke(Color.CADETBLUE);
			circleplay.setFill(Color.CADETBLUE);
			circlepane.getChildren().addAll(circleplay,playtext);

			circleplay.setOnMouseEntered(e-> {
				circleplay.setStroke(Color.CHOCOLATE); //If user put the mouse on play text the border of the circle change and effect is displayed
				playtext.setEffect(dropShadow);

			});
			circleplay.setOnMouseExited(e-> {
				circleplay.setStroke(Color.CADETBLUE);//If user put the mouse away from play text the border of the circle goes back to normal and 
				playtext.setEffect(null);             // effect is gone.
			});
			playtext.setOnMouseEntered(e->  {
				circleplay.setStroke(Color.CHOCOLATE);  
				playtext.setEffect(dropShadow);

			});	                                          //The same mouse events were done for the circle like we did on the play text.
			playtext.setOnMouseExited(e-> {
				circleplay.setStroke(Color.CADETBLUE);
				playtext.setEffect(null);
			});

			Button infoButton = new Button();     //Putting the image "i" inside info button.
			infoButton.setGraphic(infoshow);
		VBox box=new VBox(65);
		box.getChildren().addAll(logoView,circlepane,motto);//And a vertical box was created to keep cicrle and motto in it.
		box.setAlignment(Pos.CENTER); //The Box was positioned at the center.

		HBox bottomButtons = new HBox();
		bottomButtons.getChildren().addAll(sound,infoButton);//HBox was created and we added sound and info buttons in it.
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT); //This Box was positioned on the bottom right.



		BorderPane pane = new BorderPane();

		pane.setStyle("-fx-background-color: burlywood");//Color of the background.
		pane.setCenter(box);//We placed vbox which icludes playcircle and motto center of a borderpane.
		pane.setBottom(bottomButtons);//We placed hbox which icludes sound and info buttons to the bottom right.

		Text information = new Text();
		String info ="This game's goal is to create a proper path for \na ball to travel from the beginning point\nto the end point by "
				+ "dragging boxes.However\nnot all boxes are able to move.Empty and static\nboxes could not be moved."+
				"Moreover \nyou shall not transport a box diagonally,turn it \nupside down or any directions." + "\n(All Rights Reserved)" ;
		information.setText(info);
		information.setFill(Color.DARKBLUE);
		information.setFont(Font.font("Verdana", 20));//We created Information Text that inform user and give them instructions.

		String str="MainMenu";//String of mainmenu button.

		VBox bottom = new VBox(); //Three Boxes we created one for the bottom right image,another one for bottom left,
		VBox middle = new VBox(); //and middle for mainmenu button.




		Button infoback =new Button(str);//MainMenu button.
		infoback.setMaxSize(90, 45);
		infoback.setTextFill(Color.DARKGOLDENROD);
		infoback.setAlignment(Pos.CENTER); //It sets the scene as entrance scene.


		pipeshow.setFitWidth(160);
		pipeshow.setFitHeight(160);
		pipeshow3.setFitWidth(160);
		pipeshow3.setFitHeight(160);
		
		PathTransition ptPipe = new PathTransition();
		Path pathPipe = new Path(new MoveTo(240, 0),
				new LineTo(-80, 0),
				new ClosePath()
                );
		ptPipe.setDuration(Duration.millis(8000));
		ptPipe.setPath(pathPipe);
		ptPipe.setNode(pipeshow);
		ptPipe.setCycleCount(Timeline.INDEFINITE);
		ptPipe.play();

		infoshow.setFitHeight(35);
		infoshow.setFitWidth(35);

		bottom.getChildren().add(pipeshow);//Putting images inside the boxes.

		middle.getChildren().add(infoback);

		VBox informationbox= new VBox();
		informationbox.getChildren().addAll(information);//We created a vbox for information text an placed it top of the left.
		informationbox.setAlignment(Pos.TOP_LEFT);
	
		bottom.setAlignment(Pos.BOTTOM_CENTER); //Placing the other boxes which contains images.
		middle.setAlignment(Pos.CENTER);

		StackPane infopane = new StackPane();
		infopane.getChildren().addAll(bottom,informationbox,middle);//We put the pipe images,information text and menu
		entranceScene = new Scene(pane,480,560);                                    //button on the infopane.
		Scene infoScene = new Scene(infopane,480,560);
		infopane.setStyle("-fx-background-color: burlywood");
		
		
		Pane sPane = new Pane();
		Pane allNodes = new Pane();
		Circle circle = new Circle();
		circle.setRadius(15);
		
		FlowPane gameBoard = new FlowPane();
		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: burlywood");
		primaryStage.setTitle("PIPER");
				
		gameBoard.setAlignment(Pos.TOP_LEFT);
		gameBoard.setMaxWidth(480);
		gameBoard.setMinWidth(480);
		gameBoard.setMaxHeight(480);
		gameBoard.setMinHeight(480);
		
		HBox top = new HBox();
		top.setPrefHeight(40);
		
		Text text1 = new Text(20, 20, "PIPER"); //Text at the top of game screen.
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 20));
		
		top.getChildren().add(text1);
		top.setAlignment(Pos.CENTER);
		
		borderPane.setTop(top);
				
		VBox first1 = new VBox();
		VBox first2 = new VBox();
		VBox first3 = new VBox();
		VBox first4 = new VBox();
		VBox second1 = new VBox();
		VBox second2 = new VBox();
		VBox second3 = new VBox();
		VBox second4 = new VBox();
		VBox third1 = new VBox();
		VBox third2 = new VBox();
		VBox third3 = new VBox();
		VBox third4 = new VBox();
		VBox fourth1 = new VBox();
		VBox fourth2 = new VBox();
		VBox fourth3= new VBox();
		VBox fourth4 = new VBox();
		
		VBox[] paneArray = {first1,first2,first3,first4,second1,second2,second3,second4,third1,third2,third3,third4,fourth1,fourth2,fourth3,fourth4}; //This ArrayList is for game board.
		
		ArrayList<Level> levels = new ArrayList<Level>();
		levels.add(level1); levels.add(level2); levels.add(level3); levels.add(level4); levels.add(level5);
		
		ArrayList<ImageView> imgArray = new ArrayList<ImageView>();
				
		PathTransition pt1 = new PathTransition(); //These all path statements for the slide animation when level change button pressed.
		PathTransition pt2 = new PathTransition();
		PathTransition pt3 = new PathTransition();
		PathTransition pt4 = new PathTransition();
		Path path = new Path(new MoveTo(240, 238),
				new LineTo(-240,238)
                );
		Path path2 = new Path(new MoveTo(780,238),
				new LineTo(240,238)
                );
		Path reversePath = new Path(new MoveTo(240, 238),
				new LineTo(780,238)
                );
		Path reversePath2 = new Path(new MoveTo(-240,238),
				new LineTo(240,238)
                );
		pt1.setDuration(Duration.millis(500));
		pt1.setPath(path);
		pt1.setNode(sPane);
		pt2.setDuration(Duration.millis(500));
		pt2.setPath(path2);
		pt2.setNode(sPane);
		pt3.setDuration(Duration.millis(500));
		pt3.setPath(reversePath);
		pt3.setNode(sPane);
		pt4.setDuration(Duration.millis(500));
		pt4.setPath(reversePath2);
		pt4.setNode(sPane);						////////////////////////////////////////////////////////////////////////////////////////////
		
		
		Button nextLvl = new Button("Next Level"); 			//Level change buttons.
		Button prevLvl = new Button("Previous Level");
		Button toMainMenu = new Button();					//The button for returning to main menu.
		
		Image house = new Image("Proje/mainmenu.png");		//Image and ImageView for main menu button.
		ImageView houseForButton = new ImageView(house);
		
		houseForButton.setFitHeight(30);
		houseForButton.setFitWidth(30);
		
		toMainMenu.setGraphic(houseForButton);
		
		HBox buttons = new HBox();
		
		borderPane.setBottom(buttons);
		buttons.setAlignment(Pos.CENTER);
		nextLvl.setMinSize(100, 40);
		prevLvl.setMinSize(100, 40);
		toMainMenu.setMinSize(60, 40);
		buttons.getChildren().addAll(prevLvl, toMainMenu, nextLvl);
		
		nextLvl.setOnMouseClicked(e -> {	//Event handler for nextLevel button. Changes the VBoxs' ImageVýews.
				if (currentLevel.isCompleted()) {
					reListen(paneArray, imgArray);
					currentLevel = levels.get(levels.indexOf(currentLevel) < 4 ? levels.indexOf(currentLevel) + 1 : 4);
					pt1.play();
					pt1.setOnFinished(event -> {
						pt1.stop();
						pt2.play();
							pt2.setOnFinished(event2 -> {
								pt2.stop();
							});
						try {
							Level.showLevel(currentLevel, paneArray, imgArray); //This statement changes the VBoxs' ImageViews.
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						text1.setText(currentLevel.getLevel());
					});
					circle.setFill(null);
				}
				else
					text1.setText("You should complete previous level(s) first!");
			
		});
		
		prevLvl.setOnMouseClicked(e -> {	//Event handler for prevLevel button. Changes the VBoxs' ImageVýews.
				reListen(paneArray, imgArray);
				currentLevel = levels.get(levels.indexOf(currentLevel) > 0 ? levels.indexOf(currentLevel) - 1 : 0);
				pt3.play();
				pt3.setOnFinished(event -> {
					pt3.stop();
					pt4.play();
						pt4.setOnFinished(event2 -> {
							pt4.stop();
						});
					try {
						Level.showLevel(currentLevel, paneArray, imgArray); //This statement changes the VBoxs' ImageViews.
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					text1.setText(currentLevel.getLevel());
				});
				circle.setFill(null);
		});
		
		PathTransition ptReturn = new PathTransition();
		Path pathReturn = new Path(new MoveTo(-240, 262),
				new LineTo(240,262)
                );
		ptReturn.setDuration(Duration.millis(500));
		ptReturn.setPath(pathReturn);
		ptReturn.setNode(box);
		
		ptReturn.setOnFinished(e -> {
			ptReturn.stop();
		});
		
		toMainMenu.setOnMouseClicked(e -> { //This event handler changes the scene to mainMenu scene if user clicks the button with house image.
			ptReturn.play();
			primaryStage.setScene(entranceScene);
		});
		
		for (int i = 0; i < 16; i++) { //This loop assigns the drag event to all VBox's.
			int j = i;
			paneArray[i].setOnMouseDragged(e -> {
			doAction(j, e.getX(), e.getY(), paneArray, imgArray);
			});
		}
		for (int i = 0; i < 16; i++) {
			paneArray[i].setOnMouseReleased(f -> {
				if (Level.isSolved(imgArray)) { //This if statement checks if the level is completed or not.
					correct.play();
					currentLevel.setCompleted(true);
					circle.setFill(javafx.scene.paint.Color.YELLOW);
					currentLevel.solve(currentLevel, circle); //Moves the ball from start point to end point.
					text1.setText(currentLevel.getLevel() + " is completed."); //Sets the text top of the scene.
					System.out.println(currentLevel.getLevel() + " is completed.");
					for (int j = 0; j < 16; j++) { //Assigns null to all VBoxes because level is finished.
						paneArray[j].setOnMouseDragged(null);
					}
				}
			});
		}
		
		allNodes.getChildren().add(borderPane);
		
		gameBoard.getChildren().addAll(first1,first2,first3,first4,second1,second2,second3,second4,third1,third2,third3,third4,fourth1,fourth2,fourth3,fourth4);
		gameBoard.maxHeight(480);
		gameBoard.maxWidth(480);		
		borderPane.setCenter(sPane);
		circle.setFill(null);
		circle.setCenterX(57);
		circle.setCenterY(50);
		
		sPane.getChildren().addAll(gameBoard, circle);
				
		Scene scene = new Scene(allNodes, 480, 560);

		PathTransition ptMain = new PathTransition(); //Animations for main menu.
		Path pathMain = new Path(new MoveTo(240, 262),
				new LineTo(-240,262)
                );
		ptMain.setDuration(Duration.millis(500));
		ptMain.setPath(pathMain);
		ptMain.setNode(box);
		
		PathTransition ptInfo = new PathTransition();
		Path pathInfo = new Path(new MoveTo(240, 262),
				new LineTo(-240,262)
                );
		ptInfo.setDuration(Duration.millis(500));
		ptInfo.setPath(pathInfo);
		ptInfo.setNode(box);
		
		ptInfo.setOnFinished(e -> {
			ptInfo.stop();
		});
		
		infoButton.setOnAction(e-> {
				ptInfo.play();
				ptInfo.setOnFinished(b -> {
					ptInfo.stop();
					ptReturn.play();
					primaryStage.setScene(infoScene);
				});
		});//Whenever user click on "i" button we set the scene info scene.	
		
		infoback.setOnAction(e->{
			ptReturn.play();
			primaryStage.setScene(entranceScene);
		});//When you click main menu button while you on info scene.
		
		playtext.setOnMouseClicked(e -> { //Event handlers for interactive shape-like button.
			ptMain.play();
			ptMain.setOnFinished(b -> {
				ptMain.stop();
				ptReturn.play();
				primaryStage.setScene(scene);
				try {
					Level.showLevel(currentLevel, paneArray, imgArray);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});
		});


		circleplay.setOnMouseClicked(e -> { //Event handlers for interactive shape-like button.
			ptMain.play();
			ptMain.setOnFinished(b -> {
				ptMain.stop();
				ptReturn.play();
				primaryStage.setScene(scene);
				try {
					Level.showLevel(currentLevel, paneArray, imgArray);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});
		});
		
		primaryStage.setScene(entranceScene);
		primaryStage.getIcons().add(new Image("INFO/infopipe.png"));
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void reListen (VBox[] arr, ArrayList<ImageView> imgArr) { //After changing an image in doAction method, source VBox's listener assigned null. This method reassigns the doAction method if the level is not completed.
		for (int i = 0; i < 16; i++) {
			int j = i;
			arr[i].setOnMouseDragged(e -> {
			doAction(j, e.getX(), e.getY(), arr, imgArr);
			});
		}
	}
	
	public static void doAction (int i, double x, double y, VBox[] arr, ArrayList<ImageView> imgArr) { //This method simply changes the dragged VBox's image, according to event's x and y property, also changes the destination's image. 
		int x1 = i / 4 + 1; int x2 = i % 4 + 1; //These are the row and column index of dragged tile.
		if (x < 120 && x > 0 && y > 120 && !(i == 12 || i == 13 || i == 14 || i == 15) && check(imgArr, i+4, i)) {
			System.out.println("Index " + x1 + ", " + x2 + " slided down."); //This statement indicates which tile move to which direction.
			ImageView temp = imgArr.get(i+4);
			arr[i+4].getChildren().clear();
			arr[i+4].getChildren().add(imgArr.get(i));
			arr[i].getChildren().clear();
			arr[i].getChildren().add(imgArr.get(i+4));
			imgArr.set(i+4, imgArr.get(i));
			imgArr.set(i, temp);
		}
		else if (y < 120 && y > 0 && x > 120 && !(i == 3 || i == 7 || i == 11 || i == 15) && check(imgArr, i+1, i)){
			System.out.println("Index " + x1 + ", " + x2 + " slided right."); //This statement indicates which tile move to which direction.
			ImageView temp = imgArr.get(i+1);
			arr[i+1].getChildren().clear();
			arr[i+1].getChildren().add(imgArr.get(i));
			arr[i].getChildren().clear();
			arr[i].getChildren().add(imgArr.get(i+1));
			imgArr.set(i+1, imgArr.get(i));
			imgArr.set(i, temp);
		}
		else if (y < 120 && y > 0 && x < 0 && !(i == 0 || i == 4 || i == 8 || i == 12) && check(imgArr, i-1, i)) {
			System.out.println("Index " + x1 + ", " + x2 + " slided left."); //This statement indicates which tile move to which direction.
			ImageView temp = imgArr.get(i-1);
			arr[i-1].getChildren().clear();
			arr[i-1].getChildren().add(imgArr.get(i));
			arr[i].getChildren().clear();
			arr[i].getChildren().add(imgArr.get(i-1));
			imgArr.set(i-1, imgArr.get(i));
			imgArr.set(i, temp);
		}
		else if (y < 0 && x > 0 && x < 120 && !(i == 0 || i == 1 || i == 2 || i == 3) && check(imgArr, i-4, i)){
			System.out.println("Index " + x1 + ", " + x2 + " slided up."); //This statement indicates which tile move to which direction.
			ImageView temp = imgArr.get(i-4);
			arr[i-4].getChildren().clear();
			arr[i-4].getChildren().add(imgArr.get(i));
			arr[i].getChildren().clear();
			arr[i].getChildren().add(imgArr.get(i-4));
			imgArr.set(i-4, imgArr.get(i));
			imgArr.set(i, temp);
		}
	}
	
	public static boolean check (ArrayList<ImageView> imgArray, int k, int currentPlace) { //Checks if the movement can be done or not.
		
		Image freeNode = new Image("Proje/EmptyFree.png");
		Image EH = new Image("Proje/EndHorizontal.png");
		Image EV = new Image("Proje/EndVertical.png");
		Image PS01 = new Image("Proje/PipeStatic01.png");
		Image PSH = new Image("Proje/PipeStaticHorizontal.png");
		Image PSV = new Image("Proje/PipeStaticVertical.png");
		Image SH = new Image("Proje/StarterHorizontal.png");
		Image SV = new Image("Proje/StarterVertical.png");
		
		//k is the destination tile's index.		
		
		if (compare(imgArray.get(k).getImage(), freeNode) && !compare(imgArray.get(currentPlace).getImage(), freeNode)) {
			if (!compare(imgArray.get(currentPlace).getImage(), EH) && !compare(imgArray.get(currentPlace).getImage(), EV)
					&& !compare(imgArray.get(currentPlace).getImage(), PS01) && !compare(imgArray.get(currentPlace).getImage(), PSH)
					&& !compare(imgArray.get(currentPlace).getImage(), PSV) && !compare(imgArray.get(currentPlace).getImage(), SH)
					&& !compare(imgArray.get(currentPlace).getImage(), SV)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean compare (Image i1, Image i2) { //This method simply compares two images. If they are same returns true, otherwise returns false.
		for (int i = 0; i < i1.getHeight(); i++)
		{
		  for (int j = 0; j < i1.getWidth(); j++)
		  {
		    if (!i1.getPixelReader().getColor(i, j).equals(i2.getPixelReader().getColor(i, j))) 
		    	return false;
		  }
		}
		return true;
	}

	public static void main(String[] args) {
	    launch(args);
	  }
}
