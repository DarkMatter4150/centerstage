package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class MeepMeepTesting {
    static Pose2d startPoseMCBlue = new Pose2d(12, 62, Math.toRadians(180));
    //Start Position for Main Character Robot
    static Pose2d startPoseSupport = new Pose2d(-40, 62, Math.toRadians(270));
    //Start Position for Support Robot
     static Vector2d parkSupportBlue = new Vector2d(62, 15);

     static Vector2d parkMCBlue = new Vector2d(62, 60);

     static Vector2d parkMCRed = new Vector2d(62, -60);


    static Vector2d[] tapesBlueRight = { new Vector2d(9, 30), new Vector2d(15, 24), new Vector2d(31, 30)};
    //Location of tape spikes by backboard (Blue)
    //MeepMeep View
    static Vector2d[] tapesBlueLeft = { new Vector2d(-47, 36), new Vector2d(-36, 24), new Vector2d(-24, 36)};
    //Location of tape spikes closest to stacks (Blue)
    //MeepMeep View
    static Vector2d[] boardsBlue = { new Vector2d(40, 30), new Vector2d(40, 36), new Vector2d(40, 42)};
    //Location of Backboard for Blue Alliance

    static Vector2d[] stacksBlue = { new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};
      //Location of stacks for Blue Alliance


    static Pose2d startPoseMCRed = new Pose2d(12, -62, Math.toRadians(270));
    static Pose2d startPoseRedSup = new Pose2d(-40, -62, Math.toRadians(90));
    //Location for Red SUpport Role
    static Vector2d[] tapesRedLeft = { new Vector2d(-55, -30), new Vector2d(-41, -22), new Vector2d(-32, -30)};

         //Location of Tapes for Red Alliance Stacks
    static Vector2d[] tapesRedRight = { new Vector2d(9, -30), new Vector2d(15, -24), new Vector2d(31, -30)};
    static Vector2d[] boardsRed = { new Vector2d(48, -30), new Vector2d(48, -36), new Vector2d(48, -42)};
    //Backboard location for Red Alliance
    static Vector2d[] stacksRed = { new Vector2d(-60, -36), new Vector2d(-60, -24), new Vector2d(-60, -12)};

    static Vector2d parkSupportRed = new Vector2d(62, -15);


    static int locBlue = 0;
    static int locRed = 0;
    static int distance = 6;
    //Code to see if robot is in way


    public static void main(String[] args) throws InterruptedException {
        MeepMeep meepMeep = new MeepMeep(500);
        //New MeepMeep instance
        //Dark Matter Robot
        RoadRunnerBotEntity myBotBlueSupport = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(14.00, 15.00)
                .setColorScheme(new ColorSchemeBlueLight())
                .build();

        RoadRunnerBotEntity myBotBlueMC = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(14.00, 15.00)
                .setColorScheme(new ColorSchemeBlueDark())
                .build();

       RoadRunnerBotEntity myBotRedSupport = new DefaultBotBuilder(meepMeep)
               .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(14, 15)
               .setColorScheme(new ColorSchemeRedLight())
               .build();
       RoadRunnerBotEntity myBotRedMC = new DefaultBotBuilder(meepMeep)
               .setConstraints(60, 60,Math.toRadians(180), Math.toRadians(180), 15)
                       .setDimensions(14, 15)
                       .setColorScheme(new ColorSchemeRedDark())
                       .build();

//Support Role (US)
        myBotBlueSupport.runAction(myBotBlueSupport.getDrive().actionBuilder(startPoseSupport)
//     //Use for DELTA Scrimmage
       //Coords for DELTA
                //tapesBlueRight
                //2   (-24, 36)
                //1 (-36, 24)
                //0 (-48, 36)
                //Updated Blue Support ROLE (USE FOR STATE)
                                .strafeToConstantHeading(new Vector2d(-36, 36))
                                .strafeToConstantHeading(tapesBlueLeft[locBlue])
                                .strafeToConstantHeading(new Vector2d(-36, 36))
                                .strafeToConstantHeading(new Vector2d(-35, 10))
                                .strafeToLinearHeading(new Vector2d(48, 13),Math.PI)

                                .waitSeconds(2.6)
                                .strafeTo(boardsBlue[locBlue])
                                .strafeTo(new Vector2d(44, boardsBlue[locBlue].y))
                                .strafeTo(new Vector2d(48, 7))
                                .strafeTo(new Vector2d(62, 15))









           //State pathing
              //Use for state
                    //Coords for state
                // tapes
                    //2 (-37.25, 30)
                    //1 (-40, 23)
                    //0 (-55, 33)
//                    .strafeToLinearHeading(tapesBlueLeft[locBlue], 220)
//                    .strafeToLinearHeading(new Vector2d(-45, 10), Math.PI)
//                    .strafeToConstantHeading(new Vector2d(48, 12))
//                    .strafeTo(boardsBlue[locBlue])
//                    .strafeTo(new Vector2d(36, 12))
//                    .strafeTo(stacksBlue[2])
//                    .strafeTo(new Vector2d(48, 12))
//                    .strafeTo(boardsBlue[0])
//                     .strafeTo(new Vector2d(36, 12))
//                     .strafeTo(stacksBlue[2])
//                     .strafeTo(new Vector2d(48, 12))
//                     .strafeTo(boardsBlue[0])
//                     .strafeTo(new Vector2d(48, 12))
//                    .strafeTo(parkSupportBlue)

                     .build());



        //Auto Main Blue

        myBotBlueMC.runAction(myBotBlueMC.getDrive().actionBuilder(startPoseMCBlue)
                        .strafeTo(tapesBlueRight[locBlue])
                        .strafeToConstantHeading(boardsBlue[locBlue])
//                        .strafeTo(boardsBlue[1])
//                        .strafeTo(stacksBlue[0])
//                        .strafeTo(boardsBlue[1])
//                        .strafeTo(stacksBlue[0])
//                        .strafeTo(boardsBlue[1])
//                        .strafeTo(stacksBlue[0])
//                        .strafeTo(boardsBlue[1])
                        .strafeTo(new Vector2d(48, 60))
                        .strafeTo(parkMCBlue)
                         
                        



                        .build());
        myBotRedSupport.runAction(myBotRedSupport.getDrive().actionBuilder(startPoseRedSup)
                        .strafeToLinearHeading(tapesRedLeft[locRed], 50)
                        .strafeToLinearHeading(new Vector2d(-45, -10), Math.PI)
                        .strafeTo(new Vector2d(48, -10))
                        .strafeTo(boardsRed[locRed])
//                        .waitSeconds(.5)
//                        .strafeTo(new Vector2d(36, -12))
//                        .strafeTo(stacksRed[2])
//                        .strafeTo(new Vector2d(48, -10))
//                        .strafeTo(boardsRed[0])
//                        .strafeTo(new Vector2d(36, -12))
//                        .strafeTo(stacksRed[2])
//                        .strafeTo(new Vector2d(48, -10))
//                        .strafeTo(boardsRed[0])
                        .strafeTo(new Vector2d(48, -10))
                        .strafeTo(parkSupportRed)

                                .build());

        myBotRedMC.runAction(myBotRedMC.getDrive().actionBuilder(startPoseMCRed)
                        .strafeTo(tapesRedRight[locRed])
                        .strafeTo(boardsRed[locRed])
//                        .strafeTo(boardsRed[1])
//                        .strafeTo(stacksRed[0])
//                        .strafeTo(boardsRed[1])
//                        .strafeTo(stacksRed[0])
//                        .strafeTo(boardsRed[1])
//                        .strafeTo(stacksRed[0])
//                        .strafeTo(boardsRed[1])
                        .strafeTo(new Vector2d(48, -60))
                        .strafeTo(parkMCRed)
                        
                        .build());







        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBotBlueSupport)
                .addEntity(myBotBlueMC)
                .addEntity(myBotRedSupport)
                .addEntity(myBotRedMC)
                .start();
    }
}