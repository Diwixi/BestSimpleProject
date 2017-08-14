package com.diwixis.bestsimpleproject.listProject.presentations.todoListItem;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.diwixis.bestsimpleproject.base.BestApp;
import com.diwixis.bestsimpleproject.listProject.data.Note;
import com.diwixis.bestsimpleproject.listProject.domain.DataInteractor;

import javax.inject.Inject;

/**
 * Created by Diwixis on 08.08.2017.
 */
@InjectViewState
public class ListItemPresenter extends MvpPresenter<IListItemView> {
    @Inject    DataInteractor dataInteractor;

    ListItemPresenter() {
        BestApp.getListComponent().inject(this);
    }

    void canShow(Intent intent) {
        Note note = new Note(intent.getIntExtra(Note.extraId, 0),
                intent.getIntExtra(Note.extraColorRes, 0),
                intent.getIntExtra(Note.extraNextColorRes, 0),
                intent.getStringExtra(Note.extraMessage),
                intent.getStringExtra(Note.extraMessageTitle),
                intent.getBooleanExtra(Note.extraIsChecked, false));
        getViewState().fillingFields(note);
    }

    void updateNote(Note note) {
        dataInteractor.updateNote(note);
    }
}
