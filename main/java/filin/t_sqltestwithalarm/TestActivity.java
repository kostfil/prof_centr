package filin.t_sqltestwithalarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestActivity extends AppCompatActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, TestActivity.class);
        return intent;
    }


    private static final String TAG = "TestActivity";
    // Объявим переменные компонентов
    private EditText editTextTop;
    private boolean[] checkBoxValue = new boolean[6];
    private CheckBox mCheckBoxA;
    private CheckBox mCheckBoxB;
    private CheckBox mCheckBoxC;
    private CheckBox mCheckBoxD;
    private CheckBox mCheckBoxE;
    private CheckBox mCheckBoxF;

    private Button nextBtn;
    private Button answerBtn;
    private Button resultBtn;

    private List<QuestQuestion> questionList;
    private QuestQuestion currentQuestQuestion;

    private int mCurrentIndex ;
    private int NumberTrueAnswer;
    private boolean answerShow = true;
    private static final String KEY_INDEX = "index";
    private static final String RESULT_INDEX = "result";
    private static final String KEY_ANSWER_SHOW = "answerShow";


    // Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        // Найдем компоненты в XML разметке
        editTextTop = (EditText) findViewById(R.id.editTextTop);
        nextBtn = (Button) findViewById(R.id.NextBtn);
        answerBtn = (Button) findViewById(R.id.answerBtn);
        resultBtn = (Button) findViewById(R.id.resultBtn);

        mCheckBoxA = (CheckBox) findViewById(R.id.checkBoxA);
        mCheckBoxB = (CheckBox) findViewById(R.id.checkBoxB);
        mCheckBoxC = (CheckBox) findViewById(R.id.checkBoxC);
        mCheckBoxD = (CheckBox) findViewById(R.id.checkBoxD);
        mCheckBoxE = (CheckBox) findViewById(R.id.checkBoxE);
        mCheckBoxF = (CheckBox) findViewById(R.id.checkBoxF);


        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        questionList = getListFormDB();
//        Log.d(TAG,"onCreate: Read DB");


        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            NumberTrueAnswer = savedInstanceState.getInt(RESULT_INDEX, 0);
            answerShow = savedInstanceState.getBoolean(KEY_ANSWER_SHOW, true);
