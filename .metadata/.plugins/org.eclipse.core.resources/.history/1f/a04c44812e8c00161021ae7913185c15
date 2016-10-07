package file_analyzer;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class FileAnalyzerController {

	private File _file;
	private FileAnalyzerView _view;
	private String[] _asciiArray;
	private String _outputString = "";

	public FileAnalyzerController(FileAnalyzerView view) {
		_view = view;
		_view.setController(this);
	}

	public File getFile() {
		return _file;
	}

	public void openFile(JFileChooser fileChooser) throws IOException {
		int returnVal = fileChooser.showOpenDialog(_view);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			_file = fileChooser.getSelectedFile();

			_view.getFileLabel().setText(_file.getName());

			_outputString = convertToBinary();
			_asciiArray = binaryToAscii(_outputString);
			_outputString = asciiArrayToString(_asciiArray);
			_view.getTextArea().setText(_outputString);
		}

	}

	private String convertToBinary() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(_file.getAbsolutePath()));
		String tempString = new String(encoded, Charset.defaultCharset());

		byte[] bytes = tempString.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}

	private String[] binaryToAscii(String s) {
		int arrayLength = (int) Math.ceil(((s.length() / (double)8)));
		String[] result = new String[arrayLength];

		int j = 0;
		int lastIndex = result.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			result[i] = s.substring(j, j + 8);
			j += 8;
		}
		String lastString = s.substring(j);
		StringBuilder sb = new StringBuilder();
		if (lastString.length() < 9) {
			int rest = 9 - lastString.length();
			for(int i = 1; i < rest; i++) {
				sb.append("0");
			}
			sb.append(lastString);
		}
		result[lastIndex] = sb.toString();
		StringBuilder sb2 = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			int charCode = Integer.parseInt(result[i], 2);
			sb2.append(new Character((char)charCode).toString());
		}
		String stringWithNonAscii = sb2.toString();
		String stringWithOnlyAscii = stringWithNonAscii.replaceAll("\\P{InBasic_Latin}", "");
		return stringWithOnlyAscii.split("(?!^)");
	}
	
	private String asciiArrayToString(String[] asciiArray) {
		StringBuilder sb = new StringBuilder();
		for (String e : asciiArray) {
		    sb.append(e);
		}
		return sb.toString();
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

	public void copyClipboard() {
		StringSelection strSelec = new StringSelection(_outputString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(strSelec, null);
	}
}
