package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDCorrection implements PIDOutput {
    public double correctionValue = 0;
    @Override
    public void pidWrite(double output) {
        this.correctionValue=output;
    }
}
