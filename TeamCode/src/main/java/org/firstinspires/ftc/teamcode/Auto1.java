package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

@Autonomous
public class Auto1 extends LinearOpMode {

    @Override
    public void runOpMode() {

        DriveSubsystem drive_train = new DriveSubsystem(hardwareMap);

        //reset drive motor encoders
        drive_train.reset_encoder();

        waitForStart();

        //move robot forward 1000 ticks
        drive_train.move_ticks(1000, 1);

        while (opModeIsActive() && (drive_train.bl_busy() || drive_train.br_busy())) {
        }

        //rotates 90 degrees clockwise
        drive_train.rotate_degrees(90);

        while (opModeIsActive() && (drive_train.bl_busy() || drive_train.br_busy())) {
        }

        //drives forward 500 ticks
        drive_train.move_ticks(500, 1);

        while (opModeIsActive() && (drive_train.bl_busy() || drive_train.br_busy())) {
        }

        drive_train.rotate_degrees(0);
    }
}
