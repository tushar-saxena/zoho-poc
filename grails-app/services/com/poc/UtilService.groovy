package com.poc

import grails.transaction.Transactional

import java.util.regex.Matcher
import java.util.regex.Pattern

@Transactional
class UtilService {

    def generateResponse(def body) {
        Map response = [:]
        BufferedReader bufReader = new BufferedReader(new StringReader(body));
        String line=null;
        List str = []
        while( (line=bufReader.readLine()) != null )
        {
            str << line
        }
        String string1,string2
        str.each { String inputString ->
            Pattern pattern = Pattern.compile("= *");
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                string1 = inputString.substring(0, matcher.start());
                string2 = inputString.substring(matcher.end());
            }
            response["${string1}"] = string2
        }
        response
    }
}
