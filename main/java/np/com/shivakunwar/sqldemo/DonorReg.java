package np.com.shivakunwar.sqldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DonorReg extends AppCompatActivity {

    MyDBHandler dbHandler;
    TextView viewText;
    EditText name, type;
    private String _name;
    private String _type;
    Button btnSend = (Button) findViewById(R.id.btnSubmit);

    

    public DonorReg(String name, String type) {
        this._name = name;
        this._type = type;
    }

    public String get_name() {
        return _name;
    }

    public String get_type() {
        return _type;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_type(String _type) {
        this._type = _type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        viewText = (TextView) findViewById(R.id.viewText);
        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById(R.id.type);
        dbHandler = new MyDBHandler(this, "Sql_Demo", null, 1);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonorReg donor = new DonorReg(name.getText().toString(), type.getText().toString());
                dbHandler.addName(donor);
                printDatabase();
            }
        });

    }

    /* Add a product to the database
    public void submit(View v){
        DonorReg donor = new DonorReg(name.getText().toString(), type.getText().toString());
        dbHandler.addName(donor);
        printDatabase();
    } */

    //Delete items



    public void printDatabase(){
        String dbString = dbHandler.databasetoString();
        viewText.setText(dbString);
        viewText.setText("");

    }

}
