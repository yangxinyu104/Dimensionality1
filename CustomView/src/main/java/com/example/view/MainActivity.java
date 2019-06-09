package com.example.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int n= 4;

        int a =ClimbStairs(n);
        Log.e("tag","如果有"+n+"个台阶，那么有"+a+"种走法");

        Log.e("tag","----------------------------------------");
        int sum = getSum(5);
        Log.e("tag","sum  : " + sum);

        Log.e("tag","----------------------------------------");
        int fibonacci = fibonacci(6);
        Log.e("tag","公众号：Java3y：" + fibonacci);

        Log.e("tag","----------------------------------------");


        int num = 6;

        Log.e("tag",num++ + ++num+"");
        Log.e("tag",num*2+"");
        Log.e("tag",--num + ++num+"");
        Log.e("tag",num*2+"");


    }

    public static int fibonacci(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            int fibonacci = fibonacci(n - 1);
            Log.e("tag","  fibonacci + " +fibonacci);
            int fibonacci1 = fibonacci(n - 2);
            Log.e("tag","  fibonacci1 + " +fibonacci1);
            int i =  fibonacci+fibonacci1 ;
            Log.e("tag","  i + " +i);
            return i;
        }
    }

    public int getSum(int n){
        if (n==1) {
            return 1;
        }else{
            return n*getSum(n-1);
        }
}


    public int ClimbStairs(int n) {
        int i=1;
        if(n<=0)
            return 0;
        if(n==1){
            Log.e("tag"," n2 : " + n);
            return i;
        }
        if(n==2){
            Log.e("tag"," n3 : " + n);
            i++;
            return i;
        }
        else
            Log.e("tag"," n4 : " + n);
        int i1 = ClimbStairs(n - 1) + ClimbStairs(n - 2);
        Log.e("tag"," aaa : " + i1);
        return i1;
    }

}
