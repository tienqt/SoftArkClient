package no.group16.softark.tdt4240.softarkclient;

import android.graphics.Point;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by tien on 4/14/2016.
 */
public interface IRenderer {
    public void onUpdate();
    public void onRender();
    public void setDrawerListener(View.OnTouchListener listener);
    public void addPath(DrawingPath drawingPath);

}
