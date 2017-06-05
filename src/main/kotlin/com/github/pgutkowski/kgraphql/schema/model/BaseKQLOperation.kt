package com.github.pgutkowski.kgraphql.schema.model

import com.github.pgutkowski.kgraphql.schema.SchemaException
import kotlin.reflect.KType

abstract class BaseKQLOperation<T>(
        name : String,
        private val operationWrapper: FunctionWrapper<T>
) : KQLObject(name), KQLOperation<T>, FunctionWrapper<T> by operationWrapper {

    override val argumentsDescriptor = createArgumentsDescriptor()

    private fun createArgumentsDescriptor(): Map<String, KType> {
        val arguments : MutableMap<String, KType> = mutableMapOf()
        valueParameters().associateTo(arguments) { parameter ->
            if(parameter.name == null){
                throw SchemaException("Cannot handle nameless argument on function: ${operationWrapper.kFunction}")
            }
            parameter.name!! to parameter.type
        }
        return arguments
    }
}