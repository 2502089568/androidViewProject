package sj0202.s2.studyjams.cn.zhangzheng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

import static android.R.attr.id;
import static android.R.attr.name;


public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private  double  result ;
    private EditText et_input;
    private  String str;
    private  boolean clear_flag =true;//清空标识
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input = (EditText)findViewById(R.id.et_input);
        str =et_input.getText().toString();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(name));
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public void one(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"1");
    }

    public void two(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"2");
    }

    public void three(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"3");
    }

    public void four(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"4");
    }

    public void five(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"5");
    }

    public void six(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"6");
    }

    public void seven(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"7");
    }

    public void eight(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"8");
    }

    public void nine(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"9");
    }
    public void zero(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+"0");
    }

    public void point(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+".");
    }

    public void plus(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+" "+"+"+" ");
    }

    public void minus(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("0");
        }
        et_input.setText(str = str+" "+"-"+" ");
    }
    public void multiply(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+" "+"*"+" ");
    }
    public void divide(View view){
        if(clear_flag){
            str="";
            clear_flag =false;
            et_input.setText("");
        }
        et_input.setText(str = str+" "+"/"+" ");
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
            et_input.setText(str = str.substring(0,str.length()-1));
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

        String s1 = exp.substring(0,exp.indexOf(" "));//运算符前面的字符
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//运算符
        String s2 = exp.substring(exp.indexOf(" ")+3);// 运算符后面的字符
        if(!s1.equals("")&&!s2.equals("")){
            result =0;
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            et_input.setText(op);
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
            double d2 = Double.parseDouble(s2);
            if(op.equals("+")){
                result = result+d2;
            }
            if(op.equals("-")){
                result = result-d2;
            }
            if(op.equals("*")){
                result = result*d2;
            }
            if(op.equals("/")) {
                result = result/d2;
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

