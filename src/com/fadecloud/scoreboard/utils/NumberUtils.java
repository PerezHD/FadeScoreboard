/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fadecloud.scoreboard.utils;

import java.text.DecimalFormat;

/**
 *
 * @author devan_000
 */
public class NumberUtils {

    private static DecimalFormat formatter = new DecimalFormat("#");

    public static String formatMoney(long balance) {
        //Its over 1 trillion.
        if (balance >= 1000000000000.0) {
            return formatter.format(balance / 1000000000000.0) + "Trillion";
        }

        //Its over 1 bil.
        if (balance >= 1000000000.0) {
            return formatter.format(balance / 1000000000.0) + "Billion";
        }

        //Its over 1 mil.
        if (balance >= 1000000.0) {
            return formatter.format(balance / 1000000.0) + "Million";
        }

        if (balance < 1000) {
            return balance + "";
        }

        return formatter.format(balance / 1000.0) + "k";
    }

}
