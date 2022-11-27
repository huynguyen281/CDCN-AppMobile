package com.test.cdcn_appmobile.ui.launch.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.cdcn_appmobile.R
import com.test.cdcn_appmobile.data.models.User
import com.test.cdcn_appmobile.databinding.FragmentSignUpBinding
import com.test.cdcn_appmobile.extension.addTextChangedListener
import com.test.cdcn_appmobile.extension.backToPreFragment
import com.test.cdcn_appmobile.extension.setVisibility
import com.test.cdcn_appmobile.extension.showDialogFrag
import com.test.cdcn_appmobile.ui.launch.LaunchViewModel
import com.test.cdcn_appmobile.utils.InjectorUtil

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null
    private var launchViewModel: LaunchViewModel? = null
    private var firstTimeClick = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {

        launchViewModel =
            ViewModelProvider(
                requireActivity(),
                InjectorUtil.provideUserViewModelFactory()
            )[LaunchViewModel::class.java]

    }

    private fun initListener() {

        binding?.run {

            btnMoveLogin.setOnClickListener {
                activity?.backToPreFragment()
            }

            edtEmailSignUp.addTextChangedListener {
                if (!firstTimeClick)
                    tvWarningEmail.text = launchViewModel?.validEmail(it)
            }

            edtPassSignUp.addTextChangedListener {
                if (!firstTimeClick)
                    tvWarningPass.text = launchViewModel?.validPassWord(it)
            }

            btnSignUpFragment.setOnClickListener {
                firstTimeClick = false
                tvWarningEmail.text =
                    launchViewModel?.validEmail(edtEmailSignUp.text.toString())
                tvWarningPass.text =
                    launchViewModel?.validPassWord(edtPassSignUp.text.toString())

                if (tvWarningEmail.text.toString() == "" && tvWarningPass.text.toString() == "") {
                    launchViewModel?.insertUser(
                        User(
                            id = "",
                            email = edtEmailSignUp.text.toString().trim(),
                            password = edtPassSignUp.text.toString().trim(),
                            name = edtEmailSignUp.text.toString().trim().split("@")[0]
                        ), onStart = {
                            ctProgressBar.setVisibility(true)
                        }, onResult = {
                            ctProgressBar.setVisibility(false)
                            if (it) {
                                activity?.showDialogFrag(
                                    requireActivity().resources.getString(R.string.SignUpCongratulations),
                                    requireActivity().resources.getString(R.string.SignUpRegisterDone),
                                    requireActivity().resources.getString(R.string.SignUpMoveToLogin),
                                    requireActivity().resources.getString(R.string.SignUpCancel),
                                    negative_callback = {
                                        activity?.backToPreFragment()
                                    }
                                )
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    requireActivity().resources.getString(
                                        R.string.SignUpExistsEmail,
                                        edtEmailSignUp.text.toString().trim()
                                    ),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    )
                }
            }

        }

    }
}
