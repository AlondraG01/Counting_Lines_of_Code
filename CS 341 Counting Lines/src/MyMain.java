import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class MyMain {

	// SPECIFY THE APPLICATION ELEMENTS: UI AND OBJECTS
	private static JButton inputBtn;
	private static JButton outputBtn;
	private static JButton computeBtn;
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static File fileToRead;
	private static File fileToSave;

	public static void main(String[] args) {
		// create GUI application window
		constructAppWindow();
		addListenerEvents();
	}

	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// construct a panel container to store buttons, etc.
		panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
		panel.setPreferredSize(new Dimension(500, 150));
		panel.setBackground(Color.DARK_GRAY);

		// build buttons, etc. and add them to the panel
		inputBtn = new JButton("Specify Input Text File");
		outputBtn = new JButton("Specify Output Text File");
		computeBtn = new JButton("Perform Work");
		panel.add(inputBtn);
		panel.add(outputBtn);
		panel.add(computeBtn);

		// add panel to the application window
		jframeWindow.add(panel);

		// TASK 5: MAKE THE APPLICATION WINDOW VISIBLE TO THE USER
		jframeWindow.pack();
		jframeWindow.setVisible(true);
	}

	private static void addListenerEvents() {
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile();
			}
		});
		outputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSaveFile();
			}
		});
		computeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					computeSomething();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	/**
	 * requestSaveFile method displays the output into the chosen text file
	 * 
	 */
	public static void requestSaveFile() {
		// parent component of the dialog
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}

	/**
	 * requestInputFile method grabs the contents inside of the chosen text file
	 * 
	 */
	public static void requestInputFile() {
		// parent component of the dialog
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// fileToRead is where the text file is stored in
			fileToRead = jfc.getSelectedFile();

			// prints out the directory of the file
			System.out.println(fileToRead.getAbsolutePath());
		}
	}

	/**
	 * computeSomething method: (1)takes the contents in the chosen text file
	 * (2)counts the lines of code (3)ignore empty lines and comments
	 * @throws FileNotFoundException, IOException 
	 * 
	 */
	public static void computeSomething() throws FileNotFoundException, IOException {
		System.out.println("now computing");
		// PrintStream allows you to grab the file you have chosen
		PrintStream out = new PrintStream(new FileOutputStream(fileToSave));
		// System.setOut will write whatever you are trying to output into the file you have chosen
		System.setOut(out);

		String path = fileToRead.getPath();

		LinesOfCode.readLines(path);
		LinesOfCode.countingLoops(path);

		System.out.println(LinesOfCode.displayOutput());
	}

}