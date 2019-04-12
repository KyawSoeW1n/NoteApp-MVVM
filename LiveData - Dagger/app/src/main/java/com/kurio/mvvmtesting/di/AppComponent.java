package com.kurio.mvvmtesting.di;

import com.kurio.mvvmtesting.db.NoteDao;
import com.kurio.mvvmtesting.db.NoteRepo;
import com.kurio.mvvmtesting.db.NoteRoomDatabase;
import com.kurio.mvvmtesting.ui.activity.MainActivity;
import com.kurio.mvvmtesting.ui.viewmodel.NoteViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ContextModule.class, AppModule.class, RoomModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(NoteViewModel noteViewModel);

    NoteDao notedao();

    NoteRoomDatabase noteDatabase();

    NoteRepo NoteRepo();

}
