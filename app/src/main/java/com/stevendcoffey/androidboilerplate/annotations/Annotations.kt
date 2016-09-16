package com.stevendcoffey.androidboilerplate.annotations

import android.os.Bundle

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Retain

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class FindView(val xmlTag: Int)

fun saveObjectInstanceState(obj: Any, bundle: Bundle?) {
    for (field in obj.javaClass.declaredFields) {
        if (field.isAnnotationPresent(Retain::class.java)) {
            field.isAccessible = true
            when (field.type.toString()) {
                String::class.java.toString() -> bundle?.putString(field.name, field.get(obj) as String)
                Boolean::class.java.toString() -> bundle?.putByte(field.name, if(field.getBoolean(obj)) 1 else 0)
                Int::class.java.toString() -> bundle?.putInt(field.name, field.getInt(obj))
                Double::class.java.toString() -> bundle?.putDouble(field.name, field.getDouble(obj))
            }
            field.isAccessible = false
        }
    }
}

fun restoreObjectInstanceState(obj: Any, savedInstanceState: Bundle?) {
    savedInstanceState?.let {  _savedInstanceState ->
        for (key in _savedInstanceState.keySet()) {
            try {
                val field = obj.javaClass.getDeclaredField(key)
                if (field.isAnnotationPresent(Retain::class.java)) {
                    field.isAccessible = true
                    when (field.type.toString()) {
                        String::class.java.toString() -> field.set(obj, _savedInstanceState.getString(field.name))
                        Boolean::class.java.toString() -> field.set(obj, if (_savedInstanceState.getByte(field.name) > 0) true else false)
                        Int::class.java.toString() -> field.set(obj, _savedInstanceState.getInt(field.name))
                        Double::class.java.toString() -> field.set(obj, _savedInstanceState.getDouble(field.name))
                    }
                    field.isAccessible = false
                }
            } catch (ignored: NoSuchFieldException) {}
        }
    }
}

fun findViewsForObject(obj: Any, rootView: android.view.View?) {
    rootView?.let { _rootView ->
        for (field in obj.javaClass.declaredFields) {
            if (field.isAnnotationPresent(FindView::class.java)) {
                field.isAccessible = true
                val viewAnnotation = field.getAnnotation(FindView::class.java)
                field.set(obj, _rootView.findViewById(viewAnnotation.xmlTag))
                field.isAccessible = false
            }
        }
    }
}
