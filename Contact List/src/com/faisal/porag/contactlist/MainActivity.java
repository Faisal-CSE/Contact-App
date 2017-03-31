package com.faisal.porag.contactlist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.faisal.porag.dao.ContactDAO;
import com.faisal.porag.data.adapter.ContactListDataAdapter;
import com.faisal.porag.db.DataBaseHelper;
import com.faisal.porag.model.Contact;


public class MainActivity extends Activity {
	private ListView listView;
	private Button moreBtn;
	private DataBaseHelper helper;
	private ContactListDataAdapter adapter;
	private Context context = this;
	private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        helper = new DataBaseHelper(context);
        initComponent();
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	moreBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				count = count+3;
//				Toast.makeText(getApplicationContext(), "Read More", Toast.LENGTH_SHORT).show();
				adapter = new ContactListDataAdapter(getApplicationContext(), R.layout.contact_list_row);
				listView.setAdapter(adapter);
				List<Contact> list = new ContactDAO(context).getContactList(0,count);
				if(list.size() > 0){
					adapter.addList(list);
				}/**/
				else {
					Toast.makeText(context, "No Result Found ", Toast.LENGTH_SHORT).show();
				}
				registerForContextMenu(listView);
			}
			
		});
    	
    	
    }

    private void initComponent() {
    	listView = (ListView) findViewById(R.id.listview);
    	moreBtn = (Button) findViewById(R.id.load_more);
	}
    
   
    
    
    

	/*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
