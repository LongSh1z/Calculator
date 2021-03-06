package longsh1z.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_add;
    private Button btn_minus;
    private Button btn_multiply;
    private Button btn_divide;
    private Button btn_equal;
    private Button btn_clear;
    private Button btn_point;
    private Button btn_del;
    private EditText et_input;
    private boolean clear_flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        setAllOnClicklistener();
    }

    private void setAllOnClicklistener() {
        btn_0.setOnClickListener(MainActivity.this);
        btn_1.setOnClickListener(MainActivity.this);
        btn_2.setOnClickListener(MainActivity.this);
        btn_3.setOnClickListener(MainActivity.this);
        btn_4.setOnClickListener(MainActivity.this);
        btn_5.setOnClickListener(MainActivity.this);
        btn_6.setOnClickListener(MainActivity.this);
        btn_7.setOnClickListener(MainActivity.this);
        btn_8.setOnClickListener(MainActivity.this);
        btn_9.setOnClickListener(MainActivity.this);
        btn_add.setOnClickListener(MainActivity.this);
        btn_minus.setOnClickListener(MainActivity.this);
        btn_multiply.setOnClickListener(MainActivity.this);
        btn_divide.setOnClickListener(MainActivity.this);
        btn_clear.setOnClickListener(MainActivity.this);
        btn_del.setOnClickListener(MainActivity.this);
        btn_point.setOnClickListener(MainActivity.this);
        btn_equal.setOnClickListener(MainActivity.this);
    }

    private void findAllViews() {
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        et_input = (EditText) findViewById(R.id.et_input);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText(" ");
                    str = " ";
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText(" ");
                    str = " ";
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_del:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText("");
                    str = " ";
                }else if (str != null && !str.equals(" ")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_clear:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText(" ");
                    str = " ";
                }
                et_input.setText(" ");
                break;
            case R.id.btn_equal:
                getResult();
                break;
            default:
                break;
        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals(" "))
            return;
        if (!exp.contains(" "))
            return;
        if (clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result = 0;
        String s1 = exp.substring(0,exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        String s2 = exp.substring(exp.indexOf(" ")+3);
        if (!s1.equals(" ") && !s2.equals(" ")){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")){
                result = d1 + d2;
            }else if (op.equals("-")){
                result = d1 - d2;
            }else if (op.equals("*")){
                result = d1 * d2;
            }else if (op.equals("/")){
                if (d2 == 0)
                    result = 0;
                else
                    result = d1 / d2;
            }
            if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")){
                int r = (int) result;
                et_input.setText(r+" ");
            }else{
                et_input.setText(result+" ");
            }
        }else if (!s1.equals(" ") && s2.equals(" ")){
            et_input.setText(exp);
        }else if (s1.equals(" ") && !s2.equals(" ")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")){
                result = 0 + d2;
            }else if (op.equals("-")){
                result = 0 - d2;
            }else if (op.equals("*")){
                result = 0;
            }else if (op.equals("/")){
                result = 0;
            }
            if (!s2.contains(".") && !op.equals("/")){
                int r = (int) result;
                et_input.setText(r+" ");
            }else{
                et_input.setText(result+"");
            }
        }else{
            et_input.setText(" ");
        }
    }
}
