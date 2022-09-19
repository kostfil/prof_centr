package filin.sqlfragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class FragmentMain extends Fragment {

    Button alarmBtn;
    Button testBtn;

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frame_main, container, false);

        alarmBtn = (Button) v.findViewById(R.id.alarmBtn);
        testBtn = (Button) v.findViewById(R.id.testBtn);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment alarmFragment = new FragmentAlarm();
                ft.replace(R.id.fragment_container, alarmFragment);
                ft.commit();
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });

        return v;
    }
}
/*
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Button alarmBtn");
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();
                dlg.show();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        timer.cancel(); // also just top the timer thread, otherwise,
                    }
                }, 5000); // через 5 секунд,the task will be active.
            }
 */