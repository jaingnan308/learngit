package com.family.Finance.MyFamily;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /** 加密字符串 */
    private static final String key_1    = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /** 加密字符串转ASCII时，对产生的空格进行加密 */
    private static final String key_2    = "@#$%&*+=?";
    /** 对加密字符串进行移位 */
    private static final int    MOVE_NUM = 6;

    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    // String str = "HGP&KP&HGL&LG&HGP&LH&HHM&LI&";
    // String str = "HGJ&PN&HHH&HIH&PN&HHG&KO&KP&LG&LK&"; //gaohhyan
    // String str = "HII&KP&KO&KP&KP&LG&LN&"; //luohuih

    /**
     * 赵勇在线ERP登陆 密码解码方式 解密ASCII码
     * 
     * @author Dwen
     * @param pwd
     * @return
     */
    public static void main(String[] args) {
        String str = "HGJ&PN&HHH&HIH&PN&HHG&KO&KP&LG&LK&";
        StringBuffer sb_2 = new StringBuffer();
        for (int i = 0; i < str.length() - 1; i++) {
            if (key_2.contains(Character.toString(str.charAt(i)))) {
                sb_2.append(" ");
            } else {
                sb_2.append(key_1.indexOf(str.charAt(i) - MOVE_NUM));
            }
        }
        String result = asciiToString(sb_2.toString());
        System.out.println("result=" + result);
    }

    /**
     * ASCII码转字符串
     * 
     * @author Dwen
     */
    public static String asciiToString(String ascii) {
        StringBuffer sb_4 = new StringBuffer();
        String[] chars = ascii.split(" ");
        for (int j = 0; j < chars.length; j++) {
            sb_4.append((char) Integer.parseInt(chars[j]));
        }
        return sb_4.toString();
    }
}
