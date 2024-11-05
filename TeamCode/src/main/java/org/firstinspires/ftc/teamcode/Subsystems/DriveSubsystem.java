package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {
    DcMotorEx back_right;
    DcMotorEx back_left;

    public DriveSubsystem(HardwareMap hardwareMap) {
        back_right = hardwareMap.get(DcMotorEx.class, "br");
        back_left = hardwareMap.get(DcMotorEx.class, "bl");

        //reverse directions
        //back_right.setDirection(DcMotorSimple.Direction.REVERSE);
        //back_left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void arcade_drive(double rx_joystick, double ly_joystick) {
        back_right.setPower(ly_joystick - rx_joystick);
        back_left.setPower(ly_joystick + rx_joystick);
    }

    public void tank_drive(double ry_joystick, double ly_joystick) {
        back_right.setPower(ry_joystick);
        back_left.setPower(ly_joystick);
    }
}
