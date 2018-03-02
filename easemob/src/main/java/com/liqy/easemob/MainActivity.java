package com.liqy.easemob;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册失败会抛出HyphenateException

        button=(Button)findViewById(R.id.btn_send_msg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage("你好，1510c", "1510c");
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        });

        new AsyncTask<String,Integer,String>(){
            @Override
            protected String doInBackground(String... strings) {
                try {
                    EMClient.getInstance().createAccount("1510Ca", "1510Ca");//同步方法
                }catch (HyphenateException exception){

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                EMClient.getInstance().login("1510Ca","1510Ca",new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                    }
                });

            }
        }.execute("");



    }
}
