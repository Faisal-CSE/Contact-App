package com.faisal.porag.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.faisal.porag.db.DataBaseHelper;
import com.faisal.porag.model.Contact;



public class ContactDAO {

	private DataBaseHelper helper;

	public ContactDAO(Context context) {
		helper = new DataBaseHelper(context);
	}

	private ContentValues getContentValues(Contact contact) {
		ContentValues values = new ContentValues();

			values.put("name", contact.getName());
			values.put("phone", contact.getPhone());
		
		return values;
	}

	private Contact getContact(Cursor cursor) {
		Contact contact = new Contact();

			contact.setId(cursor.getLong(cursor.getColumnIndex("id")));
			contact.setName(cursor.getString(cursor.getColumnIndex("name")));
			contact.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			
		return contact;
	}

	public long addContact(Contact contact) {
		ContentValues values = getContentValues(contact);
		return helper.ADD(values, "contact");
	}


	private List<Contact> list(String query) {
		List<Contact> list = new ArrayList<Contact>();
		Cursor cursor = helper.ConditionalReadQuery(query);
		if (cursor.moveToFirst()) {
			do {
				Contact contact = getContact(cursor);
				list.add(contact);
			} while (cursor.moveToNext());
		}
		return list;
	}

//	private Contact contact(String query) {
//		Contact contact = null;
//		Cursor cursor = helper.ConditionalReadQuery(query);
//		if (cursor.moveToFirst()) {
//			do {
//				contact = getContact(cursor);
//
//			} while (cursor.moveToNext());
//		}
//
//		return contact;
//	}

	
	public List<Contact> getContactList(int f,int t) {
		StringBuilder query = new StringBuilder(" SELECT * FROM contact LIMIT "+ f +","+ t +";");
		return this.list(query.toString());
	}


	

}
