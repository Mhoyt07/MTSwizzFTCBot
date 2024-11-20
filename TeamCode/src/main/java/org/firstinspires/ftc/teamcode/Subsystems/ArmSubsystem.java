package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class ArmSubsystem {
    DcMotorEx arm;
    OpMode opMode;
    Telemetry telemetry;

    public ArmSubsystem(HardwareMap hardwareMap, OpMode opMode, Telemetry telemetry) {
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        this.opMode = opMode;
        this.telemetry = telemetry;

        //resets encoder
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(0);
        arm.setTargetPositionTolerance(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setDirection(DcMotorSimple.Direction.REVERSE);


        update_pidf(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF1);



    }

    public void run_to_1() {
        update_pidf(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF1);
        arm.setTargetPosition(Constants.arm_pos1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Constants.arm_power);
    }

    public void run_to_2() {
        update_pidf(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF2);
        arm.setTargetPosition(Constants.arm_pos2);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Constants.arm_power);
    }

    public void run_to_3() {
        update_pidf(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF3);
        arm.setTargetPosition(Constants.arm_pos3);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Constants.arm_power);
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

    public void update_pidf(double kP, double kI, double kD, double kF) {
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        arm.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);
    }

    public void periodic() {
        telemetry.addData("Current Arm Position", arm.getCurrentPosition());
    }
}
