package salsa20Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import algoritms.salsa20.Salsa20;
import utils.Utils;

import java.io.*;

public class UnitTest {

    @Test
    public void rotationTest() {
        Salsa20 salsa20 = new Salsa20();

        Assertions.assertEquals(0x150f0fd8, salsa20.rotl(0xc0a8787e, 5));
    }

    @Test
    public void quarterroundTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] expected1 = {0x00000000, 0x00000000, 0x00000000, 0x00000000};
        int[] expected2 = {0x08008145, 0x00000080, 0x00010200, 0x20500000};
        int[] expected3 = {0x88000100, 0x00000001, 0x00000200, 0x00402000};
        int[] expected4 = {0x80040000, 0x00000000, 0x00000001, 0x00002000};
        int[] expected5 = {0x00048044, 0x00000080, 0x00010000, 0x20100001};
        int[] expected6 = {0xe876d72b, 0x9361dfd5, 0xf1460244, 0x948541a3};
        int[] expected7 = {0x3e2f308c, 0xd90a8f36, 0x6ab2a923, 0x2883524c};

        Assertions.assertArrayEquals(expected1, salsa20.quarterround(0x00000000, 0x00000000, 0x00000000, 0x00000000));
        Assertions.assertArrayEquals(expected2, salsa20.quarterround(0x00000001, 0x00000000, 0x00000000, 0x00000000));
        Assertions.assertArrayEquals(expected3, salsa20.quarterround(0x00000000, 0x00000001, 0x00000000, 0x00000000));
        Assertions.assertArrayEquals(expected4, salsa20.quarterround(0x00000000, 0x00000000, 0x00000001, 0x00000000));
        Assertions.assertArrayEquals(expected5, salsa20.quarterround(0x00000000, 0x00000000, 0x00000000, 0x00000001));
        Assertions.assertArrayEquals(expected6, salsa20.quarterround(0xe7e8c006, 0xc4f9417d, 0x6479b4b2, 0x68c67137));
        Assertions.assertArrayEquals(expected7, salsa20.quarterround(0xd3917c5b, 0x55f1c407, 0x52a58a7a, 0x8f887a3b));
    }

    @Test
    public void rowroundTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] input1 = {
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000
        };
        int[] input2 = {
                0x08521bd6, 0x1fe88837, 0xbb2aa576, 0x3aa26365,
                0xc54c6a5b, 0x2fc74c2f, 0x6dd39cc3, 0xda0a64f6,
                0x90a2f23d, 0x067f95a6, 0x06b35f61, 0x41e4732e,
                0xe859c100, 0xea4d84b7, 0x0f619bff, 0xbc6e965a
        };
        int[] expected1 = {
                0x08008145, 0x00000080, 0x00010200, 0x20500000,
                0x20100001, 0x00048044, 0x00000080, 0x00010000,
                0x00000001, 0x00002000, 0x80040000, 0x00000000,
                0x00000001, 0x00000200, 0x00402000, 0x88000100
        };
        int[] expected2 = {
                0xa890d39d, 0x65d71596, 0xe9487daa, 0xc8ca6a86,
                0x949d2192, 0x764b7754, 0xe408d9b9, 0x7a41b4d1,
                0x3402e183, 0x3c3af432, 0x50669f96, 0xd89ef0a8,
                0x0040ede5, 0xb545fbce, 0xd257ed4f, 0x1818882d
        };

        Assertions.assertArrayEquals(expected1, salsa20.rowround(input1));
        Assertions.assertArrayEquals(expected2, salsa20.rowround(input2));
    }

    @Test
    public void columnroundTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] input1 = {
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000001, 0x00000000, 0x00000000, 0x00000000
        };
        int[] input2 = {
                0x08521bd6, 0x1fe88837, 0xbb2aa576, 0x3aa26365,
                0xc54c6a5b, 0x2fc74c2f, 0x6dd39cc3, 0xda0a64f6,
                0x90a2f23d, 0x067f95a6, 0x06b35f61, 0x41e4732e,
                0xe859c100, 0xea4d84b7, 0x0f619bff, 0xbc6e965a
        };
        int[] expected1 = {
                0x10090288, 0x00000000, 0x00000000, 0x00000000,
                0x00000101, 0x00000000, 0x00000000, 0x00000000,
                0x00020401, 0x00000000, 0x00000000, 0x00000000,
                0x40a04001, 0x00000000, 0x00000000, 0x00000000
        };
        int[] expected2 = {
                0x8c9d190a, 0xce8e4c90, 0x1ef8e9d3, 0x1326a71a,
                0x90a20123, 0xead3c4f3, 0x63a091a0, 0xf0708d69,
                0x789b010c, 0xd195a681, 0xeb7d5504, 0xa774135c,
                0x481c2027, 0x53a8e4b5, 0x4c1f89c5, 0x3f78c9c8
        };

        Assertions.assertArrayEquals(expected1, salsa20.columnround(input1));
        Assertions.assertArrayEquals(expected2, salsa20.columnround(input2));
    }

    @Test
    public void doubleroundTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] input1 = {
                0x00000001, 0x00000000, 0x00000000, 0x00000000,
                0x00000000, 0x00000000, 0x00000000, 0x00000000,
                0x00000000, 0x00000000, 0x00000000, 0x00000000,
                0x00000000, 0x00000000, 0x00000000, 0x00000000
        };
        int[] input2 = {
                0xde501066, 0x6f9eb8f7, 0xe4fbbd9b, 0x454e3f57,
                0xb75540d3, 0x43e93a4c, 0x3a6f2aa0, 0x726d6b36,
                0x9243f484, 0x9145d1e8, 0x4fa9d247, 0xdc8dee11,
                0x054bf545, 0x254dd653, 0xd9421b6d, 0x67b276c1
        };
        int[] expected1 = {
                0x8186a22d, 0x0040a284, 0x82479210, 0x06929051,
                0x08000090, 0x02402200, 0x00004000, 0x00800000,
                0x00010200, 0x20400000, 0x08008104, 0x00000000,
                0x20500000, 0xa0000040, 0x0008180a, 0x612a8020
        };
        int[] expected2 = {
                0xccaaf672, 0x23d960f7, 0x9153e63a, 0xcd9a60d0,
                0x50440492, 0xf07cad19, 0xae344aa0, 0xdf4cfdfc,
                0xca531c29, 0x8e7943db, 0xac1680cd, 0xd503ca00,
                0xa74b2ad6, 0xbc331c5c, 0x1dda24c7, 0xee928277
        };

        Assertions.assertArrayEquals(expected1, salsa20.doubleround(input1));
        Assertions.assertArrayEquals(expected2, salsa20.doubleround(input2));
    }

    @Test
    public void littleendianTest() {
        Salsa20 salsa20 = new Salsa20();

        Assertions.assertEquals(0x00000000, salsa20.littleendian(0, 0, 0, 0));
        Assertions.assertEquals(0x091e4b56, salsa20.littleendian(86, 75, 30, 9));
        Assertions.assertEquals(0xfaffffff, salsa20.littleendian(255, 255, 255, 250));
    }

    @Test
    public void invertLittleendianTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] expected1 = {0, 0, 0, 0};
        int[] expected2 = {86, 75, 30, 9};
        int[] expected3 = {255, 255, 255, 250};

        Assertions.assertArrayEquals(expected1, salsa20.invertLittleendian(0x00000000));
        Assertions.assertArrayEquals(expected2, salsa20.invertLittleendian(0x091e4b56));
        Assertions.assertArrayEquals(expected3, salsa20.invertLittleendian(0xfaffffff));
    }

    @Test
    public void hashTest() {
        Salsa20 salsa20 = new Salsa20();
        int[] input1 = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        int[] input2 = {
                211, 159, 13, 115, 76, 55, 82, 183, 3, 117, 222, 37, 191, 187, 234, 136,
                49, 237, 179, 48, 1, 106, 178, 219, 175, 199, 166, 48, 86, 16, 179, 207,
                31, 240, 32, 63, 15, 83, 93, 161, 116, 147, 48, 113, 238, 55, 204, 36,
                79, 201, 235, 79, 3, 81, 156, 47, 203, 26, 244, 243, 88, 118, 104, 54
        };
        int[] input3 = {
                88, 118, 104, 54, 79, 201, 235, 79, 3, 81, 156, 47, 203, 26, 244, 243,
                191, 187, 234, 136, 211, 159, 13, 115, 76, 55, 82, 183, 3, 117, 222, 37,
                86, 16, 179, 207, 49, 237, 179, 48, 1, 106, 178, 219, 175, 199, 166, 48,
                238, 55, 204, 36, 31, 240, 32, 63, 15, 83, 93, 161, 116, 147, 48, 113
        };
        int[] expected1 = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        int[] expected2 = {
                109, 42, 178, 168, 156, 240, 248, 238, 168, 196, 190, 203, 26, 110, 170, 154,
                29, 29, 150, 26, 150, 30, 235, 249, 190, 163, 251, 48, 69, 144, 51, 57,
                118, 40, 152, 157, 180, 57, 27, 94, 107, 42, 236, 35, 27, 111, 114, 114,
                219, 236, 232, 135, 111, 155, 110, 18, 24, 232, 95, 158, 179, 19, 48, 202
        };
        int[] expected3 = {
                179, 19, 48, 202, 219, 236, 232, 135, 111, 155, 110, 18, 24, 232, 95, 158,
                26, 110, 170, 154, 109, 42, 178, 168, 156, 240, 248, 238, 168, 196, 190, 203,
                69, 144, 51, 57, 29, 29, 150, 26, 150, 30, 235, 249, 190, 163, 251, 48,
                27, 111, 114, 114, 118, 40, 152, 157, 180, 57, 27, 94, 107, 42, 236, 35
        };

        Assertions.assertArrayEquals(expected1, salsa20.salsa20Hash(input1));
        Assertions.assertArrayEquals(expected2, salsa20.salsa20Hash(input2));
        Assertions.assertArrayEquals(expected3, salsa20.salsa20Hash(input3));
    }

    @Test
    public void expand16Test() {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116};
        int[] expected = {
                39, 173, 46, 248, 30, 200, 82, 17, 48, 67, 254, 239, 37, 18, 13, 247,
                241, 200, 61, 144, 10, 55, 50, 185, 6, 47, 246, 253, 143, 86, 187, 225,
                134, 85, 110, 246, 161, 163, 43, 235, 231, 94, 171, 51, 145, 214, 112, 29,
                14, 232, 5, 16, 151, 140, 183, 141, 171, 9, 122, 181, 104, 182, 177, 193
        };

        Assertions.assertArrayEquals(expected, salsa20.salsa20Expand16(k, n));
    }

    @Test
    public void expand32Test() {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 201, 202, 203,
                204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216
        };
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116};
        int[] expected = {
                69, 37, 68, 39, 41, 15, 107, 193, 255, 139, 122, 6, 170, 233, 217, 98,
                89, 144, 182, 106, 21, 51, 200, 65, 239, 49, 222, 34, 215, 114, 40, 126,
                104, 197, 7, 225, 197, 153, 31, 2, 102, 78, 76, 176, 84, 245, 246, 184,
                177, 160, 133, 130, 6, 72, 149, 119, 192, 195, 132, 236, 234, 103, 246, 74
        };

        Assertions.assertArrayEquals(expected, salsa20.salsa20Expand32(k, n));
    }

    @Test
    public void encryptionBytesMessageTest() throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        int[] message = new int[512];

        int[] crypt = salsa20.execute(k, n, message, 0);
        crypt = salsa20.execute(k, n, crypt, 0);
        Assertions.assertArrayEquals(message, crypt);

        Utils.logEncryptionProcess("encryptMessage", message, k, n, 0);
    }

    @Test
    public void encryptionOffsetOnBlockBoundaryTest() throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        int[] message = new int[512];

        int[] crypt = salsa20.execute(k, n, message, 128);
        crypt = salsa20.execute(k, n, crypt, 128);
        Assertions.assertArrayEquals(message, crypt);

        Utils.logEncryptionProcess("encryptMessageOnBlockBoundary", message, k, n, 0);
    }

    @Test
    public void encryptionOffsetNotOnBlockBoundaryTest() throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        int[] message = new int[512];

        int[] crypt = salsa20.execute(k, n, message, 246);
        crypt = salsa20.execute(k, n, crypt, 246);
        Assertions.assertArrayEquals(message, crypt);

        Utils.logEncryptionProcess("encryptMessageNotOnBlockBoundary", message, k, n, 0);
    }

    @Test
    public void encryptionImageTest() throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        int[] message = Utils.getImageArray("src/test/resources/imageInput", "bmp");

        int[] crypt = salsa20.execute(k, n, message, 0);
        crypt = salsa20.execute(k, n, crypt, 0);
        Assertions.assertArrayEquals(message, crypt);

        Utils.arrayToImage(Utils.intToByte(crypt), "imageOutput", "bmp");

        Utils.logEncryptionProcess("encryptImage", message, k, n, 0);
    }
}
