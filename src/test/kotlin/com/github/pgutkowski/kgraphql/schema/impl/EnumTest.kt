package com.github.pgutkowski.kgraphql.schema.impl

import com.github.pgutkowski.kgraphql.extract
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


class EnumTest : BaseSchemaTest() {

    @Test
    fun testQueryResultWithEnum(){
        val map = execute("{film{type}}")
        assertNoErrors(map)
        MatcherAssert.assertThat(extract<String>(map, "data/film/type"), CoreMatchers.equalTo("FULL_LENGTH"))
    }

    @Test
    fun testQueryWithEnumArgument(){
        val map = execute("{ films: filmsByType(type: FULL_LENGTH){title, type}}")
        assertNoErrors(map)
        MatcherAssert.assertThat(extract<String>(map, "data/films[0]/type"), CoreMatchers.equalTo("FULL_LENGTH"))
        MatcherAssert.assertThat(extract<String>(map, "data/films[1]/type"), CoreMatchers.equalTo("FULL_LENGTH"))
    }
}