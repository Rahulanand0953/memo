package com.example.memo.callbacks;

import com.example.memo.model.Note;

public interface NoteEventListener {
    void onNoteClick(Note note);
    void onNoteLongClick(Note note);
}
