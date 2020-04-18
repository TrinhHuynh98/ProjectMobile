package com.example.learnenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnenglish.Adapter.CustomAdapter;
import com.example.learnenglish.Data.DBManager;
import com.example.learnenglish.model.Vocabulary;

import java.util.List;

public class MainActivity extends AppCompatActivity {
// bat su kien cho cac thuoc tinh cua view
    private EditText edtName;
    private EditText edtDef;
    private EditText edtDes;
    private EditText edtEx;
    private EditText edtSyn;
    private Button btnSave;
    private EditText edtID;
    private Button btnUpdate;
    private ListView lvVocabulary;
    private CustomAdapter customAdapter;
   private List<Vocabulary> vocabularies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBManager db = new DBManager(this);
        initWidget();

        vocabularies = db.getAllVocabulary();
        setAdapter();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vocabulary vocabulary = createVocabulary();
                if(vocabulary !=null){

                    db.addVocabulary(vocabulary);
                }
//                create xong auto cap nhat tren listview
                vocabularies.clear();
                vocabularies.addAll(db.getAllVocabulary());
                setAdapter();
            }
        });
//        Liet ke danh sach cac vocabulary
        lvVocabulary.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long l) {
                Vocabulary vocabulary = vocabularies.get(position);
                edtID.setText(String.valueOf(vocabulary.getmID()));
                edtName.setText(vocabulary.getmName());
                edtDef.setText(vocabulary.getmDefine());
                edtDes.setText(vocabulary.getmDescription());
                edtEx.setText(vocabulary.getmExample());
                edtSyn.setText(vocabulary.getmSyn());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);

            }
        });
//        Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.setmID(Integer.parseInt(String.valueOf(edtID.getText())));
            vocabulary.setmName(String.valueOf(edtName.getText()));
            vocabulary.setmDefine(String.valueOf(edtDef.getText()));
            vocabulary.setmDescription(String.valueOf(edtDes.getText()));
            vocabulary.setmExample(String.valueOf(edtEx.getText()));
            vocabulary.setmSyn(String.valueOf(edtSyn.getText()));

            int count = db.updateVocabulary(vocabulary);

            if(count>0){

                vocabularies.clear();
                vocabularies.addAll(db.getAllVocabulary());
                customAdapter.notifyDataSetChanged();
            }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });
//        bat su kien delete
        lvVocabulary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> AdapterView, View view, int position, long l) {
                Vocabulary vocabulary = vocabularies.get(position);
                int count  = db.deleteVocabulary(vocabulary.getmID());
                if(count>0){
                    Toast.makeText(MainActivity.this,"Delete successfully", Toast.LENGTH_LONG).show();
                    vocabularies.clear();
                    vocabularies.addAll(db.getAllVocabulary());
                    customAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this,"Delete failed", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

    }

    private Vocabulary createVocabulary(){
        String name = edtName.getText().toString();
        String define = String.valueOf(edtDef.getText());
        String description = edtDes.getText()+"";
//                3 cach kieu text sang string
        String example = edtEx.getText().toString();
        String synonymous = edtSyn.getText().toString();
        Vocabulary vocabulary =new Vocabulary(name, define, description, example, synonymous);
        return vocabulary;
    }
    private void initWidget(){
        edtName = (EditText) findViewById(R.id.edt_name);
        edtDef = (EditText) findViewById(R.id.edt_def);
        edtDes = (EditText) findViewById(R.id.edt_des);
        edtEx = (EditText) findViewById(R.id.edt_ex);
        edtSyn = (EditText) findViewById(R.id.edt_syn);
        btnSave = (Button) findViewById(R.id.btn_save);
        lvVocabulary = (ListView) findViewById(R.id.lv_voca);
        edtID = (EditText)findViewById(R.id.edt_id);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }

    public void setAdapter(){
        if(customAdapter==null){
            customAdapter = new CustomAdapter(this, R.layout.item_list_vocabulary, vocabularies);
            lvVocabulary.setAdapter(customAdapter);
        }else {
            //        tu dong cap nhat khi tao moi data
            customAdapter.notifyDataSetChanged();
//            hien thi vi tri cuoi cung cua voca vua moi them
            lvVocabulary.setSelection(customAdapter.getCount()-1);
        }

    }


}
