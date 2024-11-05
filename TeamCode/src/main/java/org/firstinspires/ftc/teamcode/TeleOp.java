package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

@TeleOp
public class TeleOp extends LinearOpMode {

    public void runOpMode() {

        DriveSubsystem drive_train = new DriveSubsystem(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            //gamepad values
            double rx1 = gamepad1.right_stick_x;
            double ly1 = gamepad1.left_stick_y;

            //arcade drive
            drive_train.arcade_drive(rx1, ly1);

        }
    }
}
