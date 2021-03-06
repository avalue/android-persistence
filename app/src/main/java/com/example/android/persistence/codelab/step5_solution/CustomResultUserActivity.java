/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.codelab.step5_solution;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.android.codelabs.persistence.R;


public class CustomResultUserActivity extends LifecycleActivity {

    private CustomResultViewModel mShowUserViewModel;
    private TextView mBooksTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity);
        mBooksTextView = (TextView) findViewById(R.id.books_tv);

        // Android will instantiate my ViewModel for me, and the best part is
        // the viewModel will survive configurationChanges!
        mShowUserViewModel = ViewModelProviders.of(this).get(CustomResultViewModel.class);

        // We'll observe updates to our LiveData loan string.
        mShowUserViewModel.getLoansResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String result) {
                mBooksTextView.setText(result);
            }
        });
    }

    public void onRefreshBtClicked(View view) {
        mShowUserViewModel.simulateDataUpdates();
    }
}
