package com.notepad.android.huiming.notepadapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by huiming on 15-5-29.
 * 配置activity托管fragment的通用代码
 */
public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
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

        setContentView(R.layout.activity_fragment);

        //设置fragment manager
        FragmentManager fm = getFragmentManager();
        Fragment notefragment = fm.findFragmentById(R.id.fragmentContainer);

        if (notefragment == null) {
            notefragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, notefragment)
                    .commit();
        }
    }
}
