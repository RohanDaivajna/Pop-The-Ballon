// Author: Rohan Daivajna 
// Roll Number: 35
// Title: Outdoor Game
// Start Date: 22-09-2024
// Modified Date: 22-09-2024
// Description: A fun and engaging balloon popping game where players aim to click and pop balloons while avoiding missing too many, with adjustable difficulty levels and dynamic gameplay elements.

class Balloon {
    int x, y; // The x and y coordinates of the balloon
    double speed; // The speed at which the balloon moves

    // Constructor to initialize the position and speed of the balloon
    public Balloon(int x, int y, double speed) {
        this.x = x; // Set the initial horizontal position
        this.y = y; // Set the initial vertical position
        this.speed = speed; // Set the balloon's speed
    }

    // Method to move the balloon upwards (decreasing y position)
    public void move() {
        y -= speed; // Decrease the y-coordinate by the speed to move the balloon up
    }
}
