package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class GrabberSubsystem {
    Servo grab;
    OpMode opMode;
    public GrabberSubsystem(HardwareMap hardwareMap, OpMode opMode) {

        grab = hardwareMap.get(Servo.class, "grabber");

        this.opMode = opMode;

        //reverses direction
        //grab.setDirection(Servo.Direction.REVERSE);

    }

    public void drop() {
        grab.setPosition(Constants.empty);
    }

    //puts grabber in full position. Grabs item
    public void grab() {
        grab.setPosition(Constants.full);
    }

}
