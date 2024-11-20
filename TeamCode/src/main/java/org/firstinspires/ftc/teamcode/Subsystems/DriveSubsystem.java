package org.firstinspires.ftc.teamcode.Subsystems;

import androidx.core.math.MathUtils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Constants;

public class DriveSubsystem {
    DcMotorEx back_right;
    DcMotorEx back_left;
    IMU imu;
    IMU.Parameters imu_param;

    YawPitchRollAngles robot_orientation;

    public DriveSubsystem(HardwareMap hardwareMap) {
        back_right = hardwareMap.get(DcMotorEx.class, "br");
        back_left = hardwareMap.get(DcMotorEx.class, "bl");
        imu = hardwareMap.get(IMU.class, "imu");


        //reverse directions
        back_right.setDirection(DcMotorSimple.Direction.REVERSE);
        //back_left.setDirection(DcMotorSimple.Direction.REVERSE);

        //imu parameters
        imu_param = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        );
        //initializing imu
        imu.initialize(imu_param);
    }

    public void arcade_drive(double rx_joystick, double ly_joystick) {
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setPower(ly_joystick + rx_joystick);
        back_left.setPower(ly_joystick - rx_joystick);
    }

    public void tank_drive(double ry_joystick, double ly_joystick) {
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setPower(ry_joystick);
        back_left.setPower(ly_joystick);
    }

    public void move_ticks(int ticks, double speed) {
        //reset encoder first
        this.reset_encoder();
        //this.reset_encoder();
        back_right.setTargetPosition(ticks);
        back_left.setTargetPosition(ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setPower(speed);
        back_left.setPower(speed);
    }


    public void reset_encoder() {
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public double get_Yaw() {
        robot_orientation = imu.getRobotYawPitchRollAngles();
        return robot_orientation.getYaw(AngleUnit.DEGREES);
    }

    //goes from -180 to 180
    public void rotate_degrees(double target) {
        while (this.get_Yaw() != target) {
            double distance = target - this.get_Yaw();

            //sets motor speeds
            back_left.setPower(MathUtils.clamp(distance * Constants.rot_kP, -1, 1));
            back_right.setPower(MathUtils.clamp(-distance * Constants.rot_kP, -1, 1));
        }
    }

    //checks if motors are busy
    public boolean bl_busy() {
        return back_left.isBusy();
    }
    public boolean br_busy() {
        return back_right.isBusy();
    }
}
