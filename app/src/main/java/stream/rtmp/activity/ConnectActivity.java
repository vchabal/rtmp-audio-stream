package stream.rtmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stream.rtmp.R;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ConnectActivity.this.doConnection();
            }
        });
    }

    private void doConnection () {
        final EditText txtAddress = findViewById(R.id.txtAddress);
        final String address = txtAddress.getText().toString();
        if (!isAddressValid(address)) {
            txtAddress.setError("Required format rtmp://address.com/uri/location");
            return;
        }

        Intent intent = new Intent(this, StreamActivity.class);
        intent.putExtra(StreamActivity.ADDRESS, address);
        startActivity(intent);
    }
    private boolean isAddressValid (final String address) {
        final String rtmpRegExp = "rtmp:\\/\\/.*\\/.*";
        return address != null && address.matches(rtmpRegExp);
    }
}