package file_analyzer;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class FileAnalyzer {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileAnalyzerView _view = new FileAnalyzerView();
				FileAnalyzerController _controller = new FileAnalyzerController(_view);
				_view.setTitle("File Analyzer");
				_view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				_view.pack();
				_view.setResizable(true);
				_view.setLocationRelativeTo(null);
				_view.setVisible(true);
			}
		});
	}
}