package player;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static List<File> listFilesForFolder(final File folder) {
		List<File> files = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				files.add(fileEntry);
			}
		}
		return files;
	}

	public static void ListFileName(List<File> files) {
		for(int i=0;i<files.size();i++){
			System.out.println((i+1)+" -> "+files.get(i).getName());
		}
	}
}
