package frc.robot.util;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;

public class PIDConfig implements Sendable {

    private double Kp;
    private double Ki;
    private double Kd;
    private double Kf;

    public PIDController getController(double period) {
        return new PIDController(Kp, Ki, Kd, period);
    }

    /**
     * Constructs a Generic PIDConfig (Wrapper class to include all of the PID's
     * values)
     * 
     * @param Kp - Proportional value
     * @param Ki - Integral value
     * @param Kd - Derrivative value
     */
    public PIDConfig(double Kp, double Ki, double Kd, double Kf) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.Kf = Kf;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Kp", this::getKp, this::setKp);
        builder.addDoubleProperty("Ki", this::getKi, this::setKi);
        builder.addDoubleProperty("Kd", this::getKd, this::setKd);
        builder.addDoubleProperty("Kf", this::getKf, this::setKf);
    }

    public double getKp() {
        return this.Kp;
    }

    public double getKi() {
        return this.Ki;
    }

    public double getKd() {
        return this.Kd;
    }

    public double getKf() {
        return this.Kf;
    }

    public void setKp(double Kp) {
        this.Kp = Kp;
    }

    public void setKi(double Ki) {
        this.Ki = Ki;
    }

    public void setKd(double Kd) {
        this.Kd = Kd;
    }

    public void setKf(double Kf) {
        this.Kf = Kf;
    }
}
