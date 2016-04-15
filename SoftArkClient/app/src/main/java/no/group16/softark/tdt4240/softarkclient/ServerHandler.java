package no.group16.softark.tdt4240.softarkclient;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tien on 4/14/2016.
 */
public abstract class ServerHandler   {
    LinkedList<JSONObject> outgoingMessageQueue;
    LinkedList<JSONObject> incomingMessageQueue;

    HashMap<String, ArrayList<IReceiver>> listenerMap;

    public ServerHandler() {
        outgoingMessageQueue = new LinkedList<>();
        incomingMessageQueue = new LinkedList<>();
        new SenderTask().execute();
        new ReceiverTask().execute();
    }

    void registerListener(String eventKey, IReceiver receiver) {
        ArrayList classes;
        if(listenerMap.containsKey(eventKey)) {
            classes = listenerMap.get(eventKey);
            classes.add(receiver);
        } else {
            classes = new ArrayList();
            classes.add(receiver);
            listenerMap.put(eventKey, classes);
        }
    }

    void removeListener(String eventKey, IReceiver receiver) {
        if(listenerMap.containsKey(eventKey)) {
            ArrayList classes = listenerMap.get(eventKey);

            if(classes.contains(receiver))
                classes.remove(receiver);
        }
    }

    private class SenderTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            while(true) {

                while(outgoingMessageQueue.isEmpty() == false) {
                    // TODO: dequeue outgoingMessageQueue and send to server
                    // serverConnection
                    JSONObject obj = outgoingMessageQueue.remove();
                    onMessageReadyToBeSent(obj);
                }
                // Sleep?

                if(/*disconnectNow == true*/ false)
                    break;
            }

            return null;
        }
    }

    private class ReceiverTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            boolean stop = false;

            while(!stop) {
                while(incomingMessageQueue.isEmpty() == false) {
                    JSONObject obj = incomingMessageQueue.remove();
                    onIncomingMessageFromQueue(obj);
                }
            }

            return null;
        }
    }



    /**
     * This function is called whenever a message is dequeued from incomingMessageQueue
     * @param json
     */
    public void onIncomingMessageFromQueue(JSONObject json) {
        try {
            String type = json.getString("type");
            for(IReceiver r : listenerMap.get(type)) {
                r.onReceive(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is called whenever a message is dequeued from outgoingMessageQueue
     * @param json
     */
    abstract public void onMessageReadyToBeSent(JSONObject json);

}