//            Log.d(TAG,"savedInstanceState : mCurrentIndex = " + mCurrentIndex);
//            Log.d(TAG,"savedInstanceState : NumberTrueAnswer = " + NumberTrueAnswer);
        }



        currentQuestQuestion = questionList.get(mCurrentIndex);
        showView(currentQuestQuestion);
        setCheckBoxValue(answerShow);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerShow = true;
                setCheckBoxValue(answerShow);
                if(isTrueAnswer()){
                    ++NumberTrueAnswer;
                }

                if(++mCurrentIndex < questionList.size()){
                    currentQuestQuestion = questionList.get(mCurrentIndex);
                    showView(currentQuestQuestion);

                }else{
                    answerShow = false;
                    setCheckBoxValue(answerShow);
                }
            }
        });

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // отключить checkBox
                answerShow = false;
                setCheckBoxValue(answerShow);
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                builder.setTitle("Правильный ответ");
                builder.setMessage(currentQuestQuestion.getTrueAnswer());
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dlg = builder.create();
                dlg.show();
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Кол-во верных ответов :");
                builder.setMessage(String.valueOf(NumberTrueAnswer));
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();
                dlg.show();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        timer.cancel(); // also just top the timer thread, otherwise,
                    }
                }, 3000); // через 3 секунд,the task will be active.
            }
        });
    }


    private void setCheckBoxValue(boolean value){
        mCheckBoxA.setEnabled(value);
        mCheckBoxB.setEnabled(value);
        mCheckBoxC.setEnabled(value);
        mCheckBoxD.setEnabled(value);
        mCheckBoxE.setEnabled(value);
        mCheckBoxF.setEnabled(value);
    }


    private boolean isTrueAnswer(){
        boolean result= false;
        if(mCheckBoxA.isChecked() == checkBoxValue[0] &&
                mCheckBoxB.isChecked() == checkBoxValue[1] &&
                mCheckBoxC.isChecked() == checkBoxValue[2] &&
                mCheckBoxD.isChecked() == checkBoxValue[3] &&
                mCheckBoxE.isChecked() == checkBoxValue[4] &&
                mCheckBoxF.isChecked() == checkBoxValue[5]
        ) {
            result = true;
        }
        return result;
    }

    private void showView(QuestQuestion currentQuestQuestion){
        for(int i = 0 ; i< checkBoxValue.length; ++i){
            checkBoxValue[i] = false;
        }
        String strQ = currentQuestQuestion.getQuestion();
        String vstrQ = strQ.replaceFirst("\\.", ":\n");
        editTextTop.setText( vstrQ );

        mCheckBoxA.setChecked(false);
        mCheckBoxA.setText(currentQuestQuestion.getAnswer().get(0).substring(3));
        checkBoxValue[0] = (currentQuestQuestion.getAnswer().get(0).substring(0,3).contains("*"));

        mCheckBoxB.setChecked(false);
        mCheckBoxB.setText(currentQuestQuestion.getAnswer().get(1).substring(3));
        checkBoxValue[1] = (currentQuestQuestion.getAnswer().get(1).substring(0,3).contains("*"));

        mCheckBoxC.setChecked(false);
        mCheckBoxC.setText(currentQuestQuestion.getAnswer().get(2).substring(3));
        checkBoxValue[2] = (currentQuestQuestion.getAnswer().get(2).substring(0,3).contains("*"));

        mCheckBoxD.setChecked(false);
        mCheckBoxD.setText(currentQuestQuestion.getAnswer().get(3).substring(3));
        checkBoxValue[3] = (currentQuestQuestion.getAnswer().get(3).substring(0,3).contains("*"));

        mCheckBoxE.setChecked(false);
        mCheckBoxE.setText(currentQuestQuestion.getAnswer().get(4).substring(3));
        checkBoxValue[4] = (currentQuestQuestion.getAnswer().get(4).substring(0,3).contains("*"));

        mCheckBoxF.setChecked(false);
        mCheckBoxF.setText(currentQuestQuestion.getAnswer().get(5).substring(3));
        checkBoxValue[5] = (currentQuestQuestion.getAnswer().get(5).substring(0,3).contains("*"));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(mCurrentIndex >= questionList.size()){
            mCurrentIndex = questionList.size() - 1;
        }
//        Log.i(TAG, "onSaveInstanceState : mCurrentIndex = " + mCurrentIndex + " , NumberTrueAnswer = " + NumberTrueAnswer);
//        Log.i(TAG, "onSaveInstanceState : answerShow= " + answerShow);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(RESULT_INDEX, NumberTrueAnswer);
        savedInstanceState.putBoolean(KEY_ANSWER_SHOW, answerShow);
    }

    private List<QuestQuestion> getListFormDB() {

        List<QuestQuestion> questionList = new ArrayList<>();
        int id_q;
        String textQuestion;
        String texttrueAnswer;

        Cursor cursorQuest = mDb.rawQuery("SELECT * FROM Questions", null);
        Cursor cursorAnswer = null;

        cursorQuest.moveToFirst();
        while (!cursorQuest.isAfterLast()) {
            id_q = cursorQuest.getInt(0);
            textQuestion = cursorQuest.getString(1);
            texttrueAnswer = cursorQuest.getString(2);

            cursorAnswer = mDb.rawQuery("SELECT * from Answers a where id_q = ?", new String[]{String.valueOf(id_q)});
            cursorAnswer.moveToFirst();
            ArrayList<String> answers = new ArrayList<>();
            while (!cursorAnswer.isAfterLast()) {
                String textAnswer = cursorAnswer.getString(3);
                answers.add(textAnswer);
                cursorAnswer.moveToNext();
            }

            QuestQuestion questQuestionCur = new QuestQuestion(textQuestion, answers, texttrueAnswer);
            questionList.add(questQuestionCur);
            cursorQuest.moveToNext();
        }
        cursorQuest.close();
        if(cursorAnswer != null)
            cursorAnswer.close();

        if(questionList.isEmpty()){
            return Collections.emptyList();
        }

        return questionList;
    }
}