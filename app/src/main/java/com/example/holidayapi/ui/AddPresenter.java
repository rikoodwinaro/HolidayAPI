package com.example.holidayapi.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.example.holidayapi.entity.AppDatabase;
import com.example.holidayapi.entity.DataPersonalHoliday;

import java.util.List;

public class AddPresenter implements AddContact.presenter {
    private AddContact.view view;

    public AddPresenter(AddContact.view view){
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataPersonalHoliday dataPersonalHoliday;
        public InsertData(AppDatabase appDatabase, DataPersonalHoliday dataPersonalHoliday) {
            this.appDatabase = appDatabase;
            this.dataPersonalHoliday = dataPersonalHoliday;
        }

        protected Long doInBackground(Void... voids){
            return appDatabase.dao().insertData(dataPersonalHoliday);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String name, String date, AppDatabase database) {
        final DataPersonalHoliday dataCrypto =  new DataPersonalHoliday();
        dataCrypto.setName(name);
        dataCrypto.setDate(date);
        new InsertData(database,dataCrypto).execute();
    }

    public void readData(AppDatabase database) {
        List<DataPersonalHoliday> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataPersonalHoliday dataPersonalHoliday;

        public EditData(AppDatabase appDatabase, DataPersonalHoliday dataPersonalHoliday){
            this.appDatabase = appDatabase;
            this.dataPersonalHoliday = dataPersonalHoliday;
        }

        protected Integer doInBackground(Void... voids){
            return  appDatabase.dao().updateData(dataPersonalHoliday);
        }

        protected void  onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String name, String date, int id, AppDatabase database) {
        final DataPersonalHoliday dataPersonalHoliday = new DataPersonalHoliday();
        dataPersonalHoliday.setName(name);
        dataPersonalHoliday.setDate(date);
        new EditData(database,dataPersonalHoliday).execute();
    }

    class  DeleteData extends  AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataPersonalHoliday dataPersonalHoliday;

        public DeleteData(AppDatabase appDatabase, DataPersonalHoliday dataPersonalHoliday){
            this.appDatabase = appDatabase;
            this.dataPersonalHoliday = dataPersonalHoliday;
        }

        protected Long doInBackground(Void... voids){
            appDatabase.dao().deleteData(dataPersonalHoliday);
            return null;
        }

        protected void  onPostExecute(Long along) {
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataPersonalHoliday dataPersonalHoliday, AppDatabase appDatabase) {
        new DeleteData(appDatabase, dataPersonalHoliday).execute();
    }
}
