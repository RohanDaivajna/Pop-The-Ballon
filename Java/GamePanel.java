// Author: Rohan Daivajna 
// Roll Number: 35
// Title: Outdoor Game
// Start Date: 22-09-2024
// Modified Date: 22-09-2024
// Description: A fun and engaging balloon popping game where players aim to click and pop balloons while avoiding missing too many, with adjustable difficulty levels and dynamic gameplay elements.

import java.awt.*; // Import for Graphics and AWT components
import java.awt.event.*; // Import for event handling
import java.util.ArrayList; // Import for ArrayList
import java.util.Random; // Import for generating random numbers
import javax.swing.*; // Import for Swing components

class GamePanel extends JPanel {
    private ArrayList<Balloon> balloons; // List to store all balloons in the game
    private Random random; // Random generator for balloon positions
    private Image balloonImage; // Image for the balloon
    private Image backgroundImage; // Image for the game background
    private int score; // Player's score
    private int missedBalloons; // Number of balloons missed by the player
    private boolean gameOver; // Flag to check if the game is over
    private Timer timer; // Timer to control game updates
    private double balloonSpeed; // Speed of the balloons
    private boolean paused; // Flag to check if the game is paused
    private JButton pauseButton; // Button to pause or resume the game

    // Constructor to set up the game panel
    public GamePanel() {
        initializeGame(); // Initialize game variables
        loadImages(); // Load game images (background and balloon)
        setupTimers(); // Set up timers for game updates and speed increase
        setupMouseListener(); // Set up mouse click listener for balloon popping
        setupPauseButton(); // Set up pause button to pause/resume the game
    }

    // Initialize game variables
    private void initializeGame() {
        balloons = new ArrayList<>(); // Initialize list to hold balloons
        random = new Random(); // Initialize random generator
        score = 0; // Set initial score
        missedBalloons = 0; // Set initial count of missed balloons
        gameOver = false; // Game is not over initially
        paused = false; // Game is not paused initially
        balloonSpeed = 2.0; // Initial speed of the balloons

        // Add 3 balloons to start the game
        for (int i = 0; i < 3; i++) {
            addBalloon();
        }
    }

    // Load the balloon and background images
    private void loadImages() {
        balloonImage = new ImageIcon("img/balloon.png").getImage(); // Load balloon image
        backgroundImage = new ImageIcon("img/bg.jpg").getImage(); // Load background image
    }

    // Set up timers for game updates and balloon speed increment
    private void setupTimers() {
        // Timer to update the game every 20 milliseconds
        timer = new Timer(20, e -> {
            if (!paused) { // Update game only if not paused
                update(); // Update game state (move balloons, check collisions)
            }
            repaint(); // Repaint the panel to reflect updated game state
        });
        timer.start(); // Start the game update timer

        // Timer to increase balloon speed every 5 seconds
        new Timer(5000, e -> {
            if (!gameOver && !paused) { // Increase speed only if the game is running
                balloonSpeed += 0.1; // Gradually increase balloon speed
            }
        }).start(); // Start the balloon speed increment timer
    }

    // Set up mouse listener for detecting balloon pops
    private void setupMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameOver && !paused) { // Check balloon clicks only if the game is running
                    checkBalloonClick(e.getX(), e.getY()); // Check if a balloon was clicked
                }
            }
        });
    }

    // Set up the pause button
    private void setupPauseButton() {
        pauseButton = new JButton("Pause"); // Create a button labeled "Pause"
        pauseButton.setBounds(920, 10, 80, 30); // Set the button position and size
        pauseButton.addActionListener(e -> togglePause()); // Add action listener for pause/resume
        setLayout(null); // Set layout to null to allow absolute positioning
        add(pauseButton); // Add the pause button to the panel
    }

    // Start the game by re-initializing and starting the timer
    public void startGame() {
        initializeGame(); // Reset game variables
        timer.start(); // Start the game timer
    }

    // Toggle the pause state of the game
    private void togglePause() {
        paused = !paused; // Toggle the pause flag
        pauseButton.setText(paused ? "Resume" : "Pause"); // Update button text based on game state
    }

    // Update the game logic
    private void update() {
        if (!gameOver) { // Only update if the game is not over
            for (Balloon balloon : balloons) {
                balloon.speed = balloonSpeed; // Set balloon speed
                balloon.move(); // Move the balloon upwards

                // Check if a balloon has gone off-screen
                if (balloon.y < -50) {
                    missedBalloons++; // Increment missed balloons counter
                    // End the game if more than 10 balloons are missed
                    if (missedBalloons > 10) {
                        gameOver = true;
                        timer.stop(); // Stop the game timer when game is over
                    }
                    // Reset balloon to the bottom with a random horizontal position
                    balloon.y = 672;
                    balloon.x = random.nextInt(1024 - 40);
                }
            }

            // Randomly add new balloons to the game
            if (random.nextInt(50) == 0) {
                addBalloon();
            }
        }
    }

    // Add a new balloon to the game, ensuring no overlapping with existing balloons
    private void addBalloon() {
        int x;
        boolean overlapping;
        do {
            x = random.nextInt(1024 - 40); // Random horizontal position for the balloon
            overlapping = false;
            // Check for overlap with existing balloons
            for (Balloon balloon : balloons) {
                if (Math.abs(balloon.x - x) < 30) { // Check if the balloons are too close
                    overlapping = true;
                    break;
                }
            }
        } while (overlapping); // Repeat until no overlapping

        balloons.add(new Balloon(x, 672, balloonSpeed)); // Add the new balloon at the bottom
    }

    // Check if a balloon was clicked and remove it if so
    private void checkBalloonClick(int mouseX, int mouseY) {
        for (Balloon balloon : balloons) {
            // Check if the click is within the bounds of a balloon
            if (mouseX >= balloon.x && mouseX <= balloon.x + 40 &&
                mouseY >= balloon.y && mouseY <= balloon.y + 70) {
                balloons.remove(balloon); // Remove the balloon from the list
                score++; // Increment the player's score
                break;
            }
        }
    }

    // Paint the game components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to ensure proper rendering
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30); // Draw the score
        g.drawString("Missed: " + missedBalloons, 10, 60); // Draw the missed balloon count

        // Draw each balloon on the screen
        for (Balloon balloon : balloons) {
            g.drawImage(balloonImage, balloon.x, balloon.y, 50, 100, this);
        }

        // If the game is over, display a "Game Over" message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over!", 300, 300);
        }
    }
}
