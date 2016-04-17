package no.group16.softark.tdt4240.softarkclient;

import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Even on 17.04.2016.
 */
public class DrawingPath {
    String drawingToolId;
    String colorId;
    ArrayList<Point> points;

    public DrawingPath(ArrayList<Point> points, String toolId, String colorId) {
        this.colorId = colorId;
        this.drawingToolId = toolId;
        this.points = points;
    }

    public void setDrawingToolId(String drawingToolId) {
        this.drawingToolId = drawingToolId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPath(ArrayList<Point> points) {
        this.points = points;
    }
}