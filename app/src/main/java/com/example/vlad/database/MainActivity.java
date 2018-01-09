package com.example.vlad.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btnCreate,btnRead,btnUpdate,btnDelete,btnSqlLite, btnOrmLite;
private EditText etID,etFName,etLName,etAge;
private RecyclerView rv;
private SQLLiteDBHelper sqlLiteDBHelper;
private SQLiteDatabase db;
private ContentValues cv;
private PersonAdapter personAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSqlLite = findViewById(R.id.btnsqlite);
        btnDelete  = findViewById(R.id.btnDelete);
        btnRead    = findViewById(R.id.btnRead);
        btnUpdate  = findViewById(R.id.btnUpdate);
        btnCreate  = findViewById(R.id.btnCreate);


        btnUpdate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        etID    = (EditText) findViewById(R.id.etId);
        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        etAge   = (EditText) findViewById(R.id.etAge);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        personAdapter = new PersonAdapter();
        personAdapter.setOnItemClickListener(new PersonAdapter.OnItemClickListener<Person>() {
            @Override
            public void onItemClick(int position, Person item) {
                showPerson(item);
            }
        });
        rv.setAdapter(personAdapter);

    }


    private void showPerson(Person item) {
        etID.setText(item.getId() + "");
        etFName.setText(item.getFirstName());
        etLName.setText(item.getLastName());
        etAge.setText(item.getAge() + "");
    }

    private void conectToDb()
    {
        if(sqlLiteDBHelper == null)
            sqlLiteDBHelper = new SQLLiteDBHelper(MainActivity.this);
        if(cv == null)
            cv = new ContentValues();
        db = sqlLiteDBHelper.getWritableDatabase();
    }
    private boolean isConnectToDB(){ return db !=null;}

@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                Person p = new Person();
                p.setFirstName(etFName.getText().toString());
                p.setLastName(etLName.getText().toString());
                p.setAge(Integer.parseInt(etAge.getText().toString()));
                create(p);
                personAdapter.updateList(read());
                clearPerson();
                break;
            case  R.id.btnRead:
                personAdapter.updateList(read());
                break;
            case R.id.btnUpdate:
                Person pUpdate = new Person();
                pUpdate.setId(Integer.parseInt(etID.getText().toString()));
                pUpdate.setFirstName(etFName.getText().toString());
                pUpdate.setLastName(etLName.getText().toString());
                pUpdate.setAge(Integer.parseInt(etAge.getText().toString()));
                update(pUpdate);
                personAdapter.updateList(read());
                clearPerson();
                break;
            case R.id.btnDelete:
                delete(Integer.parseInt(etID.getText().toString()));
                personAdapter.updateList(read());
                clearPerson();
                break;
        }
}

private void create(Person p){
        cv.put("FirstName", p.getFirstName());
        cv.put("LastName", p.getLastName());
        cv.put("Age", p.getAge());
        db.insert("mytable", null, cv);
}
private void update(Person p) {
    int Id = p.getId();
    cv.put("FirstName", p.getFirstName());
    cv.put("LastName", p.getLastName());
    cv.put("Age", p.getAge());
    db.update("mytable", cv, "Id =" + Id, null);
}
private void delete(int Id) {
    db.delete("mytable", "Id =" + Id, null);
}
private void clearPerson() {
    etID.setText("");
    etFName.setText("");
    etLName.setText("");
    etAge.setText("");
}
private ArrayList<Person> read() {
    ArrayList<Person> persons = new ArrayList<>();
    Cursor c = db.query("mytable", null, null, null, null, null, null);
    if(c.moveToFirst()){
        int idColIndex        = c.getColumnIndex("id");
        int firstNameColIndex = c.getColumnIndex("firstname");
        int lastNameColIndex  = c.getColumnIndex("lastname");
        int ageColIndex       = c.getColumnIndex("age");

        do {
            Person p = new Person(c.getInt(idColIndex), c.getString(firstNameColIndex), c.getString(lastNameColIndex), c.getInt(ageColIndex));
            Log.d("Read" , "id ="  + c.getInt(idColIndex) +
                    ", firstName ="          + c.getString(firstNameColIndex) +
                    ", lastName ="           + c.getString(lastNameColIndex) +
                    ", age ="                + c.getInt(ageColIndex));
            persons.add(p);
        }
        while (c.moveToNext());
    }
    else
        Log.d("read", "0 rows");
    c.close();
    return persons;
}

}
