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
package org.secu3.android.models.packets.params

import org.secu3.android.models.packets.BaseOutputPacket

data class CkpsParamPacket(

    var ckpsCogsBtdc: Int = 0,

    var ckpsIgnitCogs: Int = 0,

    var ckpsEngineCyl: Int = 0,

    var ckpsCogsNum: Int = 0,

    var ckpsMissNum: Int = 0,

    var hallFlags: Int = 0,

    var hallWndWidth: Float = 0f,

    var hallDegreesBtdc: Float = 0f

) : BaseOutputPacket() {

    val risingSpark: Boolean
        get() = hallFlags.getBitValue(0) > 0

    val useCamRef: Boolean
        get() = hallFlags.getBitValue(1) > 0

    val ckpsEdge: Boolean
        get() = hallFlags.getBitValue(2) > 0

    val refsEdge: Boolean
        get() = hallFlags.getBitValue(3) > 0

    val mergeOuts: Boolean
        get() = hallFlags.getBitValue(4) > 0

    override fun pack(): String {
        TODO("Not yet implemented")
    }

    companion object {

        internal const val DESCRIPTOR = 't'

        fun parse(data: String) = CkpsParamPacket().apply {
            ckpsCogsBtdc = data[2].toInt()
            ckpsIgnitCogs = data[3].toInt()
            ckpsEngineCyl = data[4].toInt()
            ckpsCogsNum = data[5].toInt()
            ckpsMissNum = data[6].toInt()
            hallFlags = data[7].toInt()
            hallWndWidth = data.get2Bytes(8).toFloat() / ANGLE_DIVIDER
            hallDegreesBtdc = data.get2Bytes(10).toFloat() / ANGLE_DIVIDER
        }
    }
}

