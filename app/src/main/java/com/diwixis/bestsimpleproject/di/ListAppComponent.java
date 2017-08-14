package com.diwixis.bestsimpleproject.di;

import com.diwixis.bestsimpleproject.listProject.presentations.todoList.ListPresenter;
import com.diwixis.bestsimpleproject.listProject.presentations.todoListItem.ListItemPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diwixis on 08.08.2017.
 */

@Singleton
@Component(modules = {ListModule.class, AppModule.class})
public interface ListAppComponent {
    void inject(ListPresenter presenter);
    void inject(ListItemPresenter presenter);
}
