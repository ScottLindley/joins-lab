package com.scottlindley.joins_lab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scottlindley.joins_lab.RecyclerView.MainRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;
    private SQLiteHelper mHelper;
    private List<String> mNames;
    private Button mMacysButton, mBostonButton, mHighSalaryButton;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.macys_button:
                mNames = mHelper.getMacysEmployees();
                break;
            case R.id.in_boston_button:
                mNames = mHelper.getBostonCompanies();
                break;
            case R.id.highest_salary_button:
                mNames = mHelper.getHighestSalary();
                break;
        }
        Log.d("TAG", ""+mNames.get(0));
        mAdapter.replaceData(mNames);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = SQLiteHelper.getInstance(MainActivity.this);

//        addData();

        mNames = new ArrayList<>();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MainRecyclerViewAdapter(mNames);
        mRecyclerView.setAdapter(mAdapter);

        mMacysButton = (Button)findViewById(R.id.macys_button);
        mBostonButton = (Button)findViewById(R.id.in_boston_button);
        mHighSalaryButton = (Button)findViewById(R.id.highest_salary_button);

        mMacysButton.setOnClickListener(this);
        mBostonButton.setOnClickListener(this);
        mHighSalaryButton.setOnClickListener(this);
    }


    public void addData(){
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("123-04-5678","John","Smith",1973,"NY"));
        employees.add(new Employee("123-04-5679","Davie","McWill",1982,"Seattle"));
        employees.add(new Employee("123-04-5680","Katerina","Wise",1973,"Boston"));
        employees.add(new Employee("123-04-5681","Donald","Lee",1992,"London"));
        employees.add(new Employee("123-04-5682","Gary","Henwood",1987,"Las Vegas"));
        employees.add(new Employee("123-04-5683","Anthony","Bright",1963,"Seattle"));
        employees.add(new Employee("123-04-5684","William","Newey",1995,"Boston"));
        employees.add(new Employee("123-04-5685","Melony","Smith",1970,"Chicago"));

        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("123-04-5678","Fuzz",60,1));
        jobs.add(new Job("123-04-5679","GA",70,2));
        jobs.add(new Job("123-04-5680","Little Place",120,5));
        jobs.add(new Job("123-04-5681","Macys",78,3));
        jobs.add(new Job("123-04-5682","New Life",65,1));
        jobs.add(new Job("123-04-5683","Believe",158,6));
        jobs.add(new Job("123-04-5684","Macys",200,8));
        jobs.add(new Job("123-04-5685","Stop",299,12));

        for(int i=0; i<8; i++){
            mHelper.insertEmployeeRow(employees.get(i));
            mHelper.insertJobRow(jobs.get(i));
        }

    }
}
