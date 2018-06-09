package player;



import java.io.File;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class App extends Application {

	private static File file = null;
	private static MediaPlayer mediaPlayer = null;
	private static int currentTrack = 0;
	private static List<File> files = null;
	private static String[] args = null;
	private static Scanner in = null;

	public static void main(String[] args) {
		App.args = args;
		try {
			in = new Scanner(System.in);
			System.out.println("Enter a Folder Location: ");
			String filePath = in.nextLine();
			File folder = new File(filePath);
			files = Utils.listFilesForFolder(folder);
			Utils.ListFileName(files);
			System.out.println("\nPlay Options:\n"
					+ "- 1. Track No. \n"
					+ "- 2. Random");
			String option = in.nextLine();
			switch (option) {
			case "1":
				System.out.println("Enter the track no: ");
				currentTrack = Integer.parseInt(in.nextLine());
				file = files.get(currentTrack-1);
				Thread t = new Thread(() -> launch(args));
				t.start();
				option(in);
				break;
			case "2":
				currentTrack = (int)(Math.random() * files.size() + 1);
				file = files.get(currentTrack-1);
				t = new Thread(() -> launch(args));
				t.start();
				option(in);
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			in.close();
		}
	}

	public static void option(Scanner in){
		while (true) {
			System.out.println("1- Play, 2- Pause, 3- Stop, 4- Next, 5- Previous, 6- Exit");
			String options = in.nextLine();
			switch (options) {
			case "1":
				if(!mediaPlayer.isAutoPlay())
				mediaPlayer.play();
				break;
			case "2":
				mediaPlayer.pause();
				break;
			case "3":
				mediaPlayer.stop();
				break;
			case "4":
				mediaPlayer.stop();
				file = files.get(currentTrack + 1);
				currentTrack++;
				customPlay(file);
				break;
			case "5":
				mediaPlayer.stop();
				file = files.get(currentTrack - 1);
				currentTrack--;
				customPlay(file);
				break;
			case "6":
				mediaPlayer.stop();
				System.out.println("Bye Bye...");
				System.exit(0);
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
	}

	private static void customPlay(File file) {
		Media hit = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Media hit = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
