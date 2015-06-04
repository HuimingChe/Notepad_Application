package com.notepad.android.huiming.notepadapplication;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by huiming on 15-5-29.
 */
public class NoteListFragment extends ListFragment {

    private ArrayList<Notes> mNoteList;


    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p/>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.notes_title);
        mNoteList = NoteLab.get(getActivity()).getNotelist();

//         配置adapter适配器管理ListView
   /*     ArrayAdapter<Notes> adapter =
                new ArrayAdapter<Notes>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mNoteList);*/
        NotesAdapter adapter = new NotesAdapter(mNoteList);
        setListAdapter(adapter);
    }

    /**
     * Provide default implementation to return a simple list view.  Subclasses
     * can override to replace with their own layout.  If doing so, the
     * returned view hierarchy <em>must</em> have a ListView whose id
     * is {@link android.R.id#list android.R.id.list} and can optionally
     * have a sibling view id {@link android.R.id#empty android.R.id.empty}
     * that is to be shown when the list is empty.
     * <p/>
     * <p>If you are overriding this method with your own custom content,
     * consider including the standard layout {@link android.R.layout#list_content}
     * in your layout file, so that you continue to retain all of the standard
     * behavior of ListFragment.  In particular, this is currently the only
     * way to have the built-in indeterminant progress state be shown.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            registerForContextMenu(listView);
        } else {
            listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.notes_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_notes:
                            NotesAdapter adapter = (NotesAdapter) getListAdapter();
                            NoteLab notelab = NoteLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    notelab.deleteNotes(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            NoteLab.get(getActivity()).saveNotesLab();
                            return true;
                        default:
                            return false;
                    }

                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }
        return v;
    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Notes c = ((NotesAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), NotesActivity.class);
        i.putExtra(NoteFragment.EXTRA_NOTES_ID, c.getId());
        startActivity(i);

    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        ((NotesAdapter) getListAdapter()).notifyDataSetChanged();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.  For this method
     * to be called, you must have first called {@link #setHasOptionsMenu}.  See
     * {@link Activity#onCreateOptionsMenu(Menu) Activity.onCreateOptionsMenu}
     * for more information.
     *
     * @param menu     The options menu in which you place your items.
     * @param inflater
     * @see #setHasOptionsMenu
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_notes_list, menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p/>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_notes:
                Notes notes = new Notes();
                NoteLab.get(getActivity()).addNotes(notes);
                Intent i = new Intent(getActivity(), NotesActivity.class);
                i.putExtra(NoteFragment.EXTRA_NOTES_ID, notes.getId());
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when a context menu for the {@code view} is about to be shown.
     * Unlike {@link #onCreateOptionsMenu}, this will be called every
     * time the context menu is about to be shown and should be populated for
     * the view (or item inside the view for {@link AdapterView} subclasses,
     * this can be found in the {@code menuInfo})).
     * <p/>
     * Use {@link #onContextItemSelected(MenuItem)} to know when an
     * item has been selected.
     * <p/>
     * The default implementation calls up to
     * {@link Activity#onCreateContextMenu Activity.onCreateContextMenu}, though
     * you can not call this implementation if you don't want that behavior.
     * <p/>
     * It is not safe to hold onto the context menu after this method returns.
     * {@inheritDoc}
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.notes_list_item_context, menu);
    }

    /**
     * This hook is called whenever an item in a context menu is selected. The
     * default implementation simply returns false to have the normal processing
     * happen (calling the item's Runnable or sending a message to its Handler
     * as appropriate). You can use this method for any items for which you
     * would like to do processing without those other facilities.
     * <p/>
     * Use {@link MenuItem#getMenuInfo()} to get extra information set by the
     * View that added this menu item.
     * <p/>
     * Derived classes should call through to the base class for it to perform
     * the default menu handling.
     *
     * @param item The context menu item that was selected.
     * @return boolean Return false to allow normal context menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int postiton = info.position;
        NotesAdapter adapter = (NotesAdapter) getListAdapter();
        Notes notes = adapter.getItem(postiton);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_notes:
                NoteLab.get(getActivity()).deleteNotes(notes);
                NoteLab.get(getActivity()).saveNotesLab();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 内部类定制adapter
     */
    private class NotesAdapter extends ArrayAdapter<Notes> {
        public NotesAdapter(ArrayList<Notes> noteslist) {
            super(getActivity(), 0, noteslist);
        }

        /**
         * {@inheritDoc}
         *
         * @param position
         * @param convertView
         * @param parent
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().
                        inflate(R.layout.list_item_notes, null);
            }

            Notes c = getItem(position);

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.notes_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.note_item_list_dateTextView);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.note_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }
}
