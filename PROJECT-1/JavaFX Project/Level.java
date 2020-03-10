/*
 * Yasin Enes Polat 150117015
 * Cenk Kaan Kanar 150117021
 * This program is simple game. You drag tiles to complete path, after completing path, the ball moves from starting tile to end tile following the solution path.
 * Our program has additional functionalities like animations, music, correct solve sound and info page.
 */

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.shape.*;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Level {

	private String level;
	private boolean completed;
	
	Level (String s) {
	
		level = s;
		
	}
	
	public static void showLevel (Level lvl, VBox[] vb, ArrayList<ImageView> imgArr) throws FileNotFoundException { //This method changes the board VBoxs' ImageView according to given Level instance.
		File input = new File("Levels/"+ lvl.getLevel() + ".txt");
		Scanner scan = new Scanner(input);
		imgArr.clear();
		while(scan.hasNext()) { //Reads from .txt file and creates the board
			String s = scan.next();
			String[] stringArray = s.split(",");
			vb[Integer.parseInt(stringArray[0])-1].getChildren().clear();
			vb[Integer.parseInt(stringArray[0])-1].getChildren().add(selectImage(stringArray[1],stringArray[2]));
			imgArr.add(selectImage(stringArray[1],stringArray[2]));
		}
		scan.close();
	}
	
	public static ImageView selectImage(String s1, String s2) { //Selects image from directory with the given file names as String.
		String s = s1 + s2 + ".png";
		Image img = new Image("Proje/" + s);		
		ImageView image = new ImageView(img);
		image.setFitHeight(120);
		image.setFitWidth(120);
		return image;
	}
	
	public static boolean isSolved (ArrayList<ImageView> imgArr) { //Checks whether the current level is finished or not.
				
		int startIndex = 7;
		int index = 0;
		boolean isSolved = true;
				
		Image forCompare = new Image ("Proje/Emptynone.png");
		Image EH = new Image("Proje/EndHorizontal.png");
		Image EV = new Image("Proje/EndVertical.png");
		Image PS01 = new Image("Proje/PipeStatic01.png");
		Image PSH = new Image("Proje/PipeStaticHorizontal.png");
		Image PSV = new Image("Proje/PipeStaticVertical.png");
		Image SH = new Image("Proje/StarterHorizontal.png");
		Image SV = new Image("Proje/StarterVertical.png");
		Image PV = new Image("Proje/PipeVertical.png");
		Image PH = new Image("Proje/PipeHorizontal.png");
		Image P00 = new Image("Proje/Pipe00.png");
		Image P01 = new Image("Proje/Pipe01.png");
		Image P10 = new Image("Proje/Pipe10.png");
		Image P11 = new Image("Proje/Pipe11.png");

		for (int i = 0; i < imgArr.size(); i++) {
			if (Pane16.compare(imgArr.get(i).getImage(), SH) || Pane16.compare(imgArr.get(i).getImage(), SV)) { //Finds the starter tile.
				startIndex = i;
				forCompare = imgArr.get(i).getImage();
				break;
			}
		}
		
		//Next statements follows the solution path, if there is a incompatible tile, changes isSolved method to false, otherwise the method returns true.
		int to = 0;
		if (Pane16.compare(forCompare, SH)) {
			to = -1;
			index = startIndex;
			while (isSolved) {
				if (index < 0 || index > imgArr.size() - 1)
					isSolved = false;
				if (Pane16.compare(EH, imgArr.get(index).getImage()) || Pane16.compare(EV, imgArr.get(index).getImage()))
					break;
				
				switch(to) {
				case -1: 
					if (Pane16.compare(PH, imgArr.get(index + to).getImage()) || Pane16.compare(PSH, imgArr.get(index + to).getImage())) {
						index--;
					}
					else if (Pane16.compare(P01, imgArr.get(index + to).getImage()) || Pane16.compare(PS01, imgArr.get(index + to).getImage())) {
						index--;
						to = -4;
					}
					else if (Pane16.compare(P11, imgArr.get(index + to).getImage())) {
						index--;
						to = 4;
					}
					else
						isSolved = false;
					break;
				case  1:
					if (Pane16.compare(PH, imgArr.get(index + to).getImage()) || Pane16.compare(PSH, imgArr.get(index + to).getImage())) {
						index++;
					}
					else if (Pane16.compare(P00, imgArr.get(index + to).getImage())) {
						index++;
						to = -4;
					}
					else if (Pane16.compare(P10, imgArr.get(index + to).getImage())) {
						index++;
						to = 4;
					}
					else
						isSolved = false;
					break;
				case -4:
					if (Pane16.compare(PSV, imgArr.get(index + to).getImage()) || Pane16.compare(PV, imgArr.get(index + to).getImage())) {
						index -= 4;
					}
					else if (Pane16.compare(P10, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = -1;
					}
					else if (Pane16.compare(P11, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = 1;
					}
					else
						isSolved = false;
					break;
				case  4:
					if (Pane16.compare(PV, imgArr.get(index + to).getImage()) || Pane16.compare(PSV, imgArr.get(index + to).getImage())) {
						index += 4;
					}
					else if (Pane16.compare(P01, imgArr.get(index + to).getImage()) || Pane16.compare(PS01, imgArr.get(index + to).getImage())) {
						index += 4;
						to = 1;
					}
					
					else if (Pane16.compare(P00, imgArr.get(index + to).getImage())) {
						index += 4;
						to = -1;
					}
					else
						isSolved = false;
					break;
				}
			}
		}
		
		else {
			to = 4;
			index = startIndex;
			while (isSolved) {
				if (index < 0 || index > imgArr.size() - 1)
					isSolved = false;
				
				switch(to) {
				case -1: 
					if (Pane16.compare(PH, imgArr.get(index + to).getImage()) || Pane16.compare(PSH, imgArr.get(index + to).getImage())) {
						index--;
					}
					else if (Pane16.compare(P01, imgArr.get(index + to).getImage()) || Pane16.compare(PS01, imgArr.get(index + to).getImage())) {
						index--;
						to = -4;
					}
					else if (Pane16.compare(P11, imgArr.get(index + to).getImage())) {
						index--;
						to = 4;
					}
					else {
						isSolved = false;
					}
					break;
				case  1:
					if (Pane16.compare(PH, imgArr.get(index + to).getImage()) || Pane16.compare(PSH, imgArr.get(index + to).getImage())) {
						index++;
					}
					else if (Pane16.compare(P00, imgArr.get(index + to).getImage())) {
						index++;
						to = -4;
					}
					else if (Pane16.compare(P10, imgArr.get(index + to).getImage())) {
						index++;
						to = 4;
					}
					else {
						isSolved = false;
					}
					break;
				case -4:
					if (Pane16.compare(PSV, imgArr.get(index + to).getImage()) || Pane16.compare(PV, imgArr.get(index + to).getImage())) {
						index -= 4;
					}
					else if (Pane16.compare(P10, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = -1;
					}
					else if (Pane16.compare(P11, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = 1;
					}
					else {
						isSolved = false;
					}
					break;
				case  4:
					if (Pane16.compare(PV, imgArr.get(index + to).getImage()) || Pane16.compare(PSV, imgArr.get(index + to).getImage())) {
						index += 4;
					}
					else if (Pane16.compare(P01, imgArr.get(index + to).getImage()) || Pane16.compare(PS01, imgArr.get(index + to).getImage())) {
						index += 4;
						to = 1;
					}
					else if (Pane16.compare(P00, imgArr.get(index + to).getImage())) {
						index += 4;
						to = -1;
					}
					else {
						isSolved = false;
					}
					break;
				}
				
			}
			
			index += to;
			if (Pane16.compare(EH, imgArr.get(index).getImage()) || Pane16.compare(EV, imgArr.get(index).getImage())) {
				return true;
			}
				
		}
		
		return isSolved;
	}
	
	public void solve (Level currentLevel, Circle circle) { //Shows the animation when user finish currentLevel, works statically.
		PathTransition pt1 = new PathTransition();
		if (currentLevel.getLevel().equals("level1") || currentLevel.getLevel().equals("level2") || currentLevel.getLevel().equals("level3")) {
			Path path = new Path(new MoveTo(57, 50),
	                new LineTo(57, 360),
	                new LineTo(77, 410),
	                new LineTo(120,423),
	                new LineTo(422,423));
			pt1.setDuration(Duration.millis(4000));
			pt1.setPath(path);
			pt1.setNode(circle);
			pt1.play();
		}
		else {
			Path path = new Path(new MoveTo(57, 50),
	                new LineTo(57, 240),
	                new LineTo(77, 290),
	                new LineTo(120, 303),
	                new LineTo(360, 303),
					new LineTo(403, 290),
					new LineTo(422, 240),
					new LineTo(422, 180));
			pt1.setDuration(Duration.millis(3500));
			pt1.setPath(path);
			pt1.setNode(circle);
			pt1.play();
		}
		pt1.setOnFinished(e -> {
			pt1.stop();
		});
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
