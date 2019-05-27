package com.example.socket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Runnable {

    private TextView tv_msg = null;
    private EditText ed_msg = null;
    private Button btn_send = null;
    private static final String HOST = "192.168.43.207";//服务器地址
    private static final int PORT = 5005;//连接端口号
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    //接收线程发送过来信息，并用TextView追加显示
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_msg.append((CharSequence) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_msg = (TextView) findViewById(R.id.txt_1);
        ed_msg = (EditText) findViewById(R.id.et_talk);
        btn_send = (Button) findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = ed_msg.getText().toString();
                if (socket.isConnected()) {//如果服务器连接
                    if (!socket.isOutputShutdown()) {//如果输出流没有断开
                        out.println(msg);//点击按钮发送消息
                        ed_msg.setText("");//清空编辑框
                    }
                }
            }
        });
        new Thread(this).start();

    }

    private void connection() {
        try {
            socket = new Socket(HOST, PORT);//连接服务器
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));//接收消息的流对象
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);//发送消息的流对象
        } catch (IOException ex) {
            ex.printStackTrace();
            ShowDialog("连接服务器失败：" + ex.getMessage());
        }
    }

    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("通知").setMessage(msg).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }


    public void run() {
        connection();// 连接到服务器
        try {
            while (true) {//死循环守护，监控服务器发来的消息
                if (!socket.isClosed()) {//如果服务器没有关闭
                    if (socket.isConnected()) {//连接正常
                        if (!socket.isInputShutdown()) {//如果输入流没有断开
                            String getLine;
                            if ((getLine = in.readLine()) != null) {//读取接收的信息
                                getLine += "\n";
                                Message message = new Message();
                                message.obj = getLine;
                                mHandler.sendMessage(message);//通知UI更新
                            } else {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
