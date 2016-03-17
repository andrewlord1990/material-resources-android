/*
 * Copyright (C) 2016 Andrew Lord
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ColorsElementsTest {

    @Test
    public void darkIcon38IsCorrect() {
        assertThat(getColor(R.color.md_dark_icon_38)).isEqualTo(0x62000000);
    }

    @Test
    public void darkDivider12IsCorrect() {
        assertThat(getColor(R.color.md_dark_divider_12)).isEqualTo(0x1f000000);
    }

    @Test
    public void lightIcon50IsCorrect() {
        assertThat(getColor(R.color.md_light_icon_50)).isEqualTo(0x80ffffff);
    }

    @Test
    public void lightDivider12IsCorrect() {
        assertThat(getColor(R.color.md_light_divider_12)).isEqualTo(0x1fffffff);
    }

    private int getColor(int colorRes) {
        return RuntimeEnvironment.application.getResources().getColor(colorRes);
    }

}
