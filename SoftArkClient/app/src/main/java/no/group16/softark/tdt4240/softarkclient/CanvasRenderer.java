package no.group16.softark.tdt4240.softarkclient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by tien on 4/14/2016.
 */
public class CanvasRenderer implements IRenderer {

    private ArrayList<DrawingPath> drawingPaths = new ArrayList<DrawingPath>();
    private Paint paint = new Paint();
    SurfaceView surfaceView;

    public CanvasRenderer(View context) {
        this.surfaceView = (SurfaceView)(context).findViewById(R.id.surfaceView);
    }

    @Override
    public void onUpdate() {

    }


    @Override
    public void onRender() {
        Canvas canvas = surfaceView.getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);

        for(DrawingPath drawingPath : drawingPaths) {
            Path path = new Path();

            for(int i = 0; i < drawingPath.points.size(); i++) {
                int x = drawingPath.points.get(i).x;
                int y = drawingPath.points.get(i).y;

                if(i == 0)
                    path.moveTo(x, y);
                else
                    path.lineTo(x, y);
            }

            canvas.drawPath(path, paint);
        }
        surfaceView.getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void setDrawerListener(View.OnTouchListener listener) {
        surfaceView.setOnTouchListener(listener);
    }

    @Override
    public void addPath(DrawingPath drawingPath) {
        drawingPaths.add(drawingPath);
    }

}
