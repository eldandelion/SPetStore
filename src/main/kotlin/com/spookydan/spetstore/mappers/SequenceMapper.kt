package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Sequence
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SequenceMapper {
    fun getSequence(sequence: Sequence): Sequence

    fun updateSequence(sequence: Sequence)
}