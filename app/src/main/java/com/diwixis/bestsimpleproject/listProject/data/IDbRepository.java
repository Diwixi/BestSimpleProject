package com.diwixis.bestsimpleproject.listProject.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by Diwixis on 08.08.2017.
 */

interface IDbRepository {
    @NonNull
    RealmResults<Note> getNotes();

    void setNote(Note note);

    void deleteNote(Note note);

    void updateFlag(Note note, boolean flag);
}
