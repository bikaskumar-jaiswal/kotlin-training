package com.example.demo.HTMLdsl

import com.example.demo.repository.User

open class Tag(val name: String) {
    val childrens = mutableListOf<Tag>()
    val attributes = mutableMapOf<String, String>()

    override fun toString(): String {  // <-- must return String
        val attrs = if(attributes.isNotEmpty()){
            attributes.map{(key, value) -> "$key=$value"}.joinToString("")}
        else
            ""
        val content = childrens.joinToString(""){it.toString()}
        return "<$name$attrs>$content</$name>"
    }
}

class HTML : Tag("html")
class head : Tag("head")
class body : Tag("body")
class Title : Tag("title") {
    var text = ""
    override fun toString(): String {
        return "<$name>$text</$name>"
    }
}

class p: Tag("p"){
    var text=""
    override fun toString(): String {
        return "<$name>$text</$name>"
    }
}

//DSL functions

fun html(init: HTML.() -> Unit): HTML{
    val html = HTML() // create instance of html class
    html.init() // calls the lamda on the instanc
    return html // returns the config instance
}

//html (head, title, body -> p)
fun HTML.head(init: head.() -> Unit){
    val head = head()
    head.init()
    childrens.add(head)
}

fun head.title(init: Title.() -> Unit){
    val title = Title()
    title.init()
    childrens.add(title)
}

fun HTML.body(init: body.() -> Unit){
    val body = body()
    body.init()
    childrens.add(body)
}

fun body.p(init:p.() -> Unit){
    val p = p()
    p.init()
    childrens.add(p)
}

fun mainPage(data: Any): HTML{
    val page = html {
        head {
            title {
                text = "User Details"
            }
        }

        body {
            when (data) {
                is Collection<*> -> {   // If data is a list
                    for (item in data) {
                        val user = item as User
                            p { text = "Id: ${user.id}" }
                            p { text = "Name: ${user.name}" }
                            p { text = "Email: ${user.email}" }
                            p { text = "Username: ${user.username}" }
                    }
                }

                is User -> {            // if Single User
                    p { text = "Id: ${data.id}" }
                    p { text = "Name: ${data.name}" }
                    p { text = "Email: ${data.email}" }
                    p { text = "Username: ${data.username}" }
                }
            else ->
            p {
                text += "Hello, User"
            }
                }
        }

    }

    return page;
}