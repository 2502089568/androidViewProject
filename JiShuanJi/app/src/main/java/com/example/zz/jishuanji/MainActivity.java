package com.example.zz.jishuanji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    EditText et_input = (EditText)findViewById(R.id.et_input);
    String str =et_input.getText().toString();
    boolean clear_flag;//清空标识

    public void one(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("1");
    }

    public void two(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("2");
    }

    public void three(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("3");
    }

    public void four(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("4");
    }

    public void five(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("5");
    }

    public void six(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("6");
    }

    public void seven(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("7");
    }

    public void eight(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("8");
    }

    public void nine(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText("9");
    }

    public void point(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str+".");
    }

    public void plus(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str+" "+"+"+" ");
    }

    public void minus(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str+" "+"-"+" ");
    }
    public void multiply(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str+" "+"*"+" ");
    }
    public void divide(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
    et_input.setText(str+" "+"/"+" ");
    }
    public void clear(View view){
            clear_flag =false;
            str="";
            et_input.setText("");
    }

    public void del(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }else if(str!=null&&!str.equals("")){
            et_input.setText(str.substring(0,str.length()-1));
        }
    }

    public void equal(View view){
            getResult();
    }
    private void getResult(){
        String exp = et_input.getText().toString();
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result =0;
        String s1 = exp.substring(0,exp.indexOf(" "));//运算符前面的字符
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" "));//运算符
        String s2 = exp.substring(exp.indexOf(" ")+3);// 运算符后面的字符
        if(!s1.equals("")&&!s2.equals("")){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if(op.equals("+")){
                result = d1+d2;
            }
            if(op.equals("-")){
                result = d1-d2;
            }
            if(op.equals("*")){
                result = d1*d2;
            }
            if(op.equals("/")) {
                if (d2 == 0) {
                    result = 0;
                } else {
                    result = d1 / d2;
                }
                if (!s1.contains(".") && !s2.contains(".")&&!op.equals("/")) {
                    int r = (int) result;
                    et_input.setText(r + "");
                } else {
                    et_input.setText(result + "");
                }
            } else if(!s1.equals("")&&s2.equals("")){
                    et_input.setText(exp);
                }
                else if(s1.equals("")&&!s2.equals("")){
                if(op.equals("+")){
                    result = 0+d2;
                }
                if(op.equals("-")){
                    result = 0-d2;
                }
                if(op.equals("*")){
                    result = 0*d2;
                }
                if(op.equals("/")) {
                    result = 0;
                }
                    if (!s1.contains(".") && !s2.contains(".")) {
                        int r = (int) result;
                        et_input.setText(r + "");
                    } else {
                        et_input.setText(result + "");
                    }
                }
            else {
                et_input.setText("");
            }
            }
        }
    }

}
