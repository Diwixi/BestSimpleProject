package com.diwixis.bestsimpleproject.listProject.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class DbRepository implements IDbRepository {

    @Inject
    public DbRepository() {
    }

    @NonNull
    @Override
    public RealmResults<Note> getNotes() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Note> results = realm.where(Note.class).findAll();
        realm.commitTransaction();
        return results;
    }

    @Override
    public void setNote(Note note) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    @Override
    public void deleteNote(Note note) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Note.class).equalTo("id", note.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void updateFlag(Note note, boolean flag) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> note.setFlag(flag));
//        setNote(note);
    }
}