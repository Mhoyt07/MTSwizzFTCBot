package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

@TeleOp
public class teleOp extends LinearOpMode {

    public void runOpMode() {

        DriveSubsystem drive_train = new DriveSubsystem(hardwareMap);

        ArmSubsystem arm = new ArmSubsystem(hardwareMap, this);

        waitForStart();
        while (opModeIsActive()) {
            //gamepad values
            double rx1 = gamepad1.right_stick_x;
            double ly1 = gamepad1.left_stick_y;
            boolean a2 = gamepad2.a;
            boolean b2 = gamepad2.b;
            boolean y2 = gamepad1.y;

            //arcade drive
            drive_train.arcade_drive(rx1, ly1);

            //arm position set
            if (a2) {
                arm.run_to_1();
            } else if (b2) {
                arm.run_to_2();
            } else if (y2) {
                arm.run_to_3();
            } else {
                arm.stop();
            }


            //telemetry data
            telemetry.addData("Arm Current Position", arm.current_pos());
            telemetry.addData("Arm Set Position", arm.current_set_pos());
            telemetry.update();
        }
    }
}
