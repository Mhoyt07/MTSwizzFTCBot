package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GrabberSubsystem {
    Servo grab;
    OpMode opMode;
    public GrabberSubsystem(HardwareMap hardwareMap, OpMode opMode) {

        grab = hardwareMap.get(Servo.class, "grabber");

        this.opMode = opMode;


    }
}
