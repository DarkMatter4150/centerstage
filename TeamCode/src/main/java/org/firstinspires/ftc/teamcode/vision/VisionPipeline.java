package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VisionPipeline extends OpenCvPipeline {

    public final int WIDTH = 640;
    public final int HEIGHT = 480;

    public Scalar lowBlue = new Scalar(90, 100, 100);
    public Scalar highBlue = new Scalar(145, 255, 255);

    public Scalar lowRed = new Scalar(0, 100, 100);
    public Scalar highRed = new Scalar(40, 255, 255);

    private volatile vPos pos = vPos.RIGHT;

    Mat blur = new Mat();

    Mat hsv = new Mat();
    Mat mask = new Mat();

    Mat edges = new Mat();

    Mat out = new Mat();

    Mat hierarchy = new Mat();

    boolean isBlue = false;

    public VisionPipeline() {};
    public VisionPipeline(String color) {
        isBlue = Objects.equals(color, "BLUE");
    }


    @Override
    public Mat processFrame(Mat input) {

        Imgproc.GaussianBlur(input, blur, new Size(3, 3), 0, 0);

        Imgproc.cvtColor(blur, hsv, Imgproc.COLOR_RGB2HSV);
        if(isBlue) {
            Core.inRange(hsv, lowBlue, highBlue, mask);
        }
        else
        {
            Core.inRange(hsv, lowRed, highRed, mask);
        }

        Imgproc.Canny(mask, edges, 100, 300);

        List<MatOfPoint> contours = new ArrayList<>();

        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        Rect[] boundRect = new Rect[contours.size()];
        for (int i = 0; i < contours.size(); i++) {
            contoursPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
        }

        // bounding boxes
        double maxArea = 0;
        int maxAreaX = 0;
        for (int i = 0; i != boundRect.length; i++) {
            if (boundRect[i].area() > maxArea) {
                maxArea = boundRect[i].area();
                maxAreaX = boundRect[i].x;
            }
            // draw bounding rectangles on mat
            // the mat has been converted to HSV so we need to use HSV as well
            Imgproc.rectangle(hsv, boundRect[i], new Scalar(255, 0, 0));
        }

        if (maxAreaX < WIDTH / 3) {
            pos = vPos.LEFT;
        } else if (maxAreaX < WIDTH * 2 / 3) {
            pos = vPos.CENTER;
        } else {
            pos = vPos.RIGHT;
        }

        Imgproc.cvtColor(hsv, out, Imgproc.COLOR_HSV2RGB);

        return out;
    }

    public enum vPos {
        LEFT,
        CENTER,
        RIGHT,
    }

    public vPos getPos() {
        return pos;
    }
}
