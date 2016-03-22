/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroid.component.textfield;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;

import static com.github.andrewlord1990.materialandroid.component.textfield.PasswordEditTextAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class PasswordEditTextTest {

    @Test
    public void givenContext_whenCreated_thenSetupWithDefaults() {
        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);

        //Then
        assertThat(actual)
                .hasHiddenPassword()
                .hasDefaultToggle()
                .hasToggleHiddenAlpha();
    }

    //TODO create from AttributeSet
    //TODO create from AttributeSet and defStyleAttr

    //TODO setInputType

    //TODO onTouchEvent

    //TODO togglePasswordVisibility
    //TODO setPasswordVisible

    //TODO setTintColor
    //TODO setTintColorRes

    //TODO setShownDrawable
    //TODO setShownDrawableRes

    //TODO setHiddenDrawable
    //TODO setHiddenDrawableRes

    //TODO setToggleType

}