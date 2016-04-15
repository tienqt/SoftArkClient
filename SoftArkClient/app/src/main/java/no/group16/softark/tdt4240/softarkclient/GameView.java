package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by tien on 4/15/2016.
 */
public class GameView extends RelativeLayout implements IView {

    IRenderer renderer;

    //TODO: find view

    public GameView(Context context) {
        super(context);
        init();
        renderer = new CanvasRenderer();
    }

    private void init() {
        inflate(getContext(), R.layout.activity_game, this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    // TODO: create add actionlistner method for controller
}
