package com.diwixis.bestsimpleproject.listProject.domain;

import com.diwixis.bestsimpleproject.listProject.data.DbRepository;
import com.diwixis.bestsimpleproject.listProject.data.Note;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class DataInteractor {
    @Inject    DbRepository dbRepository;

    @Inject
    public DataInteractor() {
    }

    public RealmResults<Note> getNotesFromDb() {
        return dbRepository.getNotes();
    }

    public void deleteNote(Note note) {
        dbRepository.deleteNote(note);
    }

    public void updateNote(Note note) {
        dbRepository.setNote(note);
    }

    public void updateFlag(Note note, boolean flag) {
        dbRepository.updateFlag(note, flag);
    }
}
