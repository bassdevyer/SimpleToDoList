package com.example.android.simpletodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Controller class for to-do app main activity
 */
public class DynamicListViewActivity
        extends AppCompatActivity
        implements View.OnClickListener,
        AdapterView.OnItemLongClickListener {

    // EditText that captures the text for the new list item
    private EditText item;

    // Performs the add item feature
    private Button add;

    // Listview that shows to-do items
    private ListView dynamicListView;

    // Stores the items data
    private ArrayList<String> list;

    // Binds the data to the listView
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Calls super method
        super.onCreate(savedInstanceState);
        // Inflates the view
        setContentView(R.layout.activity_dynamic_list_view);

        // Get references to UI widgets
        inflateViews();

        // Initialize the list
        initList();


    }

    private void initList() {
        // Initialize the list
        list = new ArrayList<>();

        // Initialize the array adapter
        adapter = new ArrayAdapter<>(
                DynamicListViewActivity.this,
                android.R.layout.simple_list_item_1,
                list);

        // Setting the adapter to the listView
        dynamicListView.setAdapter(adapter);
    }

    /**
     * Inflate all the views necessary for the activity
     */
    private void inflateViews() {
        // editText
        item = (EditText) findViewById(R.id.itemEditText);
        // Buttom
        add = (Button) findViewById(R.id.addItemButton);
        // Sets the onClickListener for the button
        add.setOnClickListener(this);
        // ListView
        dynamicListView = (ListView) findViewById(R.id.itemsListView);
        // Sets the onItemLongClickListener for the listView
        dynamicListView.setOnItemLongClickListener(this);
    }

    /**
     * Event called when user gives a click to the add item button
     * Add item to the listView
     *
     * @param v view clicked
     */
    @Override
    public void onClick(View v) {

        // Retrieves the text from the EditText
        String todoItem = item.getText().toString();
        // Checks if the user inserted any text
        if (todoItem.length() > 0) {
            // add editText value to the list
            list.add(todoItem);
            // refresh the listView
            // Use the notifyDataSetChanged() every time the list is updated.
            adapter.notifyDataSetChanged();
            // Clear the editText for the new Item
            item.getText().clear();
        }
    }

    /**
     * Delete item long clicked
     * {@inheritDoc}
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Removes the item from the list
        list.remove(position);
        // Apply changes on the adapter and refresh the listView
        adapter.notifyDataSetChanged();
        // return true since the callback consumed the long click
        //  it means that the View that currently received the event is the true event receiver and
        // the event should not be propagated to the other Views in the tree; when you return false
        // - you let the event be passed to the other Views that may consume it.
        return true;
    }
}
