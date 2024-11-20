package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Constants;

public class PivotSubsystem {
    DcMotorEx pivot_1;
    DcMotorEx pivot_2;
    OpMode opMode;
    public PivotSubsystem(HardwareMap hardwareMap, OpMode opMode) {
        pivot_1 = hardwareMap.get(DcMotorEx.class, "pivot1");
        pivot_2 = hardwareMap.get(DcMotorEx.class, "pivot2");

        this.opMode = opMode;

        //reset encoders
        pivot_1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivot_2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //setes target position to be initial position
        pivot_1.setTargetPosition(0);
        pivot_2.setTargetPosition(0);
        //sets target position tolerance to 0
        pivot_1.setTargetPositionTolerance(0);
        pivot_2.setTargetPositionTolerance(0);
        //puts motors in run to position mode
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pivot_2.setDirection(DcMotorSimple.Direction.REVERSE);


        update_pidf(Constants.pivot_kP, Constants.pivot_kI, Constants.pivot_kD, Constants.pivot_kF1);


    }

    public void run_to_1() {
        update_pidf(Constants.pivot_kP, Constants.pivot_kI, Constants.pivot_kD, Constants.pivot_kF1);
        pivot_1.setTargetPosition(Constants.pivot_pos1);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(Constants.pivot_power);
        pivot_2.setTargetPosition(Constants.pivot_pos1);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(Constants.pivot_power);
    }
    public void run_to_2() {
        update_pidf(Constants.pivot_kP, Constants.pivot_kI, Constants.pivot_kD, Constants.pivot_kF2);
        pivot_1.setTargetPosition(Constants.pivot_pos2);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(Constants.pivot_power);
        pivot_2.setTargetPosition(Constants.pivot_pos2);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(Constants.pivot_power);
    }
    public void run_to_3() {
        update_pidf(Constants.pivot_kP, Constants.pivot_kI, Constants.pivot_kD, Constants.pivot_kF3);
        pivot_1.setTargetPosition(Constants.pivot_pos3);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(Constants.pivot_power);
        pivot_2.setTargetPosition(Constants.pivot_pos3);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(Constants.pivot_power);
    }

    public void stop() {
        pivot_1.setPower(0);
        pivot_2.setPower(0);
    }

    public int[] current_position() {
        return new int[]{pivot_1.getCurrentPosition(), pivot_2.getCurrentPosition()};
    }

    public void update_pidf(double kP, double kI, double kD, double kF) {
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        pivot_1.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);
        pivot_2.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);

    }
}
