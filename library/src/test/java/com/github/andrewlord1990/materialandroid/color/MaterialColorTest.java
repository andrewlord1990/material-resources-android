/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License.
 *
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroid.color;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaterialColorTest {

  @Test
  public void whenRedVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.RED_50).isEqualTo(0xffffebee);
    assertThat(MaterialColor.RED_100).isEqualTo(0xffffcdd2);
    assertThat(MaterialColor.RED_200).isEqualTo(0xffef9a9a);
    assertThat(MaterialColor.RED_300).isEqualTo(0xffe57373);
    assertThat(MaterialColor.RED_400).isEqualTo(0xffef5350);
    assertThat(MaterialColor.RED_500).isEqualTo(0xfff44336);

    assertThat(MaterialColor.RED_600).isEqualTo(0xffe53935);
    assertThat(MaterialColor.RED_700).isEqualTo(0xffd32f2f);
    assertThat(MaterialColor.RED_800).isEqualTo(0xffc62828);
    assertThat(MaterialColor.RED_900).isEqualTo(0xffb71c1c);

    assertThat(MaterialColor.RED_A100).isEqualTo(0xffff8a80);
    assertThat(MaterialColor.RED_A200).isEqualTo(0xffff5252);
    assertThat(MaterialColor.RED_A400).isEqualTo(0xffff1744);
    assertThat(MaterialColor.RED_A700).isEqualTo(0xffd50000);
  }

  @Test
  public void whenPinkVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.PINK_50).isEqualTo(0xfffce4ec);
    assertThat(MaterialColor.PINK_100).isEqualTo(0xfff8bbd0);
    assertThat(MaterialColor.PINK_200).isEqualTo(0xfff48fb1);
    assertThat(MaterialColor.PINK_300).isEqualTo(0xfff06292);
    assertThat(MaterialColor.PINK_400).isEqualTo(0xffec407a);
    assertThat(MaterialColor.PINK_500).isEqualTo(0xffe91e63);
    assertThat(MaterialColor.PINK_600).isEqualTo(0xffd81b60);
    assertThat(MaterialColor.PINK_700).isEqualTo(0xffc2185b);
    assertThat(MaterialColor.PINK_800).isEqualTo(0xffad1457);
    assertThat(MaterialColor.PINK_900).isEqualTo(0xff880e4f);

    assertThat(MaterialColor.PINK_A100).isEqualTo(0xffff80ab);
    assertThat(MaterialColor.PINK_A200).isEqualTo(0xffff4081);
    assertThat(MaterialColor.PINK_A400).isEqualTo(0xfff50057);
    assertThat(MaterialColor.PINK_A700).isEqualTo(0xffc51162);
  }

  @Test
  public void whenPurpleVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.PURPLE_50).isEqualTo(0xfff3e5f5);
    assertThat(MaterialColor.PURPLE_100).isEqualTo(0xffe1bee7);
    assertThat(MaterialColor.PURPLE_200).isEqualTo(0xffce93d8);
    assertThat(MaterialColor.PURPLE_300).isEqualTo(0xffba68c8);
    assertThat(MaterialColor.PURPLE_400).isEqualTo(0xffab47bc);
    assertThat(MaterialColor.PURPLE_500).isEqualTo(0xff9c27b0);
    assertThat(MaterialColor.PURPLE_600).isEqualTo(0xff8e24aa);
    assertThat(MaterialColor.PURPLE_700).isEqualTo(0xff7b1fa2);
    assertThat(MaterialColor.PURPLE_800).isEqualTo(0xff6a1b9a);
    assertThat(MaterialColor.PURPLE_900).isEqualTo(0xff4a148c);

    assertThat(MaterialColor.PURPLE_A100).isEqualTo(0xffea80fc);
    assertThat(MaterialColor.PURPLE_A200).isEqualTo(0xffe040fb);
    assertThat(MaterialColor.PURPLE_A400).isEqualTo(0xffd500f9);
    assertThat(MaterialColor.PURPLE_A700).isEqualTo(0xffaa00ff);
  }

  @Test
  public void whenDeepPurpleVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.DEEP_PURPLE_50).isEqualTo(0xffede7f6);
    assertThat(MaterialColor.DEEP_PURPLE_100).isEqualTo(0xffd1c4e9);
    assertThat(MaterialColor.DEEP_PURPLE_200).isEqualTo(0xffb39ddb);
    assertThat(MaterialColor.DEEP_PURPLE_300).isEqualTo(0xff9575cd);
    assertThat(MaterialColor.DEEP_PURPLE_400).isEqualTo(0xff7e57c2);
    assertThat(MaterialColor.DEEP_PURPLE_500).isEqualTo(0xff673ab7);
    assertThat(MaterialColor.DEEP_PURPLE_600).isEqualTo(0xff5e35b1);
    assertThat(MaterialColor.DEEP_PURPLE_700).isEqualTo(0xff512da8);
    assertThat(MaterialColor.DEEP_PURPLE_800).isEqualTo(0xff4527a0);
    assertThat(MaterialColor.DEEP_PURPLE_900).isEqualTo(0xff311b92);

    assertThat(MaterialColor.DEEP_PURPLE_A100).isEqualTo(0xffb388ff);
    assertThat(MaterialColor.DEEP_PURPLE_A200).isEqualTo(0xff7c4dff);
    assertThat(MaterialColor.DEEP_PURPLE_A400).isEqualTo(0xff651fff);
    assertThat(MaterialColor.DEEP_PURPLE_A700).isEqualTo(0xff6200ea);
  }

  @Test
  public void whenIndigoVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.INDIGO_50).isEqualTo(0xffe8eaf6);
    assertThat(MaterialColor.INDIGO_100).isEqualTo(0xffc5cae9);
    assertThat(MaterialColor.INDIGO_200).isEqualTo(0xff9fa8da);
    assertThat(MaterialColor.INDIGO_300).isEqualTo(0xff7986cb);
    assertThat(MaterialColor.INDIGO_400).isEqualTo(0xff5c6bc0);
    assertThat(MaterialColor.INDIGO_500).isEqualTo(0xff3f51b5);
    assertThat(MaterialColor.INDIGO_600).isEqualTo(0xff3949ab);
    assertThat(MaterialColor.INDIGO_700).isEqualTo(0xff303f9f);
    assertThat(MaterialColor.INDIGO_800).isEqualTo(0xff283593);
    assertThat(MaterialColor.INDIGO_900).isEqualTo(0xff1a237e);

    assertThat(MaterialColor.INDIGO_A100).isEqualTo(0xff8c9eff);
    assertThat(MaterialColor.INDIGO_A200).isEqualTo(0xff536dfe);
    assertThat(MaterialColor.INDIGO_A400).isEqualTo(0xff3d5afe);
    assertThat(MaterialColor.INDIGO_A700).isEqualTo(0xff304ffe);
  }

  @Test
  public void whenBlueVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.BLUE_50).isEqualTo(0xffe3f2fd);
    assertThat(MaterialColor.BLUE_100).isEqualTo(0xffbbdefb);
    assertThat(MaterialColor.BLUE_200).isEqualTo(0xff90caf9);
    assertThat(MaterialColor.BLUE_300).isEqualTo(0xff64b5f6);
    assertThat(MaterialColor.BLUE_400).isEqualTo(0xff42a5f5);
    assertThat(MaterialColor.BLUE_500).isEqualTo(0xff2196f3);
    assertThat(MaterialColor.BLUE_600).isEqualTo(0xff1e88e5);
    assertThat(MaterialColor.BLUE_700).isEqualTo(0xff1976d2);
    assertThat(MaterialColor.BLUE_800).isEqualTo(0xff1565c0);
    assertThat(MaterialColor.BLUE_900).isEqualTo(0xff0d47a1);

    assertThat(MaterialColor.BLUE_A100).isEqualTo(0xff82b1ff);
    assertThat(MaterialColor.BLUE_A200).isEqualTo(0xff448aff);
    assertThat(MaterialColor.BLUE_A400).isEqualTo(0xff2979ff);
    assertThat(MaterialColor.BLUE_A700).isEqualTo(0xff2962ff);
  }

  @Test
  public void whenLightBlueVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.LIGHT_BLUE_50).isEqualTo(0xffe1f5fe);
    assertThat(MaterialColor.LIGHT_BLUE_100).isEqualTo(0xffb3e5fc);
    assertThat(MaterialColor.LIGHT_BLUE_200).isEqualTo(0xff81d4fa);
    assertThat(MaterialColor.LIGHT_BLUE_300).isEqualTo(0xff4fc3f7);
    assertThat(MaterialColor.LIGHT_BLUE_400).isEqualTo(0xff29b6f6);
    assertThat(MaterialColor.LIGHT_BLUE_500).isEqualTo(0xff03a9f4);
    assertThat(MaterialColor.LIGHT_BLUE_600).isEqualTo(0xff039be5);
    assertThat(MaterialColor.LIGHT_BLUE_700).isEqualTo(0xff0288d1);
    assertThat(MaterialColor.LIGHT_BLUE_800).isEqualTo(0xff0277bd);
    assertThat(MaterialColor.LIGHT_BLUE_900).isEqualTo(0xff01579b);

    assertThat(MaterialColor.LIGHT_BLUE_A100).isEqualTo(0xff80d8ff);
    assertThat(MaterialColor.LIGHT_BLUE_A200).isEqualTo(0xff40c4ff);
    assertThat(MaterialColor.LIGHT_BLUE_A400).isEqualTo(0xff00b0ff);
    assertThat(MaterialColor.LIGHT_BLUE_A700).isEqualTo(0xff0091ea);
  }

  @Test
  public void whenCyanVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.CYAN_50).isEqualTo(0xffe0f7fa);
    assertThat(MaterialColor.CYAN_100).isEqualTo(0xffb2ebf2);
    assertThat(MaterialColor.CYAN_200).isEqualTo(0xff80deea);
    assertThat(MaterialColor.CYAN_300).isEqualTo(0xff4dd0e1);
    assertThat(MaterialColor.CYAN_400).isEqualTo(0xff26c6da);
    assertThat(MaterialColor.CYAN_500).isEqualTo(0xff00bcd4);
    assertThat(MaterialColor.CYAN_600).isEqualTo(0xff00acc1);
    assertThat(MaterialColor.CYAN_700).isEqualTo(0xff0097a7);
    assertThat(MaterialColor.CYAN_800).isEqualTo(0xff00838f);
    assertThat(MaterialColor.CYAN_900).isEqualTo(0xff006064);

    assertThat(MaterialColor.CYAN_A100).isEqualTo(0xff84ffff);
    assertThat(MaterialColor.CYAN_A200).isEqualTo(0xff18ffff);
    assertThat(MaterialColor.CYAN_A400).isEqualTo(0xff00e5ff);
    assertThat(MaterialColor.CYAN_A700).isEqualTo(0xff00b8d4);
  }

  @Test
  public void whenTealVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.TEAL_50).isEqualTo(0xffe0f2f1);
    assertThat(MaterialColor.TEAL_100).isEqualTo(0xffb2dfdb);
    assertThat(MaterialColor.TEAL_200).isEqualTo(0xff80cbc4);
    assertThat(MaterialColor.TEAL_300).isEqualTo(0xff4db6ac);
    assertThat(MaterialColor.TEAL_400).isEqualTo(0xff26a69a);
    assertThat(MaterialColor.TEAL_500).isEqualTo(0xff009688);
    assertThat(MaterialColor.TEAL_600).isEqualTo(0xff00897b);
    assertThat(MaterialColor.TEAL_700).isEqualTo(0xff00796b);
    assertThat(MaterialColor.TEAL_800).isEqualTo(0xff00695c);
    assertThat(MaterialColor.TEAL_900).isEqualTo(0xff004d40);

    assertThat(MaterialColor.TEAL_A100).isEqualTo(0xffa7ffeb);
    assertThat(MaterialColor.TEAL_A200).isEqualTo(0xff64ffda);
    assertThat(MaterialColor.TEAL_A400).isEqualTo(0xff1de9b6);
    assertThat(MaterialColor.TEAL_A700).isEqualTo(0xff00bfa5);
  }

  @Test
  public void whenGreenVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.GREEN_50).isEqualTo(0xffe8f5e9);
    assertThat(MaterialColor.GREEN_100).isEqualTo(0xffc8e6c9);
    assertThat(MaterialColor.GREEN_200).isEqualTo(0xffa5d6a7);
    assertThat(MaterialColor.GREEN_300).isEqualTo(0xff81c784);
    assertThat(MaterialColor.GREEN_400).isEqualTo(0xff66bb6a);
    assertThat(MaterialColor.GREEN_500).isEqualTo(0xff4caf50);
    assertThat(MaterialColor.GREEN_600).isEqualTo(0xff43a047);
    assertThat(MaterialColor.GREEN_700).isEqualTo(0xff388e3c);
    assertThat(MaterialColor.GREEN_800).isEqualTo(0xff2e7d32);
    assertThat(MaterialColor.GREEN_900).isEqualTo(0xff1b5e20);

    assertThat(MaterialColor.GREEN_A100).isEqualTo(0xffb9f6ca);
    assertThat(MaterialColor.GREEN_A200).isEqualTo(0xff69f0ae);
    assertThat(MaterialColor.GREEN_A400).isEqualTo(0xff00e676);
    assertThat(MaterialColor.GREEN_A700).isEqualTo(0xff00c853);
  }

  @Test
  public void whenLightGreenVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.LIGHT_GREEN_50).isEqualTo(0xfff1f8e9);
    assertThat(MaterialColor.LIGHT_GREEN_100).isEqualTo(0xffdcedc8);
    assertThat(MaterialColor.LIGHT_GREEN_200).isEqualTo(0xffc5e1a5);
    assertThat(MaterialColor.LIGHT_GREEN_300).isEqualTo(0xffaed581);
    assertThat(MaterialColor.LIGHT_GREEN_400).isEqualTo(0xff9ccc65);
    assertThat(MaterialColor.LIGHT_GREEN_500).isEqualTo(0xff8bc34a);
    assertThat(MaterialColor.LIGHT_GREEN_600).isEqualTo(0xff7cb342);
    assertThat(MaterialColor.LIGHT_GREEN_700).isEqualTo(0xff689f38);
    assertThat(MaterialColor.LIGHT_GREEN_800).isEqualTo(0xff558b2f);
    assertThat(MaterialColor.LIGHT_GREEN_900).isEqualTo(0xff33691e);

    assertThat(MaterialColor.LIGHT_GREEN_A100).isEqualTo(0xffccff90);
    assertThat(MaterialColor.LIGHT_GREEN_A200).isEqualTo(0xffb2ff59);
    assertThat(MaterialColor.LIGHT_GREEN_A400).isEqualTo(0xff76ff03);
    assertThat(MaterialColor.LIGHT_GREEN_A700).isEqualTo(0xff64dd17);
  }

  @Test
  public void whenLimeVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.LIME_50).isEqualTo(0xfff9fbe7);
    assertThat(MaterialColor.LIME_100).isEqualTo(0xfff0f4c3);
    assertThat(MaterialColor.LIME_200).isEqualTo(0xffe6ee9c);
    assertThat(MaterialColor.LIME_300).isEqualTo(0xffdce775);
    assertThat(MaterialColor.LIME_400).isEqualTo(0xffd4e157);
    assertThat(MaterialColor.LIME_500).isEqualTo(0xffcddc39);
    assertThat(MaterialColor.LIME_600).isEqualTo(0xffc0ca33);
    assertThat(MaterialColor.LIME_700).isEqualTo(0xffafb42b);
    assertThat(MaterialColor.LIME_800).isEqualTo(0xff9e9d24);
    assertThat(MaterialColor.LIME_900).isEqualTo(0xff827717);

    assertThat(MaterialColor.LIME_A100).isEqualTo(0xfff4ff81);
    assertThat(MaterialColor.LIME_A200).isEqualTo(0xffeeff41);
    assertThat(MaterialColor.LIME_A400).isEqualTo(0xffc6ff00);
    assertThat(MaterialColor.LIME_A700).isEqualTo(0xffaeea00);
  }

  @Test
  public void whenYellowVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.YELLOW_50).isEqualTo(0xfffffde7);
    assertThat(MaterialColor.YELLOW_100).isEqualTo(0xfffff9c4);
    assertThat(MaterialColor.YELLOW_200).isEqualTo(0xfffff59d);
    assertThat(MaterialColor.YELLOW_300).isEqualTo(0xfffff176);
    assertThat(MaterialColor.YELLOW_400).isEqualTo(0xffffee58);
    assertThat(MaterialColor.YELLOW_500).isEqualTo(0xffffeb3b);
    assertThat(MaterialColor.YELLOW_600).isEqualTo(0xfffdd835);
    assertThat(MaterialColor.YELLOW_700).isEqualTo(0xfffbc02d);
    assertThat(MaterialColor.YELLOW_800).isEqualTo(0xfff9a825);
    assertThat(MaterialColor.YELLOW_900).isEqualTo(0xfff57f17);

    assertThat(MaterialColor.YELLOW_A100).isEqualTo(0xffffff8d);
    assertThat(MaterialColor.YELLOW_A200).isEqualTo(0xffffff00);
    assertThat(MaterialColor.YELLOW_A400).isEqualTo(0xffffea00);
    assertThat(MaterialColor.YELLOW_A700).isEqualTo(0xffffd600);
  }

  @Test
  public void whenAmberVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.AMBER_50).isEqualTo(0xfffff8e1);
    assertThat(MaterialColor.AMBER_100).isEqualTo(0xffffecb3);
    assertThat(MaterialColor.AMBER_200).isEqualTo(0xffffe082);
    assertThat(MaterialColor.AMBER_300).isEqualTo(0xffffd54f);
    assertThat(MaterialColor.AMBER_400).isEqualTo(0xffffca28);
    assertThat(MaterialColor.AMBER_500).isEqualTo(0xffffc107);
    assertThat(MaterialColor.AMBER_600).isEqualTo(0xffffb300);
    assertThat(MaterialColor.AMBER_700).isEqualTo(0xffffa000);
    assertThat(MaterialColor.AMBER_800).isEqualTo(0xffff8f00);
    assertThat(MaterialColor.AMBER_900).isEqualTo(0xffff6f00);

    assertThat(MaterialColor.AMBER_A100).isEqualTo(0xffffe57f);
    assertThat(MaterialColor.AMBER_A200).isEqualTo(0xffffd740);
    assertThat(MaterialColor.AMBER_A400).isEqualTo(0xffffc400);
    assertThat(MaterialColor.AMBER_A700).isEqualTo(0xffffab00);
  }

  @Test
  public void whenOrangeVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.ORANGE_50).isEqualTo(0xfffff3e0);
    assertThat(MaterialColor.ORANGE_100).isEqualTo(0xffffe0b2);
    assertThat(MaterialColor.ORANGE_200).isEqualTo(0xffffcc80);
    assertThat(MaterialColor.ORANGE_300).isEqualTo(0xffffb74d);
    assertThat(MaterialColor.ORANGE_400).isEqualTo(0xffffa726);
    assertThat(MaterialColor.ORANGE_500).isEqualTo(0xffff9800);
    assertThat(MaterialColor.ORANGE_600).isEqualTo(0xfffb8c00);
    assertThat(MaterialColor.ORANGE_700).isEqualTo(0xfff57c00);
    assertThat(MaterialColor.ORANGE_800).isEqualTo(0xffef6c00);
    assertThat(MaterialColor.ORANGE_900).isEqualTo(0xffe65100);

    assertThat(MaterialColor.ORANGE_A100).isEqualTo(0xffffd180);
    assertThat(MaterialColor.ORANGE_A200).isEqualTo(0xffffab40);
    assertThat(MaterialColor.ORANGE_A400).isEqualTo(0xffff9100);
    assertThat(MaterialColor.ORANGE_A700).isEqualTo(0xffff6d00);
  }

  @Test
  public void whenDeepOrangeVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.DEEP_ORANGE_50).isEqualTo(0xfffbe9e7);
    assertThat(MaterialColor.DEEP_ORANGE_100).isEqualTo(0xffffccbc);
    assertThat(MaterialColor.DEEP_ORANGE_200).isEqualTo(0xffffab91);
    assertThat(MaterialColor.DEEP_ORANGE_300).isEqualTo(0xffff8a65);
    assertThat(MaterialColor.DEEP_ORANGE_400).isEqualTo(0xffff7043);
    assertThat(MaterialColor.DEEP_ORANGE_500).isEqualTo(0xffff5722);
    assertThat(MaterialColor.DEEP_ORANGE_600).isEqualTo(0xfff4511e);
    assertThat(MaterialColor.DEEP_ORANGE_700).isEqualTo(0xffe64a19);
    assertThat(MaterialColor.DEEP_ORANGE_800).isEqualTo(0xffd84315);
    assertThat(MaterialColor.DEEP_ORANGE_900).isEqualTo(0xffbf360c);

    assertThat(MaterialColor.DEEP_ORANGE_A100).isEqualTo(0xffff9e80);
    assertThat(MaterialColor.DEEP_ORANGE_A200).isEqualTo(0xffff6e40);
    assertThat(MaterialColor.DEEP_ORANGE_A400).isEqualTo(0xffff3d00);
    assertThat(MaterialColor.DEEP_ORANGE_A700).isEqualTo(0xffdd2c00);
  }

  @Test
  public void whenBrownVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.BROWN_50).isEqualTo(0xffefebe9);
    assertThat(MaterialColor.BROWN_100).isEqualTo(0xffd7ccc8);
    assertThat(MaterialColor.BROWN_200).isEqualTo(0xffbcaaa4);
    assertThat(MaterialColor.BROWN_300).isEqualTo(0xffa1887f);
    assertThat(MaterialColor.BROWN_400).isEqualTo(0xff8d6e63);
    assertThat(MaterialColor.BROWN_500).isEqualTo(0xff795548);
    assertThat(MaterialColor.BROWN_600).isEqualTo(0xff6d4c41);
    assertThat(MaterialColor.BROWN_700).isEqualTo(0xff5d4037);
    assertThat(MaterialColor.BROWN_800).isEqualTo(0xff4e342e);
    assertThat(MaterialColor.BROWN_900).isEqualTo(0xff3e2723);
  }

  @Test
  public void whenGrayVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.GRAY_50).isEqualTo(0xfffafafa);
    assertThat(MaterialColor.GRAY_100).isEqualTo(0xfff5f5f5);
    assertThat(MaterialColor.GRAY_200).isEqualTo(0xffeeeeee);
    assertThat(MaterialColor.GRAY_300).isEqualTo(0xffe0e0e0);
    assertThat(MaterialColor.GRAY_400).isEqualTo(0xffbdbdbd);
    assertThat(MaterialColor.GRAY_500).isEqualTo(0xff9e9e9e);
    assertThat(MaterialColor.GRAY_600).isEqualTo(0xff757575);
    assertThat(MaterialColor.GRAY_700).isEqualTo(0xff616161);
    assertThat(MaterialColor.GRAY_800).isEqualTo(0xff424242);
    assertThat(MaterialColor.GRAY_900).isEqualTo(0xff212121);
  }

  @Test
  public void whenBlueGrayVariants_thenCorrectColorValues() {
    assertThat(MaterialColor.BLUE_GRAY_50).isEqualTo(0xffeceff1);
    assertThat(MaterialColor.BLUE_GRAY_100).isEqualTo(0xffcfd8dc);
    assertThat(MaterialColor.BLUE_GRAY_200).isEqualTo(0xffb0bec5);
    assertThat(MaterialColor.BLUE_GRAY_300).isEqualTo(0xff90a4ae);
    assertThat(MaterialColor.BLUE_GRAY_400).isEqualTo(0xff78909c);
    assertThat(MaterialColor.BLUE_GRAY_500).isEqualTo(0xff607d8b);
    assertThat(MaterialColor.BLUE_GRAY_600).isEqualTo(0xff546e7a);
    assertThat(MaterialColor.BLUE_GRAY_700).isEqualTo(0xff455a64);
    assertThat(MaterialColor.BLUE_GRAY_800).isEqualTo(0xff37474f);
    assertThat(MaterialColor.BLUE_GRAY_900).isEqualTo(0xff263238);
  }

  @Test
  public void whenBlack_thenCorrectColorValue() {
    assertThat(MaterialColor.BLACK).isEqualTo(0xff000000);
    assertThat(MaterialColor.BLACK_87).isEqualTo(0xdf000000);
    assertThat(MaterialColor.BLACK_54).isEqualTo(0x8a000000);
    assertThat(MaterialColor.BLACK_38).isEqualTo(0x62000000);
    assertThat(MaterialColor.BLACK_12).isEqualTo(0x1f000000);
  }

  @Test
  public void whenWhite_thenCorrectColorValue() {
    assertThat(MaterialColor.WHITE).isEqualTo(0xffffffff);
    assertThat(MaterialColor.WHITE_70).isEqualTo(0xb3ffffff);
    assertThat(MaterialColor.WHITE_50).isEqualTo(0x80ffffff);
    assertThat(MaterialColor.WHITE_12).isEqualTo(0x1fffffff);
  }

  @Test
  public void whenCreated_thenContainsColor() {
    //Given
    int expected = MaterialColor.AMBER_300;

    //When
    MaterialColor actual = new MaterialColor(expected);

    //Then
    assertThat(actual.getColor()).isEqualTo(expected);
  }

  @Test
  public void whenFromInt_thenContainsColor() {
    //Given
    int expected = MaterialColor.AMBER_300;

    //When
    MaterialColor actual = MaterialColor.fromInt(expected);

    //Then
    assertThat(actual.getColor()).isEqualTo(expected);
  }

}