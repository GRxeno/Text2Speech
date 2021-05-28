# Text2Speech

>A simple application that converts text to speech created with Java Swing

## Description

---

Text2speech is an advanced text to speech application that converts text from
a file or the build-in editor to speech. The user can open a word (.docx) or
an excel (.xslx) document, edit and save the contents of it with no, rot13
or atbash encryption and convert it to speech. The voice is fully customizable, 
the volume, pitch and rate can be adjusted with sliders.
The user can select to play the whole contents of the document,
highlight specific text or select a line by pressing the button
corresponding to the action. The app also allows the user to record
the button presses or actions and replay them at a later time.

---

## Screenshots

<img src="/screenshots/DarkTheme.png?raw=true" alt="Dark Theme" width="300" height="300" /> <img src="/screenshots/LightTheme.png?raw=true" alt="Light Theme" width="300" height="300" />

---

## Functionality

The user can:

- Open a file that is stored on a disk and view its contents. The application allows the user to open different kinds of files. It supports Microsoft Word (.docx) and Excel (.xlsx) documents. The application also allows the user to open files with encoded contents. It support different encodings, including Atbash and Rot-13. 

- Edit the contents of the file so he can produce a new version of the file that he opened.  

- Save the contents of the file that he opened on the disk. The application allows him to specify the format of the file, 
the encoding (if he can store a new version of the file that he opened).

- Choose to transform the contents of the file that the user opened to audio. 
He can listen what is in the file instead of having to read it. 

- Select a part of the contents of the file that the user opened and transform them to audio. 
He can listen only a part of the contents of the file instead of all. 

- Tune the audio parameters such as the volume, the speech rate and the pitch. Customize the audio to his needs.  

- Activate a recording operation that keeps track of a sequence of text to audio transformation actions/commands, de-activate the recording operation and replay the recorded sequence of actions.

---

## How To Run

1. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:
    ```
    git clone https://github.com/GRxeno/Text2Speech.git
    ```
2. Import it in Eclipse.

3. Add all external JARs inside the lib folder to the project (FREE TTS and POI).

4. Run the application :D

