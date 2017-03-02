package personal.development.gilho.timerstuff;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView repeat;
    private Handler mHandler;
    private Thread backgroundThread;
    private String uiString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(Bundle) called by Gil ");

        repeat = (TextView)findViewById(R.id.view_repeat);
        runInBackground();
        backgroundThread.start();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                Bundle bundle = message.getData();
                uiString = bundle.getString("count");
                repeat.setText("Repeat: " + uiString);
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called by Gil ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called by Gil ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called by Gil ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called by Gil ");
    }

    public void runInBackground() {
        Log.d(TAG, "runInBackGround() called by Gil");

        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d(TAG, "Background Thread started by Gil");
                Bundle bundle = new Bundle();
                Message message = new Message();

                for (int i = 1; i < 11; i++) {

                    bundle.putString("count", ""+i);
                    message.setData(bundle);
                    mHandler.sendMessage(message);

                    try {
                        // delay for 30 seconds
                        Thread.sleep(3000);
                    } catch (Throwable t) {
                        Log.d(TAG, "Throwable Error caused by Thread.Sleep() & Gil");
                    }

                }

            }
        });

    }




}
