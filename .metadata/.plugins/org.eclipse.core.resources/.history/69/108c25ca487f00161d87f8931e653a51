package file_analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import javax.swing.JFileChooser;

public class FileAnalyzerController {

	private File _file;
	private FileAnalyzerView _view;
	private String _outputString = ""; // Jervin Cruz

	public FileAnalyzerController(FileAnalyzerView view) {
		_view = view;
		_view.setController(this);
	}

	public void openFile(JFileChooser fileChooser) {
		int returnVal = fileChooser.showOpenDialog(_view);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			_file = fileChooser.getSelectedFile();
			
			_view.getFileLabel().setText(_file.getName());
						
			Path path = FileSystems.getDefault().getPath(_file.getAbsolutePath());
			try {
				BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
				_outputString = "Name: " + _file.getName() + "\n"
						+ "CreationTime: " + attr.creationTime() + "\n"
						+ "... more file metadata here";
				_view.getTextArea().setText(_outputString);
			} catch(IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public void saveFile(JFileChooser fileChooser) throws FileNotFoundException {
		int returnVal = fileChooser.showSaveDialog(_view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			_file = fileChooser.getSelectedFile();
			String filePath = _file.getAbsolutePath();
			if (!filePath.endsWith(".txt")) {
				_file = new File(filePath + ".txt");
			}
			try (PrintStream out = new PrintStream(new FileOutputStream(_file))) {
			    out.print(_outputString);
			}
		}
	}
	
	public File getFile() {
		return _file;
	}
}
