package com.faisal.porag.data.adapter;

import java.util.ArrayList;
import java.util.List;

import com.faisal.porag.contactlist.R;
import com.faisal.porag.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class ContactListDataAdapter extends ArrayAdapter<Contact>{
	List<Contact> list=new ArrayList<Contact>();


	public ContactListDataAdapter(Context context, int resource) {
		super(context, resource);
	}
	static class LayoutHandler
	{
		TextView name;
		TextView phone;
	}
	
	@Override
	public void add(Contact object) {
		super.add(object);
		list.add(object);
	}
	
	public void addList(List<Contact> list){
		super.addAll(list);
		this.list.addAll(list);
	}
	
	public void removeAll(){
		super.clear();
		this.list.clear();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public Contact getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row=convertView;
		LayoutHandler layoutHandler;
		
		if(row == null)
		{
			LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row=layoutInflater.inflate(R.layout.contact_list_row, parent, false);
			layoutHandler=new LayoutHandler();
			
			layoutHandler.name = (TextView) row.findViewById(R.id.name);
			layoutHandler.phone = (TextView) row.findViewById(R.id.phone);
			
			row.setTag(layoutHandler);
		}
		else {
			layoutHandler=(LayoutHandler) row.getTag();
		}
		
		Contact contact = (Contact) this.getItem(position);
			layoutHandler.name.setText(contact.getName());	
			layoutHandler.phone.setText(contact.getPhone());	
		
		return row;
	}


}
