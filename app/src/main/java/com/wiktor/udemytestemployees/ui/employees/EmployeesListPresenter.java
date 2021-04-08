package com.wiktor.udemytestemployees.ui.employees;

import android.widget.Toast;

import com.wiktor.udemytestemployees.api.ApiFactory;
import com.wiktor.udemytestemployees.api.ApiService;
import com.wiktor.udemytestemployees.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeesListPresenter {

    //Если объектов Disposable несколько, то можно их хранить в объекте CompositeDisposable и закрывать все разом
    private CompositeDisposable compositeDisposable;

    //Передача активити презентеру. Неправильно
    private MainActivity activity;

    //Правильно обращаться к активити через интерфейс. Этот интерфейс должен имплементироваться у активити
    private EmployeesListView view;
    //Контсруктор в котором задается значение для View
    public EmployeesListPresenter(EmployeesListView view) {
        this.view = view;
    }

    public EmployeesListPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void loadDate() {

        compositeDisposable = new CompositeDisposable();

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        Disposable disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.showData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
