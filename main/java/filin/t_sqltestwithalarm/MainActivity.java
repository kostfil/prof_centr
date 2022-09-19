package filin.t_sqltestwithalarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button testBtn;
    Button alarmBtn;
    Button aboutBtn;
   // String str="Тест взят из курса \"Разработка БД MS SQL Service\" ВМК МГУ.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        setContentView(R.layout.activity_main);

        testBtn = (Button) findViewById(R.id.testBtn);
        alarmBtn = (Button) findViewById(R.id.alarmBtn);
  //      aboutBtn = (Button) findViewById(R.id.aboutBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TestActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AlarmActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

//        aboutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setMessage(str);
//                builder.setCancelable(true);
//
//                final AlertDialog dlg = builder.create();
//                dlg.show();
//                final Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    public void run() {
//                        dlg.dismiss(); // when the task active then close the dialog
//                        timer.cancel(); // also just top the timer thread, otherwise,
//                    }
//                }, 5000); // через 5 секунд,the task will be active.
//            }
//        });
    }
}