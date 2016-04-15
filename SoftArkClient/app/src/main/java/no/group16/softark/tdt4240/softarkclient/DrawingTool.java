package no.group16.softark.tdt4240.softarkclient;

/**
 * Created by tien on 4/14/2016.
 */
public abstract class DrawingTool {
    final String toolId;

    public DrawingTool(String toolId) {
        this.toolId = toolId;
    }

    public String getToolId() {
        return toolId;
    }
}
