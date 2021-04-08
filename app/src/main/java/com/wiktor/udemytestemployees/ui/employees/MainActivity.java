package com.wiktor.udemytestemployees.ui.employees;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wiktor.udemytestemployees.R;
import com.wiktor.udemytestemployees.adapters.EmployeeAdapter;
import com.wiktor.udemytestemployees.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeesListView{

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    private EmployeesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new EmployeesListPresenter(this);


        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);


        //Для проверки recyclerViewEmployees
/*        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.setfName("Виктор");
        employee2.setfName("не Виктор");
        employee1.setlName("Фамилия 1");
        employee2.setlName("Фамилия 2");

        employees.add(employee1);
        employees.add(employee2);

        adapter.setEmployees(employees);*/

        presenter.loadDate();

    }

    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    public void showError(){
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }
}