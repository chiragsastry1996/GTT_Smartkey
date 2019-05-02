package com.volvo.gtt_smartkey.SplashScreen;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.volvo.gtt_smartkey.Utils.ChatApplication;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;

public class SocketService extends Service {

    private static Socket mSocket;
    public static String headlight_status;
    public static String hazardlight_status;
    public static String engine_status;
    public static String hvac_status;
    public static String door_status;
    public static final int DOOR_UNLOCK = 0;
    public static final int DOOR_LOCK = 1;
    public static final int HEADLIGHT_ON = 2;
    public static final int HEADLIGHT_OFF = 3;
    public static final int HAZARDLIGHT_ON = 4;
    public static final int HAZARDLIGHT_OFF = 5;
    public static final int HVAC_SET = 6;
    public SocketService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        ChatApplication app = (ChatApplication) getApplication();
        mSocket = app.getSocket();
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

        mSocket.on("light", onHeadlightChange);
        mSocket.on("hzdLight", onHazardlightChange);
        mSocket.on("engine", OnEngineChange);
        mSocket.on("hvac", onHvacChange);
        mSocket.on("door", onDoorChange);


    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart(Intent intent, int startid) {

    }

    private Emitter.Listener onDoorChange = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            {
                JSONObject data = (JSONObject) args[0];
                try {
                    door_status = data.getString("data");
                    Intent door_intent = new Intent("door_status");
                    door_intent.putExtra("door_status", door_status);
                    sendBroadcast(door_intent);
                    Log.e("SocketTest",door_status);
                } catch (JSONException e) {
                    Log.e("SocketTest", e.getMessage());
                    return;
                }


            }
        }
    };

    private Emitter.Listener onHeadlightChange = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            {
                JSONObject data = (JSONObject) args[0];
                try {
                    headlight_status = data.getString("data");
                    Intent door_intent = new Intent("headlight_status");
                    door_intent.putExtra("headlight_status", headlight_status);
                    sendBroadcast(door_intent);
                    Log.e("SocketTest",headlight_status);
                } catch (JSONException e) {
                    Log.e("SocketTest", e.getMessage());
                    return;
                }


            }
        }
    };

    private Emitter.Listener onHazardlightChange = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            {
                JSONObject data = (JSONObject) args[0];
                try {
                    hazardlight_status = data.getString("data");
                    Intent door_intent = new Intent("hazardlight_status");
                    door_intent.putExtra("hazardlight_status", hazardlight_status);
                    sendBroadcast(door_intent);
                    Log.e("SocketTest",hazardlight_status);
                } catch (JSONException e) {
                    Log.e("SocketTest", e.getMessage());
                    return;
                }


            }
        }
    };

    private Emitter.Listener OnEngineChange = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            {
                JSONObject data = (JSONObject) args[0];
                try {
                    engine_status = data.getString("data");
                    Log.e("SocketTest",engine_status);
                } catch (JSONException e) {
                    Log.e("SocketTest", e.getMessage());
                    return;
                }


            }
        }
    };

    private Emitter.Listener onHvacChange = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            {
                JSONObject data = (JSONObject) args[0];
                try {
                    hvac_status = data.getString("data");
                    Intent door_intent = new Intent("door_status");
                    door_intent.putExtra("door_status", door_status);
                    sendBroadcast(door_intent);
                    Log.e("SocketTest",hvac_status);
                } catch (JSONException e) {
                    Log.e("SocketTest", e.getMessage());
                    return;
                }


            }
        }
    };

    public static void door_lock() {
        mSocket.emit("door", "LOCK");
        Log.e("SocketTest", "Running");
    }

    public static void door_unlock() {
        mSocket.emit("door", "UNLOCK");
        Log.e("SocketTest", "Running");
    }

        public static void headlight_on() {
            mSocket.emit("light", "ON");
            Log.e("SocketTest", "Running");
    }

    public static void headlight_off() {
        mSocket.emit("light", "OFF");
        Log.e("SocketTest", "Running");
    }

    public static void hazardlight_on() {
        mSocket.emit("hzdLight", "ON");
        Log.e("SocketTest", "Running");
    }

    public static void hazardlight_off() {
        mSocket.emit("hzdLight", "OFF");
        Log.e("SocketTest", "Running");
    }

    public static void hvac_set() {
        mSocket.emit("hvac", "26");
        Log.e("SocketTest", "Running");
    }

}
