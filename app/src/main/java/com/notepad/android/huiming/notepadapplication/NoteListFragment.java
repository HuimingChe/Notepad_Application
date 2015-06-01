package com.notepad.android.huiming.notepadapplication;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
