package com.notification.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zenglb.framework.updateinstaller.R;

public class NotificationiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationi);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerView=findViewById(R.id.dialogRecycler);
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] strings= initData();

        SimpleAdapter simpleAdapter=new SimpleAdapter(strings);
        recyclerView.setAdapter(simpleAdapter);
    }

    private String[] initData() {

        String[] strings=new String[]{
                "普通通知",
                "多行文本Notification",
                "跳转到指定的activity",
                "注册",
                "增加回复Action",
                "增加带进度条notifacation",
                "免打扰",
                "重要通知,锁屏通知",
                "设置通知的可见性，隐私，公开，私密。可以设置替代通知（）",
        };

        return strings;

    };


    public class  SimpleAdapter extends  RecyclerView.Adapter<SimpleAdapter.ViewHolder>{

        String[] strings;
        public SimpleAdapter(String[] strings) {
            this.strings=strings;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(NotificationiActivity.this).inflate(R.layout.activity_notificatioin,null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(strings[position]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(NotificationiActivity.this, position+"",1000).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return strings.length;
        }

        public  class  ViewHolder extends RecyclerView.ViewHolder{

            public TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.update_mess_txt);
            }
        }
    }
}
