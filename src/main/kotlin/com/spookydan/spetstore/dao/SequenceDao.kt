package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.Sequence

interface SequenceDao {
    fun getSequence(sequence: Sequence): Sequence

    fun updateSequence(sequence: Sequence)
}