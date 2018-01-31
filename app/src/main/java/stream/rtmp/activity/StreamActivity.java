package stream.rtmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import stream.rtmp.R;

public class StreamActivity extends AppCompatActivity {

    public static final String ADDRESS = "stream.rtmp.activity.Address";
    private PowerManager.WakeLock streamWakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming);

        Intent intent = getIntent();
        String address = intent.getStringExtra(ADDRESS);
        startStreamAtAddress(address);

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        streamWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "streamWakeLock");
        streamWakeLock.acquire();
    }

    private void startStreamAtAddress (final String address) {
        final TextView lblStreamAddress = findViewById(R.id.lblStreamAddress);
        lblStreamAddress.setText(address);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (streamWakeLock == null) {
            System.out.println("Stream Wake Lock NULL");
        } else {
            System.out.println("Releasing Stream Wake Lock");
            streamWakeLock.release();
        }
    }
}
