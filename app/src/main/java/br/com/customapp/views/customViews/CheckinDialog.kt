package br.com.customapp.views.customViews

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import br.com.customapp.R
import br.com.customapp.databinding.DialogCheckinBinding

class CheckinDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view: View = activity.layoutInflater.inflate(R.layout.dialog_checkin, null, false)
        val binding = DialogCheckinBinding.bind(view)
//        binding.viewModel = viewModel
        var alert = AlertDialog.Builder(activity)
        alert.setView(view)
        return alert.create()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}