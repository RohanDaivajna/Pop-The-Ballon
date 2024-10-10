// Author: Rohan Daivajna 
// Roll Number: 35
// Title: Outdoor Game
// Start Date: 22-09-2024
// Modified Date: 29-09-2024
// Description: A fun and engaging balloon popping game where players aim to click and pop balloons while avoiding missing too many, with adjustable difficulty levels and dynamic gameplay elements.

import java.awt.*; // Import for layout and event handling
import javax.swing.*; // Import for GUI components

public class GameWindow extends JFrame {
    private CardLayout cardLayout; // Manages switching between panels (Main Menu, Game)
    private GamePanel gamePanel; // Panel for the actual game
    private MainMenuPanel mainMenuPanel; // Panel for the main menu

    // Constructor for the GameWindow
    public GameWindow() {
        setTitle("Balloon Game"); // Set the window title
        setSize(1024, 672); // Set the window size (same as game resolution)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when window is closed
        setResizable(false); // Prevent resizing the window

        cardLayout = new CardLayout(); // Initialize the CardLayout to switch between panels
        setLayout(cardLayout); // Set the layout of the frame to CardLayout

        gamePanel = new GamePanel(); // Initialize the game panel where the game happens
        // Initialize the main menu panel and pass an ActionListener that starts the game
        mainMenuPanel = new MainMenuPanel(e -> startGame());

        // Add both panels to the frame. Each panel is identified by a string
        add(mainMenuPanel, "MainMenu"); // Add the main menu panel with identifier "MainMenu"
        add(gamePanel, "Game"); // Add the game panel with identifier "Game"

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
    }

    // Method to start the game when called
    private void startGame() {
        // Switch to the game panel
        cardLayout.show(this.getContentPane(), "Game");
        // Start the game logic in the game panel
        gamePanel.startGame(); 
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new GameWindow(); // Create an instance of GameWindow, which opens the GUI
    }
}
