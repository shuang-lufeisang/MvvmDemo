/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duan.mvvmdemo.bridge.state;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * TODO tip：每个页面都要单独准备一个 stateViewModel，
 * 来托管 DataBinding 绑定的临时状态，以及视图控制器重建时状态的恢复。
 *
 * 此外，stateViewModel 的职责仅限于 状态托管，不建议在此处理 UI 逻辑，
 * UI 逻辑只适合在 Activity/Fragment 等视图控制器中完成，是 “数据驱动” 的一部分，
 * 将来升级到 Jetpack Compose 更是如此。
 *
 * 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350
 *
 * Create by KunMinX at 19/10/29
 */
public class MainActivityViewModel extends ViewModel {

    //TODO 演示 LiveData 来用作 DataBinding 数据绑定的情况。
    // 记得在视图控制器中要加入 mBinding.setLifecycleOwner(this);
    //详见 https://xiaozhuanlan.com/topic/9816742350

    public final MutableLiveData<Boolean> showToBuyer = new MutableLiveData<>();  // 买家身份

    public final MutableLiveData<Boolean> showToShop = new MutableLiveData<>();   // 商户身份


    public final ObservableBoolean initTabAndPage = new ObservableBoolean();           // init TabLayout and ViewPager

    public final ObservableField<String> pageAssetPath = new ObservableField<>();

    public void initState(){
        showToBuyer.setValue(true); // 默认打开买家身份
        showToShop.setValue(false); // 默认关闭商户身份
    }
}
