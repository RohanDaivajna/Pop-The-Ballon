// Author: Rohan Daivajna 
// Roll Number: 35
// Title: Outdoor Game
// Start Date: 22-09-2024
// Modified Date: 03-10-2024
// Description: A fun and engaging balloon popping game where players aim to click and pop balloons while avoiding missing too many, with adjustable difficulty levels and dynamic gameplay elements.

import java.awt.*; // Import for GUI components and layout
import java.awt.event.*; // Import for event handling
import javax.swing.*; // Import for Swing components

class MainMenuPanel extends JPanel {
    private Image backgroundImage; // Background image for the main menu
    private JButton startButton; // Button to start the game
    private JButton exitButton; // Button to exit the application
    private JButton instructionsButton; // Button to show game instructions
    private JLabel titleLabel; // Label for the game title

    // Constructor to set up the main menu panel
    public MainMenuPanel(ActionListener startGameAction) {
        backgroundImage = new ImageIcon("img/bg.jpg").getImage(); // Load background image
        
        // Set layout to null for custom component positioning
        setLayout(null);
        
        // Create and configure the game title label
        titleLabel = new JLabel("POP THE BALLOONS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Set font style and size
        titleLabel.setForeground(Color.RED); // Set the title color to red
        titleLabel.setBounds(300, 200, 400, 50); // Set position and size of the title
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        
        // Create and configure the "Start Game" button
        startButton = new JButton("Start Game");
        startButton.setBounds(400, 300, 200, 50); // Set position and size of the button
        startButton.addActionListener(startGameAction); // Attach action to start the game
        
        // Create and configure the "Instructions" button
        instructionsButton = new JButton("Instructions");
        instructionsButton.setBounds(400, 370, 200, 50); // Set position and size of the button
        instructionsButton.addActionListener(e -> showInstructions()); // Attach action to show instructions
        
        // Create and configure the "Exit" button
        exitButton = new JButton("Exit");
        exitButton.setBounds(400, 440, 200, 50); // Set position and size of the button
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the application
            }
        }); // Attach action to exit the program

        // Add the components (title, start button, instructions button, exit button) to the panel
        add(titleLabel);
        add(startButton);
        add(instructionsButton);
        add(exitButton);
    }

    // Method to show game instructions
    private void showInstructions() {
        String instructions = "Welcome to 'Pop the Balloons'!\n"
                            + "By:Rohan_Daivajna\n\n"
                            + "Instructions:\n"
                            + "- Use your mouse to aim at the balloons.\n"
                            + "- Click on the balloons to pop them.\n"
                            + "- Try to pop as many balloons as you can before they fly up the sky!\n"
                            + "- If 10 balloons fly up without being popped its game over!!\n"
                            + "- Have fun!";
        
        JOptionPane.showMessageDialog(this, instructions, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    // Override paintComponent to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method for proper rendering
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image
    }
}
