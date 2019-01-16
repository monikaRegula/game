package sample;

/**
 * Class Rockete
 * @author Monika Reguła Katarzyna Jurkowska
    @version 1.0
 */
public class Rocket {

    /**
     * Represents mass of the rocket
     */
    private double mass;
    /**
     * Represents velocity of the rocket
     */
    private double velocity;
    /**
     * Represents height on which rocket is
     */
    private double height;
    /**
     * Represents force of draw
     */
    private double k;
    /**
     * Represents fuel usage of rocket
     */
    private double fuelUsage;


    /**
     * Creates object WeatherStation with 'city' as a parameter
     * @param mass
     * @param velocity
     * @param height
     * @param k
     * @param fuelUsage
     */
    public Rocket(double mass, double velocity, double height, double k, double fuelUsage) {
        this.mass = mass;
        this.velocity = velocity;
        this.height = height;
        this.k = k;
        this.fuelUsage = fuelUsage;
    }

    /**
     * Method gets the value of rocket mass
     * @return mass
     */
    public double getMass() { return mass; }
    /**
     * Method gets the value of rocket velocity
     * @return velocity
     */
    public double getVelocity() { return velocity; }
    /**
     * Method gets the value of rocket height
     * @return height
     */
    public double getHeight() { return height; }
    /**
     * Method gets the value of rocket force of draw
     * @return mass
     */
    public double getK() { return k; }

}
