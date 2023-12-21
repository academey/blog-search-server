/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.kakao.bank.blog.search.domain

class LinkedList {
    private var head: com.kakao.bank.blog.search.domain.LinkedList.Node? = null

    fun add(element: String) {
        val newNode = com.kakao.bank.blog.search.domain.LinkedList.Node(element)

        val it = tail(head)
        if (it == null) {
            head = newNode
        } else {
            it.next = newNode
        }
    }

    private fun tail(head: com.kakao.bank.blog.search.domain.LinkedList.Node?): com.kakao.bank.blog.search.domain.LinkedList.Node? {
        var it: com.kakao.bank.blog.search.domain.LinkedList.Node?

        it = head
        while (it?.next != null) {
            it = it.next
        }

        return it
    }

    fun remove(element: String): Boolean {
        var result = false
        var previousIt: com.kakao.bank.blog.search.domain.LinkedList.Node? = null
        var it: com.kakao.bank.blog.search.domain.LinkedList.Node? = head
        while (!result && it != null) {
            if (0 == element.compareTo(it.data)) {
                result = true
                unlink(previousIt, it)
                break
            }
            previousIt = it
            it = it.next
        }

        return result
    }

    private fun unlink(
        previousIt: com.kakao.bank.blog.search.domain.LinkedList.Node?,
        currentIt: com.kakao.bank.blog.search.domain.LinkedList.Node,
    ) {
        if (currentIt == head) {
            head = currentIt.next
        } else {
            previousIt?.next = currentIt.next
        }
    }

    fun size(): Int {
        var size = 0

        var it = head
        while (it != null) {
            ++size
            it = it.next
        }

        return size
    }

    fun get(idx: Int): String {
        var index = idx
        var it = head
        while (index > 0 && it != null) {
            it = it.next
            index--
        }

        if (it == null) {
            throw IndexOutOfBoundsException("Index is out of range")
        }

        return it.data
    }

    private data class Node(val data: String) {
        var next: com.kakao.bank.blog.search.domain.LinkedList.Node? = null
    }
}