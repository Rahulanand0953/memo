package com.example.memo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.memo.db.NotesDB;
import com.example.memo.db.NotesDao;
import com.example.memo.model.Note;

import java.util.Date;

public class EditeNoteActivity extends AppCompatActivity {
    private EditText inputNote;
    private NotesDao dao;
    private Note temp;
    public static final String NOTE_EXTRA_Key = "note_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_note);
        inputNote=findViewById(R.id.input_note);
        dao = NotesDB.getInstance(this).notesDao();
        if(getIntent().getExtras()!=null){
            int id= getIntent().getExtras().getInt(NOTE_EXTRA_Key, 0);
            temp=dao.getNoteById(id);
            inputNote.setText(temp.getNoteText());
        }
        else temp = new Note();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edite_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {

        String text = inputNote.getText().toString();
        if(!text.isEmpty()) {
            long date = new Date().getTime();
            temp.setNoteDate(date);
            temp.setNoteText(text);

            if(temp.getId()==-1)
                dao.insertNote(temp);
            else
                dao.updateNote(temp);

            finish();

        }

    }
}
