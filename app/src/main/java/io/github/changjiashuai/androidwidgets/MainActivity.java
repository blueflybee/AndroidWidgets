package io.github.changjiashuai.androidwidgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.github.changjiashuai.widgets.bottomsheet.BottomSheetLayout;

public class MainActivity extends AppCompatActivity {

    private BottomSheetLayout bottomSheetLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View view = getLayoutInflater().inflate(R.layout.activity_loading, null);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bottomSheetLayout.showWithSheetView(view);
                Intent intent = new Intent(MainActivity.this, WebViewDemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
