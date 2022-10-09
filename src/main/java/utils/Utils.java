package utils;

import algoritms.salsa20.Salsa20;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static int[] stringToInt(String s) {
        return byteToInt(stringToByte(s));
    }

    public static String intToString(int[] a) {
        return byteToString(intToByte(a));
    }

    public static String byteToString(byte[] b) {
        return new String(b, StandardCharsets.UTF_8);
    }

    public static byte[] stringToByte(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] intToByte(int[] a) {
        byte[] b = new byte[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = (byte) a[i];
        return b;
    }

    public static int[] byteToInt(byte[] b) {
        int[] a = new int[b.length];
        for (int i = 0; i < b.length; i++)
            a[i] = b[i] & 0xFF;
        return a;
    }

    public static int[] getImageArray(String src, String extension) throws IOException {
        BufferedImage img = ImageIO.read(new File(src + "." + extension));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(img, extension, bos);
        return byteToInt(bos.toByteArray());
    }

    public static void arrayToImage(byte[] data, String name, String extension) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
        ImageIO.write(img, extension, new File(name + "." + extension));
    }

    public static void logEncryptionProcess(String nameFileOutput, int[] message, int[] k, int[] n, long streamIndex) throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] crypt;

        BufferedWriter bf = new BufferedWriter(new FileWriter(nameFileOutput + ".txt"));

        bf.append("Original message: ");
        bf.newLine();
        for (int i = 0; i < message.length; i++) {
            if (i % 64 == 0)
                bf.newLine();
            bf.append(message[i] + " ");
        }

        bf.newLine();
        bf.newLine();

        crypt = salsa20.execute(k, n, message, streamIndex);

        bf.append("Encrypted message: ");
        bf.newLine();
        for (int i = 0; i < crypt.length; i++) {
            if (i % 64 == 0)
                bf.newLine();
            bf.append(crypt[i] + " ");
        }

        bf.newLine();
        bf.newLine();

        crypt = salsa20.execute(k, n, crypt, streamIndex);

        bf.append("Decrypted message: ");
        bf.newLine();
        for (int i = 0; i < crypt.length; i++) {
            if (i % 64 == 0)
                bf.newLine();
            bf.append(crypt[i] + " ");
        }

        bf.close();
    }

    public static String testCasesGenerator(String path, boolean is128) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        path = path.substring(0, path.lastIndexOf('.'));
        new File(path).mkdirs();

        String line, pathFile;
        BufferedWriter bw = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("Set")) {
                pathFile = path + "\\" + line.trim().replaceAll(":", "") + ".txt";
                bw = new BufferedWriter(new FileWriter(pathFile));

                bw.append(br.readLine().trim());

                if (!is128)
                    bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());

                for (int i = 0; i < 3; i++)
                    bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());
                for (int i = 0; i < 3; i++)
                    bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());
                for (int i = 0; i < 3; i++)
                    bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());
                for (int i = 0; i < 3; i++)
                    bw.append(br.readLine().trim());

                bw.newLine();
                bw.append(br.readLine().trim());
                for (int i = 0; i < 3; i++)
                    bw.append(br.readLine().trim());

                bw.close();
            }
        }

        br.close();
        return path;
    }

    public static ArrayList<ArrayList<Integer>> parseTestCase(File testCase) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(testCase));
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        ArrayList<Integer> streamIndex = new ArrayList<>();

        String line;

        while ((line = br.readLine()) != null) {
            String textToRemove = "";
            if (line.contains("key")) {
                textToRemove = "key = ";
            } else if (line.contains("IV")) {
                textToRemove = "IV = ";
            } else if (line.contains("stream")) {
                textToRemove = "stream\\[.+\\] = ";
            } else if (line.contains("xor-digest")) {
                textToRemove = "xor-digest = ";
            }

            Pattern pattern = Pattern.compile("stream\\[(\\d+).+\\]");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find())
                streamIndex.add(Integer.valueOf(matcher.group(1), 10));

            line = line.replaceAll(textToRemove, "").trim();

            ArrayList<Integer> inner = new ArrayList<>();

            for (int j = 0; j <= line.length() - 2; j += 2)
                inner.add(Integer.valueOf(line.substring(j, j + 2), 16));

            data.add(inner);
        }

        data.add(streamIndex);

        return data;
    }

    public static int[] toPrimitive(ArrayList<Integer> array) {
        return Arrays.stream(array.toArray(new Integer[array.size()])).mapToInt(Integer::intValue).toArray();
    }

}

