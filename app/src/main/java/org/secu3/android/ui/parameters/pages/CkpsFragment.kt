/* SecuDroid  - An open source, free manager for SECU-3 engine control unit
   Copyright (C) 2020 Vitaliy O. Kosharskiy. Ukraine, Kharkiv

   SECU-3  - An open source, free engine control unit
   Copyright (C) 2007 Alexey A. Shabelnikov. Ukraine, Kyiv

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.

   contacts:
              http://secu-3.org
              email: vetalkosharskiy@gmail.com
*/
package org.secu3.android.ui.parameters.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import org.secu3.android.databinding.FragmentCkpsBinding
import org.secu3.android.ui.parameters.ParamsViewModel

class CkpsFragment : Fragment() {

    private val mViewModel: ParamsViewModel by activityViewModels()
    private lateinit var mBinding: FragmentCkpsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCkpsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.ckpsLiveData.observe(viewLifecycleOwner) {
            mBinding.apply {

                if (it.ckpsEdge) {
                    ckpsEdgeRising.isChecked = true
                } else {
                    ckpsEdgeFalling.isChecked = true
                }

                if (it.refsEdge) {
                    refSEdgeRising.isChecked = true
                } else {
                    refSEdgeFalling.isChecked = true
                }

                sparkRisingEdgeForCdi.isChecked = it.risingSpark
                mergeSignalsToSingleOutput.isChecked = it.mergeOuts

                numberOfWheelsTeeth.text = it.ckpsCogsNum.toString()
                numberOfMissingTeeth.text = it.ckpsMissNum.toString()

                teethBeforeTdc.setText(it.ckpsCogsBtdc.toString(), false)
                numberOfCylinders.setText(it.ckpsEngineCyl.toString(), false)

                durationIngDriverPulseTeeth.text = it.ckpsIgnitCogs.toString()
                hallSensorInterrupterDegree.text = it.hallWndWidth.toString()
                degreesBeforeTDC.text = it.hallDegreesBtdc.toString()

                useCamSensorAsReference.isChecked = it.useCamRef
            }
        }
    }
}