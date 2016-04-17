package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.graphics.Canvas;
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
public class CanvasRenderer extends SurfaceView implements IRenderer {

    private TouchInputEvent touchEvent;
    private ArrayList<DrawingPath> drawingPaths = new ArrayList<DrawingPath>();
    private Paint paint = new Paint();

    public CanvasRenderer(Context context) {
        super(context);
    }

    public CanvasRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasRenderer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onRender() {
        invalidate();
    }

    @Override
    public void setTouchEventListener(TouchInputEvent listener) {
        this.touchEvent = listener;
    }

    @Override
    public void addPath(DrawingPath drawingPath) {
        drawingPaths.add(drawingPath);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.touchEvent.onTouchInputEvent(new Point((int)event.getX(), (int)event.getY()), event.getAction());

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

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
    }


}
