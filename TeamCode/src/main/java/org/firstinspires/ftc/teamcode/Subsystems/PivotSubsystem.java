package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class PivotSubsystem {
    DcMotorEx pivot_1;
    DcMotorEx pivot_2;
    OpMode opMode;
    int pos1;
    int pos2;
    int pos3;
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

        //pidf stuff
        double kP = 3;
        double kI = 0;
        double kD = 0;
        double kF = 0;
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        pivot_1.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);
        pivot_2.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);

        //pivot positions
        pos1 = 0;
        pos2 = 120;
        pos3 = 200;
    }

    public void run_to_1() {
        pivot_1.setTargetPosition(pos1);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(1);
        pivot_2.setTargetPosition(pos1);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(1);
    }
    public void run_to_2() {
        pivot_1.setTargetPosition(pos2);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(1);
        pivot_2.setTargetPosition(pos2);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(1);
    }
    public void run_to_3() {
        pivot_1.setTargetPosition(pos3);
        pivot_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_1.setPower(1);
        pivot_2.setTargetPosition(pos3);
        pivot_2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot_2.setPower(1);
    }

    public void stop() {
        pivot_1.setPower(0);
        pivot_2.setPower(0);
    }

    public int[] current_position() {
        return new int[]{pivot_1.getCurrentPosition(), pivot_2.getCurrentPosition()};
    }
}
