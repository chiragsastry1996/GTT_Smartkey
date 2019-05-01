package com.volvo.gtt_smartkey.socketTest;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.Utils.ChatApplication;

import org.json.JSONException;
import org.json.JSONObject;


public class SocketTest extends WearableActivity {

    private Socket mSocket;
    TextView message_out;
    String light_status, light_command = "ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);

        message_out = (TextView)findViewById(R.id.message_output);

        ChatApplication app = (ChatApplication) getApplication();
        mSocket = app.getSocket();
        mSocket.connect();

        Log.v("AvisActivity", "try to connect");
        mSocket.connect();

        mSocket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Transport transport = (Transport) args[0];
                transport.on(Transport.EVENT_ERROR, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Exception e = (Exception) args[0];
                        Log.e("AvisActivity", "Transport error " + e);
                        e.printStackTrace();
                        e.getCause().printStackTrace();
                    }
                });
            }
        });

        mSocket.emit("light", light_command);

        message_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.emit("light", light_command);
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mSocket.on("light", onNewMessage);
//        mSocket.emit("add user", "Chirag");
//        mSocket.emit("connection", "Chirag");
        mSocket.emit("new message", "Smartwatch connected");

    }

//    private Emitter.Listener onNewMessage = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    String message;
//                    try {
//                        username = data.getString("username");
//                        message = data.getString("message");
//                        message_out.setText(username + ": " + message);
//                    } catch (JSONException e) {
//                        Log.e("SocketTest", e.getMessage());
//                        return;
//                    }
//
//
//                }
//            });
//        }
//    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        light_status = data.getString("data");

                        if(light_status.equals("ON")) {
                            message_out.setText("OFF the lamp");
                            light_command = "OFF";
                        }
                        if(light_status.equals("OFF")){
                            message_out.setText("ON the lamp");
                            light_command = "ON";
                        }

                    } catch (JSONException e) {
                        Log.e("SocketTest", e.getMessage());
                        return;
                    }


                }
            });
        }
    };

}
