package com.example.juserzhang.gridview.Calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juserzhang.gridview.R;


public class CalcActivity extends AppCompatActivity implements OnClickListener{

    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;
    private Button bt_8;
    private Button bt_9;
    private Button bt_0;
    private Button bt_add;
    private Button bt_sub; // 减
    private Button bt_multiply; // 乘
    private Button bt_divide; // 除
    private Button bt_back;
    private Button bt_equal; // 等于
    private Button bt_point; // 点
    private Button bt_clear; // 清除
    private TextView  et_play; // 显示
    private String str_oper = "+"; // 运算符
    private StringBuffer str_display = new StringBuffer();// 显示
    private String str_result; // 结果显示
    private double num1;
    private double num2;
    private boolean flag = true; // 小数点个数开关控制；

    private boolean b_sub, b_mul, b_div; // 运算符开关控制

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        initFindViewById();
        //初始化显示
        et_play.setText("0.0");

        initSetOnClickListener();

    }


    /*
    * 实例控件
    * */
    private void initFindViewById() {
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_sub = (Button) findViewById(R.id.bt_sub);
        bt_multiply = (Button) findViewById(R.id.bt_multiply);
        bt_divide = (Button) findViewById(R.id.bt_divide);
        bt_back = (Button) findViewById(R.id.bt_back);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        bt_point = (Button) findViewById(R.id.bt_point);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        et_play = (TextView) findViewById(R.id.et);
    }

    /*
    * 监听控件点击事件
    * */
    private void initSetOnClickListener() {

        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_0:
                str_display.append("0");
                et_play.setText(str_display.toString());
                break;

            case R.id.bt_1:
                str_display.append("1");
                et_play.setText(str_display.toString());
                break;
            case R.id.bt_2:
                str_display.append("2");
                et_play.setText(str_display.toString());

                break;
            case R.id.bt_3:
                str_display.append("3");
                et_play.setText(str_display.toString());
                break;
            case R.id.bt_4:
                str_display.append("4");
                et_play.setText(str_display.toString());

                break;
            case R.id.bt_5:
                str_display.append("5");
                et_play.setText(str_display.toString());
                break;
            case R.id.bt_6:
                str_display.append("6");
                et_play.setText(str_display.toString());

                break;
            case R.id.bt_7:
                str_display.append("7");
                et_play.setText(str_display.toString());
                break;
            case R.id.bt_8:
                str_display.append("8");
                et_play.setText(str_display.toString());

                break;
            case R.id.bt_9:
                str_display.append("9");
                et_play.setText(str_display.toString());
                break;
            case R.id.bt_point:
                if (flag) {
                    str_display.append(".");
                    flag = false;
                }
                break;
            case R.id.bt_add:
               try {
                   funAdd();
               }catch (Exception e) {
                   return;
               }
                break;
            case R.id.bt_sub:
                try {
                    funSub();
                }catch (Exception e){
                 return;
                }
                break;
            case R.id.bt_multiply:
               try {
                   funMultiply();
               }catch (Exception e){
                   return;
               }
                break;
            case R.id.bt_divide:
                try {
                    funDivide();
                }catch (Exception e) {
                    return;
                }
                break;
            case R.id.bt_back:
                funBack();
                break;
            case R.id.bt_equal:
                try {
                    funEqual();
                }catch (Exception e){
                    return;
                }
                break;

            case R.id.bt_clear:
                funClear();
                break;
        }
    }

   /*
   * 清屏操作
   * */
    private void funClear() {
        str_display = new StringBuffer("");
        str_result = null;
        num1 = 0;
        num2 = 0;
        flag = true;
        b_sub = false;
        b_mul = false;
        b_div = false;
        et_play.setText("0.0");
        return;
    }

    /*
    * 退格操作
    * */
    private void funBack() {

        if (str_display.length() != 0) {
            str_display.deleteCharAt(str_display.length() - 1);
            et_play.setText(str_display.toString());
        }
    }

    /*
    * 求运算结果
    * */
    private void funEqual() {
        if (str_oper.equals("+")) {
            num2 = Double.parseDouble(str_display.toString());
            str_result = String.valueOf((num1 + num2));
            et_play.setText(str_result);
            str_display = new StringBuffer("");
        }

        if (str_oper.equals("-")) {
            num2 = Double.parseDouble(str_display.toString());
            str_result = String.valueOf((num1 - num2));
            et_play.setText(str_result);
            str_display = new StringBuffer("");
        }

        if (str_oper.equals("*")) {
            num2 = Double.parseDouble(str_display.toString());
            str_result = String.valueOf((num1 * num2));
            et_play.setText(str_result);
            str_display = new StringBuffer("");
        }

        if (str_oper.equals("/")) {
            num2 = Double.parseDouble(str_display.toString());
            if (!(num2 == 0)) {
                str_result = String.valueOf((num1 / num2));
                et_play.setText(str_result);
            } else {
                Toast.makeText(CalcActivity.this,
                        "除数不能为0！", Toast.LENGTH_LONG).show();
            }
            str_display = new StringBuffer("");
        }
    }

    /*
    * 减法运算
    * */
    private void funSub() {
        str_oper = "-";

        if (!b_sub && !(str_display.toString() == "")) {
            num1 = Double.parseDouble(str_display.toString());
            et_play.setText(String.valueOf(num1));
            str_display = new StringBuffer("");
            b_sub = true;
        } else {
            if (!(str_display.toString() == "")) {
                num1 -= Double.parseDouble(str_display.toString());
                str_display = new StringBuffer("");
            }
            if (!(str_result == null)) {
                num1 = Double.parseDouble(str_result);
                str_result = null;
            }
            et_play.setText(String.valueOf(num1));
        }

        flag = true;
    }

    /*
    * 除法运算
    * */
    private void funDivide() {
        str_oper = "/";

        if (!b_div && !(str_display.toString() == "")) {
            num1 = Double.parseDouble(str_display.toString());
            et_play.setText(String.valueOf(num1));
            str_display = new StringBuffer("");
            b_div = true;
        } else {
            if (!(str_display.toString() == "")) {
                if (Double.parseDouble(str_display.toString()) == 0) {
                    Toast.makeText(CalcActivity.this,
                            "除数不能为0！", Toast.LENGTH_LONG).show();
                } else {
                    num1 /= Double.parseDouble(str_display.toString());
                    str_display = new StringBuffer("");
                }
            }
            if (!(str_result == null)) {
                num1 = Double.parseDouble(str_result);
                str_result = null;
            }
            et_play.setText(String.valueOf(num1));
        }
        flag = true;
    }

   /*
   * 乘法运算
   * */
    private void funMultiply() {
        str_oper = "*";

        if (!b_mul && !(str_display.toString() == "")) {
            num1 = Double.parseDouble(str_display.toString());
            et_play.setText(String.valueOf(num1));
            str_display = new StringBuffer("");
            b_mul = true;
        } else {
            if (!(str_display.toString() == "")) {
                num1 *= Double.parseDouble(str_display.toString());
                str_display = new StringBuffer("");
            }
            if (!(str_result == null)) {
                num1 = Double.parseDouble(str_result);
                str_result = null;
            }
            et_play.setText(String.valueOf(num1));
        }

        flag = true;
    }

    /*
    * 加法运算
    * */
    private void funAdd() {
        str_oper = "+";
        if (!(str_display.toString() == "")) {
            num1 += Double.parseDouble(str_display.toString());
            str_display = new StringBuffer("");
        }
        if (!(str_result == null)) {
            num1 = Double.parseDouble(str_result);
            str_result = null;
        }
        et_play.setText(String.valueOf(num1));

        flag = true;
    }
}
