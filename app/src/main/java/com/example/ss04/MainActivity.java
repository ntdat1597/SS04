package com.example.ss04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnchildItemClick  {
    private List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        mAdapter = new ContactAdapter(this,listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                ContactModel model = listContact.get(i);
                Toast.makeText(MainActivity.this,model.getName()+": " + model.getPhone(),Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void initView() {
        lvContact = (ListView) findViewById(R.id.lvContact);
        ivUser = ( ImageView) findViewById(R.id.ivUser);
        tvName = (TextView) findViewById(R.id.tvName);
    }

    private void initData() {
        listContact.add(new ContactModel("Nguyễn Tiến Đạt", "012412112", R.drawable.user1));
        listContact.add(new  ContactModel("Nguyễn Xuân Tùng","012423432",R.drawable.user3));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user4));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user1));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));
        listContact.add(new  ContactModel("Nguyễn Tiến Đạt","012423432",R.drawable.user2));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contact = listContact.get(position);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }
}