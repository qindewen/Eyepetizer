package com.example.eyepetizer.ui.mine

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eyepetizer.BR

import com.example.eyepetizer.R
import com.example.eyepetizer.app.AppViewModelFactory
import com.example.eyepetizer.databinding.FragmentPersonBinding
import me.goldze.mvvmhabit.base.BaseFragment

class PersonFragment : BaseFragment<FragmentPersonBinding, PersonViewModel>() {

    companion object {
        fun newInstance() = PersonFragment()
    }

    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.fragment_person
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewModel(): PersonViewModel {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        val factory: AppViewModelFactory = AppViewModelFactory.getInstance(_mActivity.application)
        return ViewModelProviders.of(this, factory)[PersonViewModel::class.java]
    }

}
