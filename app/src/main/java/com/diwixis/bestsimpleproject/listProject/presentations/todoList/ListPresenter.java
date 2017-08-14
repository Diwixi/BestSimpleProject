package com.diwixis.bestsimpleproject.listProject.presentations.todoList;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.diwixis.bestsimpleproject.base.BestApp;
import com.diwixis.bestsimpleproject.listProject.data.Note;
import com.diwixis.bestsimpleproject.listProject.domain.DataInteractor;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Diwixis on 04.08.2017.
 */
@InjectViewState
public class ListPresenter extends MvpPresenter<IListView>{
    @Inject DataInteractor dataInteractor;

    ListPresenter() {
        BestApp.getListComponent().inject(this);
    }

    void editItem(Note note) {
        getViewState().startItemActivity(note);
    }

    RealmResults<Note> getNotesFromDb() {
        return dataInteractor.getNotesFromDb();
    }


    public void deleteItem(Note note) {
        dataInteractor.deleteNote(note);
    }

    public void updateItem(Note note) {
        dataInteractor.updateNote(note);
    }

    public void updateItemAndFlag(Note note, boolean flag) {
        dataInteractor.updateFlag(note, flag);
    }
}
