package no.group16.softark.tdt4240.softarkclient;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by tien on 4/14/2016.
 */
public class KyroNetServerHandler extends ServerHandler {

    Socket serverConnection;

    public KyroNetServerHandler() {
        super();
    }

    public void connectToServer(String ip, int port) {
        new ClientTask(ip, port).execute();
    }

    public void queueMessage(JSONObject json) {
        outgoingMessageQueue.add(json);
    }

    private void queueIncomingMessage(JSONObject json) {
        incomingMessageQueue.add(json);
    }

    public Queue<JSONObject> getIncomingMessageQueue() {
        return incomingMessageQueue;
    }

    /**
     * This function is called whenever a message is dequeued from incomingMessageQueue
     * @param json
     */
    @Override
    public void onIncomingMessageFromQueue(JSONObject json) {
       super.onIncomingMessageFromQueue(json);
    }

    /**
     * This function is called whenever a message is dequeued from outgoingMessageQueue
     * @param json
     */
    @Override
    public void onMessageReadyToBeSent(JSONObject json) {
        // TODO: Send the message to the server
    }


    /**
     * This part connects to the server and listen for data for ever.
     * It should queue the received messages to incomingMessageQueue
     */
    private class ClientTask extends AsyncTask<Void, Void, Void> {
        int port;
        String ip;

        public ClientTask(String ip, int port) {
            this.ip =  ip;
            this.port = port;
        }

        @Override
        protected Void doInBackground(Void... params) {

            // TODO: connect to server here
            // TODO: loop forever while reading messages from server and queuing them

            return null;
        }
    }



}
