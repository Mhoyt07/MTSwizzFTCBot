package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Constants;

public class ArmSubsystem {
    DcMotorEx arm;
    OpMode opMode;

    public ArmSubsystem(HardwareMap hardwareMap, OpMode opMode) {
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        this.opMode = opMode;

        //resets encoder
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(0);
        arm.setTargetPositionTolerance(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        PIDFCoefficients pidf_vals = new PIDFCoefficients(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF);
        arm.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);



    }

    public void run_to_1() {
        arm.setTargetPosition(Constants.arm_pos1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Constants.arm_power);
    }

    public void run_to_2() {
        arm.setTargetPosition(Constants.arm_pos2);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Constants.arm_power);
    }

    public void run_to_3() {
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
}
