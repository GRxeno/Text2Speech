package view;

import java.awt.*;
import javax.swing.*;

import commands.*;
import model.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/*
 * US1 Status: DONE Description: The application should  allow me to open different kinds of files. (.docx, .xlsx)(Encrypted and not)
 * US2 Status: DONE Description: To be able to edit the contents of the file.
 * US3 Status: DONE Description: To save the contents of the file that I opened on disk. (.docx, .xlsx)(Encrypted and not)
 * US4 Status: DONE Description: Transform the contents of the file that I opened to audio.
 * US5 Status: DONE Description: Select a part of the contents of the file that I opened and transform them to audio.
 * US6 Status: DONE Description: To tune the audio parameters, i.e., the volume, the speech rate and the pitch. 
 * US7 Status: DONE Description: To activate a recording operation that keeps track of a sequence of text to audio transformation actions/commands. 
 * US8 Status: DONE Description: To replay the recorded sequence of actions.
 * US9 Status: DONE Description: To deactivate the recording operation.
 */

public class TextToSpeechEditorView {
	
	private CommandsFactory factory;
	private Document doc;
	private ReplayManager replayManager;
	
	private JFrame frmTextToSpeech;
	private JTextField textField;
	private JTextArea textArea;
	private JButton button_editFile;
	private JSpinner lineSpinner;
	private JButton button_record = new JButton("Record");
	private JButton button_stop = new JButton("Stop");
	private JButton button_replay = new JButton("Replay");

