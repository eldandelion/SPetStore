package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.SequenceMapper
import com.spookydan.spetstore.model.Sequence
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("SequenceDao")
class SequenceMapperImpl(
    @Autowired
    private val sqlSession: SqlSession
) : SequenceMapper {

    override fun getSequence(sequence: Sequence): Sequence {
        try {
            val mapper = sqlSession.getMapper(SequenceMapper::class.java)
            return mapper.getSequence(sequence)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sequence
    }

    override fun updateSequence(sequence: Sequence) {
        try {
            val mapper = sqlSession.getMapper(SequenceMapper::class.java)
            mapper.updateSequence(sequence)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}