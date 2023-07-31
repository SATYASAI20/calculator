package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonDivide, buttonMultiply,buttonSub,buttonAdd,buttonEquals,buttonAC;
    MaterialButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution);
        assignId(buttonAC,R.id.button_clear);
        assignId(buttonOpenBracket, R.id.button_open_brackets);
        assignId(buttonCloseBracket,R.id.button_close_brackets);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multi);
        assignId(buttonSub, R.id.button_sub);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonC,R.id.button_C);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonEquals,R.id.button_equals);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C") && solutionTv.getText().toString().length()<=1 && !buttonText.equals("0")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("C") && solutionTv.length() >=1 ){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1 );
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult =  getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult= finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }

    }
}