package com.example.storage.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 本类是图片验证码的工具类
 */
public class ImageVerificationCodeUtils {

    /**
     * 生成随机的颜色。
     * 如果是背景色即isBackgroup 为true，则最大值默认为255；
     * 如果背景颜色为null或者false， 则最大值默认为0~155， 这样可以使字符的颜色更为突出
     * @param random
     * @param maxValue
     * @param isBackgroup
     * @return
     */
    private static Color randomColor(Random random, Integer maxValue, Boolean isBackgroup){
        //默认的背景颜色值为255
        Integer defaultBackgroupColor = 255;
        //默认的yanse最大值为150， 这样可以充分和背景颜色进行区分
        Integer maxColorValue = 150;
        if(isBackgroup == null || !isBackgroup){
            //说明不是背景颜色
            if(maxValue < 0 || maxValue > 150){
                return new Color(random.nextInt(maxColorValue), random.nextInt(maxColorValue), random.nextInt(maxColorValue));
            }else{
                return new Color(random.nextInt(maxValue), random.nextInt(maxValue), random.nextInt(maxValue));
            }
        }else{
            //说明是北京颜色
           return new Color(255, 255, 255);
        }
    }

    /**
     * 生成随机的验证码的图片
     * @param random
     * @return
     */
    private static char randomChar(Random random){
        String baseChar = "23456789abcdefghjkmnpqrtyABCDEFGHJKMNPQRTY";
        return baseChar.charAt(random.nextInt(baseChar.length() - 1));
    }

    /**
     * 生产随机的字体
     * @param random
     * @param fontNames       字体的名字
     * @param fontSize        字体的大小
     * @return
     */
    private static Font randomFont(Random random, String[] fontNames, Integer fontSize){
        //字体格式：
        //0：Plant（无格式）
        //1：BOLD（粗体）
        //2：ITALIC（斜体）
        //3：BLOD + ITALIC（加粗加斜体）
        Integer[] fontStyles = {0, 1, 2, 3};
        //基本的字体大小为24， 最终的字体大小会在该基础上进行适当的变化
        Integer basicFontSize = 24;
        return new Font(fontNames[random.nextInt(fontNames.length)],
                fontStyles[random.nextInt(fontStyles.length)],
                (fontSize < 0 || fontSize > 10)? basicFontSize + 10 : basicFontSize + random.nextInt(fontSize));
    }

    /**
     * 将图片验证码添加随机干扰线
     * @param random
     * @param lineNum    干扰线的数量
     * @param image      图片验证码对象
     * @param weight     验证码图片的长
     * @param height     验证码图片的宽
     */
    private static void drawLine(Random random, Integer lineNum, BufferedImage image, Integer weight, Integer height){
        //默认的随机干扰线为3条， 最大干扰线不超过5条
        lineNum = random.nextInt(lineNum);
        lineNum = (lineNum < 3) ? lineNum + 3: (lineNum > 5)? lineNum - 5 : lineNum;

        //随机干扰线的颜色
       Graphics2D graphics2D = (Graphics2D) image.getGraphics();
       for(int i = 0; i < lineNum; i++){
           int start_x = random.nextInt(weight);
           int start_y = random.nextInt(height);
           int end_x = random.nextInt(weight);
           int end_y = random.nextInt(height);
           graphics2D.setColor(randomColor(random, 150, false));
           graphics2D.drawLine(start_x, start_y, end_x, end_y);
       }
    }

    /**
     生成验证码图片
     * @param random
     * @param weight    验证码图片的长
     * @param height    验证码图片的宽
     * @param str       验证码字符串
     * @return
     */
    public static BufferedImage generateImage(Random random, Integer weight, Integer height, String str){
        //创建图片缓冲区
        BufferedImage bufferedImage = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        //设置背景颜色
        graphics2D.setBackground(randomColor(random, null, true));
        //填充矩形
        graphics2D.fillRect(0,0, weight, height);
        //设置字体的类型
        String[] fontNames = {"Georgia", "Times New Roman"};
        //画随机验证码字符
        for(int i = 0; i < str.length(); i++){
            int x = i * 1 * weight / str.length();
            graphics2D.setColor(randomColor(random, 150, false));
            graphics2D.setFont(randomFont(random, fontNames, 10));
            graphics2D.drawString(String.valueOf(str.charAt(i)), x, height - 5);
        }
        //画干扰线
        drawLine(random, 5, bufferedImage, weight, height);
        return bufferedImage;
    }

    /**
     * 生成随机的验证码的字符串
     * @param random
     * @param charNum
     * @return
     */
    public static String generateString(Random random, Integer charNum){
        //默认随机字符的数量为4~6个
        charNum = random.nextInt(charNum);
        charNum = (charNum < 4)? charNum + 4 : (charNum > 6)? charNum - 6 : charNum;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < charNum; i++) {
            char c = randomChar(random);
            sb.append(c);
        }
        return sb.toString();
    }

}
