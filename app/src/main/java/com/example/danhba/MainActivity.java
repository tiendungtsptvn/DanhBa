package com.example.danhba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danhba.adapter.ContactAdapter;
import com.example.danhba.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private EditText edtName ;
    private EditText edtNumber ;
    private RadioButton rbtnMale ;
    private RadioButton rbtnFeMale ;
    private Button btnAdd ;
    private RecyclerView rcvContact ;
    private ContactAdapter adtContact ;
    private ArrayList<Contact> arrContact ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
        arrContact=new ArrayList<>() ;
        adtContact=new ContactAdapter(this,arrContact,this) ;
        rcvContact.setAdapter(adtContact);
        rcvContact.setLayoutManager(new LinearLayoutManager(this));

    }
    public void setWidgets(){
        edtName= (EditText) findViewById(R.id.edt_name);
        edtNumber= (EditText) findViewById(R.id.edt_number);
        rbtnMale= (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFeMale= (RadioButton) findViewById(R.id.rbtn_female);
        btnAdd= (Button) findViewById(R.id.btn_add);
        rcvContact= (RecyclerView) findViewById(R.id.rcvContact);
    }


    public void addct(View view) {
        if(view.getId()==R.id.btn_add){
            String name=edtName.getText().toString().trim() ;
            String number=edtNumber.getText().toString().trim() ;
            boolean isMale=true ;
            if(rbtnMale.isChecked()){
                isMale=true ;
            }
            else{
                isMale=false ;
            }
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){
                Toast.makeText(MainActivity.this, "Please insert name and num",
                        Toast.LENGTH_LONG).show();
            }
            else{
                Contact contact=new Contact(isMale,name,number) ;
                arrContact.add(contact) ;
            }
            adtContact.notifyDataSetChanged();
        }
    }

    public  void showDialog(){
        Dialog dialog = new Dialog(this) ;
        dialog.setContentView(R.layout.custom_dialog);
        Button btnCall= (Button) dialog.findViewById(R.id.btn_call);
        Button btnMess= (Button) dialog.findViewById(R.id.btn_mess);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();
                intentCall() ;
            }
        });
        btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mess", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void intentCall() {
        Intent intent=new Intent() ;
        intent.setAction(Intent.ACTION_CALL) ;
        intent.setData(Uri.parse("tel:"+"094484444")) ;
        startActivity(intent);
    }

    @Override
    public void onItemClick(Contact contact) {
        showDialog();
    }
}
