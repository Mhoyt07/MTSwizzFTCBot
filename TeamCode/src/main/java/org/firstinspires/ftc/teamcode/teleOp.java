package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.GrabberSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PivotSubsystem;

@TeleOp
public class teleOp extends LinearOpMode {

    public void runOpMode() {

        DriveSubsystem drive_train = new DriveSubsystem(hardwareMap);

        ArmSubsystem arm = new ArmSubsystem(hardwareMap, this);

        PivotSubsystem pivot = new PivotSubsystem(hardwareMap, this);

        GrabberSubsystem grabber = new GrabberSubsystem(hardwareMap, this);

        waitForStart();
        while (opModeIsActive()) {
            //gamepad values
            double rx1 = gamepad1.right_stick_x;
            double ly1 = gamepad1.left_stick_y;
            boolean a2 = gamepad2.a;
            boolean b2 = gamepad2.b;
            boolean y2 = gamepad2.y;
            boolean down2 = gamepad2.dpad_down;
            boolean right2 = gamepad2.dpad_right;
            boolean up2 = gamepad2.dpad_up;
            boolean r_bump2 = gamepad2.right_bumper;
            boolean l_bump2 = gamepad2.left_bumper;

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

            //pivot position set
            if (up2) {
                pivot.run_to_3();
            } else if (right2) {
                pivot.run_to_2();
            } else if (down2) {
                pivot.run_to_1();
            } else {
                pivot.stop();
            }

            //grabber position setting
            if (r_bump2) {
                grabber.grab();
            } else if (l_bump2) {
                grabber.drop();
            }

            //telemetry data
            //arm data
            telemetry.addData("Arm Current Position", arm.current_pos());
            telemetry.addData("Arm Set Position", arm.current_set_pos());
            //pivot data
            telemetry.addData("Pivot 1 Current Position", pivot.current_position()[0]);
            telemetry.addData("Pivot 2 Current Position", pivot.current_position()[1]);
            telemetry.update();
        }
    }
}
