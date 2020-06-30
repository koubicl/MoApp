package com.example.mrfox

import org.jsoup.Jsoup

fun GetValuesFromElements() : ArrayList<String> {

//    val startNameOfService = listicek[line].indexOf(">")
//    val endNameOfService = listicek[line].indexOf("</")
//    val nameOfService = listicek[line].subSequence(startNameOfService + 1, endNameOfService)
//
//    return nameOfService
    val vals = arrayListOf<String>()
    val url = "https://www.mrfox.cz/sluzby"

    val document =
        Jsoup.connect(url).followRedirects(false).timeout(60000 /*wait up to 60 sec for response*/).get()
    val values = document.body().select(".service-item" /*css selector*/)


    if (values != null){
        for (i in 0 until values.count()){
            vals.add(values[i].text())
        }
    }

    return vals
}

fun GetNamesFromElementValues() : ArrayList<String>{
    val values = GetValuesFromElements()
    val names = arrayListOf<String>()

    for (element in values){
        var startName = 0
        while (!element[startName].isDigit()){
            startName++

        }
        names.add(element.subSequence(0, startName) as String)
    }
    return names
}

fun GetDurationFromElementValues() : ArrayList<String>{
    val values = GetValuesFromElements()
    val duration = arrayListOf<String>()

    for (element in values){
        val endDuration = element.indexOf(". Barber")
        var moveDuration = endDuration
        while (!element[moveDuration].isUpperCase()){
            moveDuration--
        }
        duration.add(element.subSequence(moveDuration + 2, endDuration) as String)
    }
    return duration
}

fun GetPricesFromElementValues() : ArrayList<MutableMap<String, String>> {
    val values = GetValuesFromElements()
    val pricesList = arrayListOf<MutableMap<String, String>>()

    for (element in values){
        val prices = mutableMapOf<String, String>()
        val barberStartOfPrice = element.indexOf(". Barber")
        val barberEndOfPrice = element.indexOf(" |")
        val barberPriceList = element.subSequence(barberStartOfPrice + 2, barberEndOfPrice).split(": ")
        prices[barberPriceList[0]] = barberPriceList[1]

        val seniorStartOfPrice = element.indexOf("Senior")
        val seniorEndOfPrice = element.indexOf(" |", seniorStartOfPrice)
        val seniorPriceList = element.subSequence(seniorStartOfPrice, seniorEndOfPrice).split(": ")
        prices[seniorPriceList[0]] = seniorPriceList[1]

        val headStartOfPrice = if (element.indexOf("Head B") == -1) element.indexOf("Head b") else
            element.indexOf("Head B")
        val headEndOfPrice = element.indexOf(" Kč", headStartOfPrice)
        val headPriceList = element.subSequence(headStartOfPrice, headEndOfPrice + 3).split(": ")
        prices[headPriceList[0]] = headPriceList[1]

        if ("Head Barber" in prices && "Head barber" in prices){
            val priceBig = prices["Head Barber"]
            val priceLow = prices["Head barber"]

            var bigEndAt = 0
            while (!priceBig!![bigEndAt].isLetter()){
                bigEndAt++
            }

            val priceB = priceBig.subSequence(0, bigEndAt - 3).toString()

            var lowEndAt = 0
            while (!priceLow!![lowEndAt].isLetter()){
                lowEndAt++
            }

            val priceL = priceLow.subSequence(0, lowEndAt - 3).toString()

            if (priceB >= priceL){
                prices.remove("Head barber")
            } else {
                prices.remove("Head Barber")
            }
        }
        pricesList.add(prices)
    }
    return pricesList
}

fun CompleteDataFromElements() : ArrayList<Map<String, String>>{
    val names = GetNamesFromElementValues()
    val duration = GetDurationFromElementValues()
    val prices = GetPricesFromElementValues()
    val data = arrayListOf<Map<String, String>>()

    for (n in 0 until names.count()){
        val completeData = mapOf("service" to names[n], "duration" to duration[n], "prices" to prices[n])
        data.add(completeData as Map<String, String>)
    }
    return data
}