	//Show the application.
	public static void main(String args[]) {
		  try {
			    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			      if ("Nimbus".equals(info.getName())) {
		          javax.swing.UIManager.setLookAndFeel(info.getClassName());
		          break;
		      }
		    }
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (InstantiationException e) {
		    e.printStackTrace();
		  } catch (IllegalAccessException e) {
		    e.printStackTrace();
		  } catch (javax.swing.UnsupportedLookAndFeelException e) {
		    e.printStackTrace();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextToSpeechEditorView window = new TextToSpeechEditorView();
					 window.frmTextToSpeech.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//Create the application.
	public TextToSpeechEditorView() {
		doc = new Document();
		replayManager = new ReplayManager();
		doc.setEncryption("none");
		textArea = new JTextArea();
		textField = new JTextField();
		lineSpinner = new JSpinner();
		button_editFile = new JButton("Edit");
		button_record = new JButton("Record");
		button_stop = new JButton("Stop");
		button_replay = new JButton("Replay");
		factory = new CommandsFactory();
		factory.setParameters(doc, replayManager, textArea, textField, lineSpinner);
		factory.setButtons(button_editFile, button_record, button_stop, button_replay);
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frmTextToSpeech = new JFrame();
		frmTextToSpeech.setTitle("Text To Speech");
		frmTextToSpeech.setBounds(200, 200, 620, 620);
		frmTextToSpeech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToSpeech.getContentPane().setLayout(null);
		changeToLightTheme();
		
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(10, 11, 365, 25);
		frmTextToSpeech.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 47, 473, 473);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		frmTextToSpeech.getContentPane().add(scrollPane);
		
		// ------------------ LineNo Code ------------------
		
		//TextLineNumber tln = new TextLineNumber(textArea);
		//scrollPane.setRowHeaderView(tln);
		
//		LineNumberTextArea lineNumberingTextArea = new LineNumberTextArea(textArea);
//		scrollPane.setRowHeaderView(lineNumberingTextArea);
//		
//		textArea.getDocument().addDocumentListener(new DocumentListener()
//		{
//		    @Override
//		    public void insertUpdate(DocumentEvent documentEvent)
//		    {
//		        lineNumberingTextArea.updateLineNumbers();
//		    }
//
//		    @Override
//		    public void removeUpdate(DocumentEvent documentEvent)
//		    {
//		        lineNumberingTextArea.updateLineNumbers();
//		    }
//
//		    @Override
//		    public void changedUpdate(DocumentEvent documentEvent)
//		    {
//		        lineNumberingTextArea.updateLineNumbers();
//		    }
//		});
		
//		textArea.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//			    if (textArea.getSelectedText() != null) { // See if they selected something 
//			        String s = textArea.getSelectedText();
//			        System.out.println(s);
//			    }
//			}
//		});
		
		// ------------------ /LineNo Code ------------------
		

		
		// ------------------ Document Buttons ------------------
		
		JButton button_openFile = new JButton("Open File");
		button_openFile.setToolTipText("Open a file");
		button_openFile.setBounds(385, 11, 100, 25);
		button_openFile.addActionListener(factory.createCommand("openFile"));
		frmTextToSpeech.getContentPane().add(button_openFile);
		
		button_editFile.addActionListener(factory.createCommand("editFile")); 
		button_editFile.setToolTipText("Edit / Save the text area");
		button_editFile.setBounds(500, 49, 89, 23);
		frmTextToSpeech.getContentPane().add(button_editFile);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.setToolTipText("Save the contents to a file");
		btnSaveFile.addActionListener(factory.createCommand("saveFile"));
		btnSaveFile.setBounds(500, 12, 89, 23);
		frmTextToSpeech.getContentPane().add(btnSaveFile);
		
		// ------------------ /Document Buttons ------------------
		
		// ------------------ Play Buttons ------------------
		
		JLabel label_play = new JLabel("Play Text");
		label_play.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_play.setHorizontalAlignment(SwingConstants.CENTER);
		label_play.setBounds(500, 81, 89, 14);
		frmTextToSpeech.getContentPane().add(label_play);
		
		JButton button_playAll = new JButton("Play All");
		button_playAll.setToolTipText("Play all content in text area");
		button_playAll.addActionListener(factory.createCommand("txtToSpeech"));
		button_playAll.setBounds(500, 106, 89, 23);
		frmTextToSpeech.getContentPane().add(button_playAll);
		
		JButton button_playSelected = new JButton("Play Selected");
		button_playSelected.setToolTipText("Play selected text in text area");
		button_playSelected.addActionListener(factory.createCommand("txtToSpeech"));
		button_playSelected.setBounds(500, 140, 89, 23);
		frmTextToSpeech.getContentPane().add(button_playSelected);
		
		JLabel lblNewLabel = new JLabel("Line:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(500, 174, 43, 20);
		frmTextToSpeech.getContentPane().add(lblNewLabel);
		
	    // Disable keyboard edits in the spinner
	    JFormattedTextField tf = ((JSpinner.DefaultEditor) lineSpinner.getEditor()).getTextField();
	    tf.setEditable(false);
		lineSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)lineSpinner.getValue() < 0) {
					lineSpinner.setValue(0);
				}
				if((int)lineSpinner.getValue() > doc.getLines()) {
					lineSpinner.setValue(doc.getLines());
				}
			}
		});
		lineSpinner.setBounds(539, 172, 50, 25);
		frmTextToSpeech.getContentPane().add(lineSpinner);
		
		JButton buttonPlayLine = new JButton("Play Line");
		buttonPlayLine.setToolTipText("Play text in selected line");
		buttonPlayLine.addActionListener(factory.createCommand("txtToSpeech"));
		buttonPlayLine.setBounds(500, 200, 89, 23);
		frmTextToSpeech.getContentPane().add(buttonPlayLine);
		
		// ------------------ / Play Buttons ------------------
		
		// ------------------ Record Buttons ------------------
		
		JLabel label_record = new JLabel("Record Text");
		label_record.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_record.setHorizontalAlignment(SwingConstants.CENTER);
		label_record.setBounds(500, 234, 89, 14);
		frmTextToSpeech.getContentPane().add(label_record);
		
		button_stop.setEnabled(false);

		button_record.addActionListener(factory.createCommand("startRecord"));
		button_record.setToolTipText("Start recording a set of actions");
		button_record.setBounds(500, 259, 89, 23);
		frmTextToSpeech.getContentPane().add(button_record);
		
		button_stop.addActionListener(factory.createCommand("startRecord"));
		button_stop.setToolTipText("Stop recording the set of actions");
		button_stop.setBounds(500, 293, 89, 23);
		frmTextToSpeech.getContentPane().add(button_stop);
		
		button_replay.addActionListener(factory.createCommand("startRecord"));
		button_replay.setToolTipText("Replay the last recorded set of actions");
		button_replay.setBounds(500, 327, 89, 23);
		frmTextToSpeech.getContentPane().add(button_replay);
		
		
		// ------------------ /Record Buttons ------------------
		
		// ------------------ SpeakSettings ------------------
		
		JLabel label_settings = new JLabel("Voice Settings");
		label_settings.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_settings.setHorizontalAlignment(SwingConstants.CENTER);
		label_settings.setBounds(493, 361, 96, 23);
		frmTextToSpeech.getContentPane().add(label_settings);
		
		// Slider Volume
		JSlider sliderVolume = new JSlider(1, 100, 100);
		JLabel label_volume = new JLabel("Volume: " + sliderVolume.getValue());
		label_volume.setHorizontalAlignment(SwingConstants.CENTER);
		label_volume.setBounds(500, 391, 89, 14);
		
		doc.getAudioManager().setVolume(sliderVolume.getValue() / (float)100);
		sliderVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_volume.setText("Volume: " + sliderVolume.getValue());
				doc.getAudioManager().setVolume(sliderVolume.getValue() / (float)100);
			}
		});
		sliderVolume.setBounds(500, 405, 89, 26);
		frmTextToSpeech.getContentPane().add(label_volume);
		frmTextToSpeech.getContentPane().add(sliderVolume);
		
		// Slider Rate
		JSlider sliderRate = new JSlider(100, 200, 150);
		JLabel label_rate = new JLabel("Rate: " + sliderRate.getValue());
		label_rate.setHorizontalAlignment(SwingConstants.CENTER);
		label_rate.setBounds(500, 436, 89, 14);
		
		doc.getAudioManager().setRate(sliderRate.getValue());
		sliderRate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_rate.setText("Rate: " + sliderRate.getValue());
				doc.getAudioManager().setRate(sliderRate.getValue());
			}
		});
		sliderRate.setBounds(500, 450, 89, 26);
		frmTextToSpeech.getContentPane().add(label_rate);
		frmTextToSpeech.getContentPane().add(sliderRate);
		
		// Slider Pitch
		JSlider sliderPitch = new JSlider(50, 150, 100);
		JLabel label_pitch = new JLabel("Pitch: " + sliderPitch.getValue());
		label_pitch.setHorizontalAlignment(SwingConstants.CENTER);
		label_pitch.setBounds(500, 480, 89, 14);
		
		doc.getAudioManager().setPitch(sliderPitch.getValue());
		sliderPitch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_pitch.setText("Pitch: " + sliderPitch.getValue());
				doc.getAudioManager().setPitch(sliderPitch.getValue());
			}
		});
		sliderPitch.setBounds(500, 494, 89, 26);
		frmTextToSpeech.getContentPane().add(label_pitch);
		frmTextToSpeech.getContentPane().add(sliderPitch);
		
		// ------------------ /SpeakSettings ------------------
		
		
		// ------------------ MenuBar ------------------
		
		JMenuBar menuBar = new JMenuBar();
		frmTextToSpeech.setJMenuBar(menuBar);
		
		JMenu Menu_File = new JMenu("  File  ");
		Menu_File.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Menu_File.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Menu_File);
		
		JMenuItem MenuItem_openFile = new JMenuItem("Open File...     ");
		MenuItem_openFile.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		MenuItem_openFile.addActionListener(factory.createCommand("openFile"));
		Menu_File.add(MenuItem_openFile);
		
		JMenuItem MenuItem_saveFile = new JMenuItem("Save File     ");
		MenuItem_saveFile.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		MenuItem_saveFile.addActionListener(factory.createCommand("saveFile"));
		Menu_File.add(MenuItem_saveFile);
		
		JMenu Menu_encryption = new JMenu(" Encryption ");
		Menu_encryption.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(Menu_encryption);
		
		JRadioButtonMenuItem encryptionRadioItem_None = new JRadioButtonMenuItem(" None ");
		encryptionRadioItem_None.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		encryptionRadioItem_None.setSelected(true);
		Menu_encryption.add(encryptionRadioItem_None);
		
		JRadioButtonMenuItem encryptionRadioItem_rot13 = new JRadioButtonMenuItem(" Rot13 ");
		encryptionRadioItem_rot13.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Menu_encryption.add(encryptionRadioItem_rot13);
		
		JRadioButtonMenuItem encryptionRadioItem_atBash = new JRadioButtonMenuItem(" AtBash ");
		encryptionRadioItem_atBash.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Menu_encryption.add(encryptionRadioItem_atBash);
		
		encryptionRadioItem_None.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encryptionRadioItem_None.setSelected(true);
				encryptionRadioItem_rot13.setSelected(false);
				encryptionRadioItem_atBash.setSelected(false);
				doc.setEncryption("none");
			}
		});
		
		encryptionRadioItem_rot13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encryptionRadioItem_None.setSelected(false);
				encryptionRadioItem_rot13.setSelected(true);
				encryptionRadioItem_atBash.setSelected(false);
				doc.setEncryption("rot13");
			}
		});
		
		encryptionRadioItem_atBash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encryptionRadioItem_None.setSelected(false);
				encryptionRadioItem_rot13.setSelected(false);
				encryptionRadioItem_atBash.setSelected(true);
				doc.setEncryption("atBash");
			}
		});

		JMenu Menu_theme = new JMenu(" Theme ");
		Menu_theme.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(Menu_theme);
		
		JRadioButtonMenuItem themeRadioItem_Light = new JRadioButtonMenuItem(" Light ");
		themeRadioItem_Light.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		themeRadioItem_Light.setSelected(true);
		Menu_theme.add(themeRadioItem_Light);
		
		JRadioButtonMenuItem themeRadioItem_Dark = new JRadioButtonMenuItem(" Dark ");
		themeRadioItem_Dark.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Menu_theme.add(themeRadioItem_Dark);
		
		themeRadioItem_Light.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themeRadioItem_Dark.setSelected(false);
				themeRadioItem_Light.setSelected(true);
				changeToLightTheme();
			}
		});
		
		themeRadioItem_Dark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themeRadioItem_Dark.setSelected(true);
				themeRadioItem_Light.setSelected(false);
				changeToDarkTheme();
			}
		});
		
		JMenu Menu_help = new JMenu(" Help ");
		Menu_help.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(Menu_help);
		
		JMenuItem MenuItem_onlineHelp = new JMenuItem("Online Help");
		MenuItem_onlineHelp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		MenuItem_onlineHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/GRxeno/Text2Speech"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}         
			}
		});
		Menu_help.add(MenuItem_onlineHelp);
		
		JMenuItem MenuItem_about = new JMenuItem("About");
		MenuItem_about.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		MenuItem_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmTextToSpeech, "Text2speech is an advanced text to speech application that converts text from a file\n"
															 + " or the build-in editor to speech. The user can open a document, edit and save the\n"
															 + " contents of it with no, rot13 or atbash encryption and convert it to speech.\n"
															 + "The voice is fully customizable, the volume, pitch and rate can be adjusted with sliders.");     
			}
		});
		Menu_help.add(MenuItem_about);
		
		// ------------------ /MenuBar ------------------	
	}
	
	public void changeToLightTheme() {
		  UIManager.put( "control", new Color( 214, 217, 223) );
		  UIManager.put( "info", new Color( 242, 242, 189) );
		  UIManager.put( "nimbusBase", new Color( 51, 98, 140) );
		  UIManager.put( "nimbusAlertYellow", new Color( 255, 220, 35) );
		  UIManager.put( "nimbusDisabledText", new Color( 142, 143, 145) );
		  UIManager.put( "nimbusFocus", new Color( 115, 164, 209) );
		  UIManager.put( "nimbusGreen", new Color( 176, 179, 50) );
		  UIManager.put( "nimbusInfoBlue", new Color( 47, 92, 180) );
		  UIManager.put( "nimbusLightBackground", new Color( 255, 255, 255) );
		  UIManager.put( "nimbusOrange", new Color( 191, 98, 4) );
		  UIManager.put( "nimbusRed", new Color( 169, 46, 34) );
		  UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
		  UIManager.put( "nimbusSelectionBackground", new Color( 57, 105, 138) );
		  UIManager.put( "text", new Color( 0, 0, 0) );
		  JFormattedTextField tf = ((JSpinner.DefaultEditor) lineSpinner.getEditor()).getTextField();
		  tf.setBackground(Color.white);
		SwingUtilities.updateComponentTreeUI(frmTextToSpeech);
	}
	
	public void changeToDarkTheme(){
		  UIManager.put( "control", new Color( 128, 128, 128) );
		  UIManager.put( "info", new Color(128,128,128) );
		  UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
		  UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
		  UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
		  UIManager.put( "nimbusFocus", new Color(115,164,209) );
		  UIManager.put( "nimbusGreen", new Color(176,179,50) );
		  UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
		  UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
		  UIManager.put( "nimbusOrange", new Color(191,98,4) );
		  UIManager.put( "nimbusRed", new Color(169,46,34) );
		  UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
		  UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
		  UIManager.put( "text", new Color( 250, 250, 250) );
		  JFormattedTextField tf = ((JSpinner.DefaultEditor) lineSpinner.getEditor()).getTextField();
		  tf.setBackground(Color.black);
		SwingUtilities.updateComponentTreeUI(frmTextToSpeech);
	}
	
}

