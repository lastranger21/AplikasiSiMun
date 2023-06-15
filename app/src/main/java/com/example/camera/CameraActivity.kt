/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.camera

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasisimun.R
import com.example.aplikasisimun.equipment.EquipmentActivity
import com.example.camera.Camera2BasicFragment


class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        savedInstanceState ?:
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, Camera2BasicFragment.newInstance())
                .commit()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // API 5+ solution
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
