package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class MeepMeepTesting {
    static Pose2d startPoseMC = new Pose2d(12, 60, Math.toRadians(180));
    //Start Position for Main Character Robot
    static Pose2d startPoseSupport = new Pose2d(-36, 60, Math.toRadians(180));
    //Start Position for Support Robot

    static Vector2d[] tapesBlueLeft = { new Vector2d(9, 30), new Vector2d(15, 24), new Vector2d(31, 30)};
    //Location of tape spikes by stacks (Blue)
    static Vector2d[] tapesBlueRight = { new Vector2d(-24, 33), new Vector2d(-36, 24), new Vector2d(-48, 30)};
    //Location of tape spikes closest to backboard (Blue)
    static Vector2d[] boardsBlue = { new Vector2d(48, 30), new Vector2d(48, 36), new Vector2d(48, 42)};
    //Location of Backboard for Blue Alliance

    static Vector2d[] stacksBlue = { new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};

    static Vector2d midfield =  new Vector2d(0, 13);

    static Vector2d robotInWayBlueVector = new Vector2d(48, 15);
    //Vector for robotInWay
    static Pose2d robotInWayBluePosition = new Pose2d(48, 15, Math.toRadians(180));
    static Pose2d ifElseStatement = new Pose2d(48, 15, Math.toRadians(180));
    static int loc = 2;
    static int distance = 6;
    //Code to see if robot is in way


    public static void main(String[] args) throws InterruptedException {
        MeepMeep meepMeep = new MeepMeep(800);
        //New MeepMeep instance
        //Dark Matter Robot
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(14.00, 15.00)
                .setColorScheme(new ColorSchemeBlueLight())
                .build();

        RoadRunnerBotEntity alliedBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .setDimensions(18, 18)
                .setColorScheme(new ColorSchemeBlueDark())
                .build();
//Support Role (US)
        myBot.runAction(myBot.getDrive().actionBuilder(startPoseSupport)
                .strafeToConstantHeading(stacksBlue[1])
                //Intake code goes here
                .strafeToConstantHeading(new Vector2d(-18, 12))
                .splineToConstantHeading(new Vector2d(48, 18),0)
                .strafeTo(boardsBlue[loc])
                //Outtake code goes here
                .build());
//Main Character Role (Ally)
        alliedBot.runAction(alliedBot.getDrive().actionBuilder(startPoseMC)
                        .strafeTo(tapesBlueLeft[loc])
                        .strafeToConstantHeading(boardsBlue[loc])
                        .strafeTo(new Vector2d(48, 36))

                        .build());




//                .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .turn(Math.toRadians(90))
//                .lineToY(0)
//                .turn(Math.toRadians(90))



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(alliedBot)
                .start();
    }
}