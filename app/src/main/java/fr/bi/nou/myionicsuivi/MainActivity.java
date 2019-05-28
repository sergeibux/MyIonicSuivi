package fr.bi.nou.myionicsuivi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connection and fetching datas
        GetByWifi getByWifi = new GetByWifi(this);
        getByWifi.start();

        // listener on click on refresh
        cmd_refresh((ImageButton)(findViewById(R.id.btn_refresh)), getByWifi);
    }

    protected void cmd_refresh(ImageButton btn_refresh, final GetByWifi getByWifi){
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getByWifi.start();
            }
        });
    }

}
