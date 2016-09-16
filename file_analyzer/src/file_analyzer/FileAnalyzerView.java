package file_analyzer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

public class FileAnalyzerView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FileAnalyzerController _controller;
	private JPanel _mainCenterScreen;
	private JPanel _bottomPanel;
	private JFileChooser _openFileChooser;
	private JFileChooser _saveFileChooser;
	private JButton _btnOpenFile;
	private JButton _btnSaveOutput;
	private JButton _btnCopyToClipboard;
	private JButton _btnExit;
	private JTextArea _textArea;

	public FileAnalyzerView() {
		getContentPane().setLayout(new BorderLayout(0, 0));

		_openFileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		_openFileChooser.setCurrentDirectory(workingDirectory);
		_openFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//_fileChooser.setFileFilter(new FileNameExtensionFilter("EXE File", "exe"));
		
		_saveFileChooser = new JFileChooser();
		_saveFileChooser.setCurrentDirectory(workingDirectory);
		_saveFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		_saveFileChooser.setFileFilter(new FileNameExtensionFilter("TXT File", "txt"));

		_mainCenterScreen = new JPanel();
		_mainCenterScreen.setLayout(new BorderLayout(0, 0));
		_mainCenterScreen.setPreferredSize(new Dimension(700, 600));

		_bottomPanel = new JPanel();
		_bottomPanel.setLayout(new FlowLayout());

		_btnOpenFile = new JButton("Open File");
		_btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_controller.openFile(_openFileChooser);
			}
		});
		
		_bottomPanel.add(_btnOpenFile);

		_btnSaveOutput = new JButton("Save Output");
		_btnSaveOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					_controller.saveFile(_saveFileChooser);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		_bottomPanel.add(_btnSaveOutput);

		_btnCopyToClipboard = new JButton("Copy to Clipboard");
		_bottomPanel.add(_btnCopyToClipboard);

		_btnExit = new JButton("Exit");
		_btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		_bottomPanel.add(_btnExit);

		Border border = BorderFactory.createLineBorder(Color.BLACK);

		_textArea = new JTextArea("Sample Text");
		_textArea.setLineWrap(true);
		_textArea.setEditable(false);

		JPopupMenu menu = new JPopupMenu();

		Action copy = new DefaultEditorKit.CopyAction();
		copy.putValue(Action.NAME, "Copy");
		copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
		menu.add(copy);

		Action selectAll = new SelectAll();
		menu.add(selectAll);

		_textArea.setComponentPopupMenu(menu);

		_textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));

		JScrollPane textAreaScrollPane = new JScrollPane(_textArea);
		textAreaScrollPane.setBorder(border);

		_mainCenterScreen.add(textAreaScrollPane, BorderLayout.CENTER);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		_mainCenterScreen.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		_mainCenterScreen.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		_mainCenterScreen.add(verticalStrut, BorderLayout.NORTH);

		//Component verticalStrut_1 = Box.createVerticalStrut(20);
		//_mainCenterScreen.add(verticalStrut_1, BorderLayout.SOUTH);

		getContentPane().add(_mainCenterScreen, BorderLayout.CENTER);
		getContentPane().add(_bottomPanel, BorderLayout.SOUTH);
	}

	public JFileChooser getFileChooser() {
		return _openFileChooser;
	}

	public JTextArea getTextArea() {
		return _textArea;
	}

	public JPanel getMainCenterScreen() {
		return _mainCenterScreen;
	}

	public JPanel getBottomPanel() {
		return _bottomPanel;
	}
	
	public void setController(FileAnalyzerController controller) {
		_controller = controller;
	}

	// Used when the user right clicks on the text and click on "Select All"
	static class SelectAll extends TextAction
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SelectAll()
		{
			super("Select All");
			putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JTextComponent component = getFocusedComponent();
			component.selectAll();
			component.requestFocusInWindow();
		}
	}

}
