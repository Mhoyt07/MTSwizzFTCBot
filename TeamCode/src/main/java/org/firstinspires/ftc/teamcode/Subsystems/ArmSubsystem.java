package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class ArmSubsystem {
    DcMotorEx arm;
    OpMode opMode;
    int pos1;
    int pos2;
    int pos3;

    public ArmSubsystem(HardwareMap hardwareMap, OpMode opMode) {
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        this.opMode = opMode;

        //resets encoder
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPositionTolerance(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //arm pidf values
        double kP = 5;
        double kI = 0;
        double kD = 0;
        double kF = 0;
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        arm.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);


        //arm positions
        pos1 = 0;
        pos2 = -1000;
        pos3 = -2000;
    }

    public void run_to_1() {
        arm.setTargetPosition(pos1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(1);
    }

    public void run_to_2() {
        arm.setTargetPosition(pos2);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(1);
    }

    public void run_to_3() {
        arm.setTargetPosition(pos3);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(1);
    }

    public void stop() {
        arm.setPower(0);
    }

    public int current_pos() {
        return arm.getCurrentPosition();
    }

    public int current_set_pos() {
        return arm.getTargetPosition();
    }
}
